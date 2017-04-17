package com.janita.hdfs.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by Janita on 2017-04-17 19:22
 */
public class FileCompressor {

    public static void main(String[] args) throws IOException, InterruptedException {
        String uri = "/home/info.gz";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri),conf,"root");

        Path inputPath = new Path(uri);
        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(inputPath);

        if (codec == null){
            System.out.println("******* NO ECODEC FOUND FOR "+uri);
            System.exit(1);
        }

        String outpubUri = CompressionCodecFactory.removeSuffix(uri,codec.getDefaultExtension());

        InputStream in = null;
        OutputStream out = null;
        try {
            in = codec.createInputStream(fs.open(inputPath));
            out = fs.create(new Path(outpubUri));
            IOUtils.copyBytes(in,out,conf);
        }finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }
}
