package com.janita.mapreduce.configuration;

import org.apache.hadoop.conf.Configuration;

/**
 * Created by Janita on 2017-04-18 14:15
 */
public class ConfigurationMergeTest {

    public static void main(String[] args){

        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        conf.addResource("configuration-2.xml");
        System.out.println("*******"+conf.get("color"));
        System.out.println("*******"+conf.get("weight"));
        System.out.println("*******"+conf.get("size-weight"));

        System.setProperty("size","20");
        System.out.println("*******"+conf.get("size-weight"));

    }
}
