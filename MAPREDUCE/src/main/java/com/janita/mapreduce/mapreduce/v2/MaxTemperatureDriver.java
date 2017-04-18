package com.janita.mapreduce.mapreduce.v2;

import com.janita.mapreduce.mapreduce.v1.MaxTemperatureMapper;
import com.janita.mapreduce.mapreduce.v1.MaxTemperatureReducer;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-18 17:31
 */
public class MaxTemperatureDriver extends Configured implements Tool {

    public MaxTemperatureDriver() throws IOException {
    }

    public int run(String[] args) throws Exception {
        if (args.length != 2){
            System.err.printf("Usage: %s [generic options] <input> <output>\n",getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }
        Job job = new Job(getConf(),"Max temp");
        job.setJarByClass(getClass());

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setMapperClass(MaxTemperatureMapper.class);
        job.setCombinerClass(MaxTemperatureReducer.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        return job.waitForCompletion(true) ? 0 :1 ;
    }

    public static void main(String[] args) throws Exception {

        int code = ToolRunner.run(new MaxTemperatureDriver(),args);
        System.exit(code);
    }

}
