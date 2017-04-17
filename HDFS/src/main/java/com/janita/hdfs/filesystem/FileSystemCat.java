package com.janita.hdfs.filesystem;

import com.janita.hdfs.consts.Const;
import com.janita.hdfs.utils.HDFSUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Janita on 2017-04-17 14:48
 */
public class FileSystemCat {

    public static void main(String[] args) throws IOException {

        String uri = Const.HDFS_URI+"info.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri),conf);
        HDFSUtil.copyBytes(fs,new Path(uri),System.out);
    }
}
