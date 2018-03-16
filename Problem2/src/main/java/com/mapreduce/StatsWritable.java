package com.mapreduce;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StatsWritable implements Writable {
    private DoubleWritable mean;
    private DoubleWritable min;
    private DoubleWritable max;
    private DoubleWritable stdDeviation;
    private DoubleWritable value;
    private IntWritable id;

    public StatsWritable() {
        this.id = new IntWritable(0);
        this.value = new DoubleWritable(0);
        this.mean = new DoubleWritable(0);
        this.min = new DoubleWritable(0);
        this.max = new DoubleWritable(0);
        this.stdDeviation = new DoubleWritable(0);
    }

    public StatsWritable(IntWritable id, DoubleWritable value) {
        this.id = id;
        this.value = value;
        this.mean = new DoubleWritable(0);
        this.min = new DoubleWritable(0);
        this.max = new DoubleWritable(0);
        this.stdDeviation = new DoubleWritable(0);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        id.write(dataOutput);
        value.write(dataOutput);
        mean.write(dataOutput);
        min.write(dataOutput);
        max.write(dataOutput);
        stdDeviation.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        id.readFields(dataInput);
        value.readFields(dataInput);
        mean.readFields(dataInput);
        min.readFields(dataInput);
        max.readFields(dataInput);
        stdDeviation.readFields(dataInput);
    }

    public DoubleWritable getMean() {
        return mean;
    }

    public void setMean(DoubleWritable mean) {
        this.mean = mean;
    }

    public DoubleWritable getMin() {
        return min;
    }

    public void setMin(DoubleWritable min) {
        this.min = min;
    }

    public DoubleWritable getMax() {
        return max;
    }

    public void setMax(DoubleWritable max) {
        this.max = max;
    }

    public DoubleWritable getStdDeviation() {
        return stdDeviation;
    }

    public void setStdDeviation(DoubleWritable stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public IntWritable getId() {
        return id;
    }

    public void setId(IntWritable id) {
        this.id = id;

    }

    Text getText() {
        return new Text(mean + "\t" + min + "\t" + max + "\t" + stdDeviation);
    }

    @Override
    public String toString() {
        return mean + "\t" + min + "\t" + max + "\t" + stdDeviation;
    }

    public DoubleWritable getValue() {
        return value;
    }

    public void setValue(DoubleWritable value) {
        this.value = value;
    }
}
