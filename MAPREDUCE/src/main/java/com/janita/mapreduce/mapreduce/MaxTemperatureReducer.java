package com.janita.mapreduce.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-18 15:39
 */
public class MaxTemperatureReducer extends Reducer<Text,IntWritable,Text,IntWritable>{

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int maxValue = Integer.MIN_VALUE;
        for (IntWritable value : values){
            maxValue = Math.max(maxValue,value.get());
        }
        context.write(key,new IntWritable(maxValue));
    }
}
