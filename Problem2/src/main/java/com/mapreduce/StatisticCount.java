package com.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.mapreduce.StatsWritable;

public class StatisticCount {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, StatsWritable> {

        private Text token = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString(), "\\n");

            while (itr.hasMoreTokens()) {
                token.set(itr.nextToken());
                StringTokenizer st = new StringTokenizer(token.toString());
                IntWritable identifier = new IntWritable(Integer.parseInt(st.nextToken()));
                DoubleWritable v = new DoubleWritable(Double.parseDouble(st.nextToken()));
                StatsWritable statsWritable = new StatsWritable(identifier, v);
                context.write(new Text(identifier.get() + ""), statsWritable);
            }
        }
    }

    /**
     * Counts statistics by each row by id
     */
    public static class MyCombiner
            extends Reducer<Text, StatsWritable, Text, StatsWritable> {

        private StatsWritable result = new StatsWritable();

        public void reduce(Text id, Iterable<StatsWritable> values, Context context
        ) throws IOException, InterruptedException {
            double sum = 0;
            int count = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            double mean = 0;
            double stdDeviation = 0;
            List<Double> localCopy = new ArrayList<>();
            for (StatsWritable val : values) {
                double v = val.getValue().get();
                localCopy.add(v);
                count++;
                sum += v;
                if (v > max) {
                    max = v;
                }
                if (v < min) {
                    min = v;
                }

            }
            mean = sum / count;
            for (double val : localCopy) {
                stdDeviation += Math.pow(val - mean, 2);
            }
            stdDeviation = Math.sqrt(stdDeviation / count);

            result.setMean(new DoubleWritable(mean));
            result.setMin(new DoubleWritable(min));
            result.setMax(new DoubleWritable(max));
            result.setStdDeviation(new DoubleWritable(stdDeviation));
            result.setId(new IntWritable(Integer.parseInt(id.toString())));

            context.write(new Text("global"), result);
        }
    }

    /**
     * Counts global statistics
     */
    public static class StatsReducer
            extends Reducer<Text, StatsWritable, Text, StatsWritable> {

        private StatsWritable result = new StatsWritable();

        public void reduce(Text key, Iterable<StatsWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            double sum = 0;
            int count = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            double mean = 0;
            double stdDeviation = 0;
            List<StatsWritable> localCopy = new ArrayList<>();
            for (StatsWritable val : values) {
                context.write(new Text(val.getId().toString()), val);
                localCopy.add(val);
                count++;
                sum += val.getMean().get();
                if (val.getMax().get() > max) {
                    max = val.getMax().get();
                }
                if (val.getMin().get() < min) {
                    min = val.getMin().get();
                }
            }
            mean = sum / count;
            for (StatsWritable val : localCopy) {
                stdDeviation += Math.pow(val.getMean().get() - mean, 2);
            }
            stdDeviation = Math.sqrt(stdDeviation / count);
//            result.set(mean + "\t" + min + "\t" + max + "\t" + stdDeviation);
//            context.write(new Text("global"), result);
            result.setMean(new DoubleWritable(mean));
            result.setMin(new DoubleWritable(min));
            result.setMax(new DoubleWritable(max));
            result.setStdDeviation(new DoubleWritable(stdDeviation));
            context.write(key, result);


        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "stat count");
        job.setJarByClass(StatisticCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(MyCombiner.class);
        job.setReducerClass(StatsReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(StatsWritable.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(StatsWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}