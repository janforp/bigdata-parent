package com.janita.hadoop.sequencefile;

import com.janita.hadoop.consts.Const;
import com.janita.hdfs.utils.HDFSUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-18 11:17
 */
public class SequenceFileWriteDemo {

    private static final String[] DATA = {
            "One, two, buckle my shoe",
            "Three, four, shut the door",
            "Five, six, pick up the sticks",
            "Seven, eight, lay them straight",
            "Nine, ten, a big fat hen"
    };
    public static void main(String[] args) throws IOException, InterruptedException {
        String uri = Const.HDFS_URI + "seq.txt";
        FileSystem fs = HDFSUtil.getFileSystem(null, uri, null);
        Path path = new Path(uri);

        IntWritable key = new IntWritable();
        Text value = new Text();
        SequenceFile.Writer writer = null ;
        try {
            writer = SequenceFile.createWriter(fs,new Configuration(),path,key.getClass(),value.getClass());
            for (int i =0 ;i<100 ; i++){
                key.set(100-i);
                value.set(DATA[i % DATA.length]);
                System.out.printf("*******[%s]\t%s\t%s\n",writer.getLength(),key,value);
                writer.append(key,value);
            }
        }finally {
            IOUtils.closeStream(writer);
        }

    }
}
