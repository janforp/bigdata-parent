package com.janita.hdfs.utils;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Janita on 2017-04-17 15:02
 */
public class HDFSUtil {

    /**
     * 把HDFS上某个文件的内容放入某个输出流中
     * @param fs   HDFS文件操作
     * @param source    源文件
     * @param out       输出流
     * @throws IOException
     */
    public static OutputStream copyBytes(FileSystem fs, Path source, OutputStream out) throws IOException {
        try {
            IOUtils.copyBytes(fs.open(source),out,4069,false);
        }finally {
            fs.close();
        }
        return out;
    }
}
