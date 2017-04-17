package com.janita.hdfs.filesystem;

import com.janita.hdfs.consts.Const;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

/**
 * Created by Janita on 2017-04-17 15:11
 */
public class FileCopyWithProgress {

    public static void main(String[] args) throws IOException, InterruptedException {

        String localSrc = Const.LOCAL_URI+"local.txt";
        String dst = Const.HDFS_URI+"localToHdfs.txt";
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst),conf,"root");
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            public void progress() {
                System.out.println("*******" );
            }
        });

        IOUtils.copyBytes(in,out,4096,true);
    }
}
