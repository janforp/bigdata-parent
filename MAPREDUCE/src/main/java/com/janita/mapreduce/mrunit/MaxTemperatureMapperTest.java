package com.janita.mapreduce.mrunit;

import com.janita.mapreduce.mapreduce.MaxTemperatureMapper;
import com.janita.mapreduce.mapreduce.MaxTemperatureReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Janita on 2017-04-18 15:47
 */
public class MaxTemperatureMapperTest {

    @Test
    public void processesValidRecord(){
        Text value = new Text("0043011990999991950051518004+68750+023550FM-12+038299999V0203201N00261220001CN9999999N9-00111+99999999999");
        new MapDriver<LongWritable,Text,Text,IntWritable>()
                .withMapper(new MaxTemperatureMapper())     //指定map的类
                .withInputValue(value)   //指定map的输入的value,因为这个map不关心输入的key，所以不需要写
                .withOutput(new Text("1950"),new IntWritable(-11))//预判本次map的输出的key-value
                .runTest(); //若实际与预判有出入则报错
    }

    @Test
    public void returnsMax(){
        new ReduceDriver<Text,IntWritable,Text,IntWritable>()
                .withReducer(new MaxTemperatureReducer())   //指定reduce的类
                .withInputKey(new Text("1950"))     //指定reduce的输入的key
                .withInputValues(Arrays.asList(new IntWritable(10),new IntWritable(5))) //指定reduce的输入的value
                .withOutput(new Text("1950"),new IntWritable(10))//预判本次reduce执行的结果的key-value
                .runTest();//若实际结果与预判的有出入，则会报错
    }
}
