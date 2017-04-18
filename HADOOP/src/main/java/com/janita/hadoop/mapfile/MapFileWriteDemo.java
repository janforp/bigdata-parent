package com.janita.hadoop.mapfile;

import com.janita.hadoop.consts.Const;
import com.janita.hdfs.utils.HDFSUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-18 13:33
 */
public class MapFileWriteDemo {

    public static void main(String[] args) throws IOException {

        String uri = Const.HDFS_URI+"mapfile.txt";
        Configuration conf = new Configuration();
        FileSystem fs = HDFSUtil.getFileSystem(conf,uri,"root");

        IntWritable key = new IntWritable();
        Text value = new Text();
        MapFile.Writer writer = null;

        try {
            writer = new MapFile.Writer(conf,fs,uri,key.getClass(),value.getClass());
            writer.setIndexInterval(128);
            for (int i=0;i<1024;i++){
                key.set(i+1);
                value.set(Const.DATA[i % Const.DATA.length]);
                writer.append(key,value);
            }
        }finally {
            IOUtils.closeStream(writer);
        }
    }
}
