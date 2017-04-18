package com.janita.hadoop.sequencefile;

import com.janita.hdfs.consts.Const;
import com.janita.hdfs.utils.HDFSUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

/**
 * Created by Janita on 2017-04-18 12:31
 */
public class SequenceFileReadDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        String uri = Const.HDFS_URI+"seq.txt";
        FileSystem fs = HDFSUtil.getFileSystem(null,uri,null);
        Path path = new Path(uri);
        SequenceFile.Reader reader = null;

        try {
            reader = new SequenceFile.Reader(fs,path,new Configuration());
            Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(),new Configuration());
            Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(),new Configuration());

            long position = reader.getPosition();
            while (reader.next(key,value)){
                String syncSeen = reader.syncSeen() ? "*" : "";
                System.out.printf("*******[%s%s]\t%s\t%s\n",position,syncSeen,key,value);
                position = reader.getPosition();
            }
        }finally {
            IOUtils.closeStream(reader);
        }
    }
}