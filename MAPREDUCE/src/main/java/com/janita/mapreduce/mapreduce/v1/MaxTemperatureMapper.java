package com.janita.mapreduce.mapreduce.v1;

import com.janita.mapreduce.consts.Const;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-18 16:35
 */
public class MaxTemperatureMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String year = line.substring(15,19);
        String airT = line.substring(87,92);
        if (!missing(airT)){
            int tem = Integer.parseInt(airT);
            context.write(new Text(year),new IntWritable(tem));
        }
    }

    private boolean missing(String temp){
        return temp.equals("+9999");
    }

    @Test
    public void ignoreMissingTemRecord(){
        Text value = new Text(Const.LINE_TWO);
        new MapDriver<LongWritable,Text,Text,IntWritable>()
                .withMapper(new MaxTemperatureMapper())
                .withInputValue(value)
                .runTest();
    }
}
