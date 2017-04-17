package com.janita.hdfs.filesystem;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.DefaultCodec;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Janita on 2017-04-17 15:23
 * 显示某个目录下面的所有文件
 */
public class ListStatus {
    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://192.168.128.101:9000/home/"),conf,"root");

        Path[] paths = new Path[1];
        paths[0] = new Path("hdfs://192.168.128.101:9000/home/");

        FileStatus[] statuses = fs.listStatus(paths);
        Path[] listedPaths = FileUtil.stat2Paths(statuses);
        for (Path path : listedPaths){
            System.out.println("*******"+path);
        }
    }
}