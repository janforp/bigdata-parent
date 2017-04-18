package com.janita.hadoop.consts;

import java.io.File;

/**
 * Created by Janita on 2017-04-17 14:51
 */
public class Const {

    public static String HDFS_URI = "hdfs://192.168.128.101:9000/user/";

    public static String LOCAL_URI = "d:"+ File.separator;

    public static final String[] DATA = {
            "One, two, buckle my shoe",
            "Three, four, shut the door",
            "Five, six, pick up the sticks",
            "Seven, eight, lay them straight",
            "Nine, ten, a big fat hen"
    };
}
