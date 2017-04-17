package com.janita.hdfs.pathfilter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Janita on 2017-04-17 16:09
 */
public class FilterFile {

    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://192.168.128.101:9000/home/"),conf,"root");

        FileStatus[] statuses = fs.globStatus(new Path("/home/*"),new RegexExcludePathFilter("^.*/home/wordcount.txt.2"));

        Path[] paths = FileUtil.stat2Paths(statuses);
        for (Path path :paths){
            System.out.println("******* "+path);
        }
    }
}
