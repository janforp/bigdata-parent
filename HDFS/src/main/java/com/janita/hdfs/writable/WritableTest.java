package com.janita.hdfs.writable;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by Janita on 2017-04-17 19:58
 */
public class WritableTest {

    private static byte[] serialize(Writable writable) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        writable.write(dataOutputStream);
        dataOutputStream.close();

        return out.toByteArray();
    }

    private static byte[] deserialize(Writable writable,byte[] bytes) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        DataInputStream dataInputStream = new DataInputStream(in);
        writable.readFields(dataInputStream);
        dataInputStream.close();

        return bytes;
    }

    public static void main(String[] args) throws IOException {

        Text t = new Text("\u0041\u00DF\u6771\uD801\uDC00");
        ByteBuffer buffer = ByteBuffer.wrap(t.getBytes(),0,t.getLength());
        int cp;
        while (buffer.hasRemaining() && (cp = Text.bytesToCodePoint(buffer)) != -1){
            System.out.println("******* "+Integer.toHexString(cp));
        }

    }
}
