package com.janita.hdfs.utils;

import com.janita.hdfs.consts.Const;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

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

    /**
     * 获取FileSystem实例
     * @param conf      配置
     * @param pathURI   文件路径
     * @param user      用户名
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static FileSystem getFileSystem(Configuration conf,String pathURI,String user){
        if (org.apache.commons.lang.StringUtils.isEmpty(user)){
            user = Const.USER_ROOT;
        }
        if (conf == null){
            conf = new Configuration();
        }
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(pathURI),conf,user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fs;
    }
}
