package com.janita.mapreduce.configuration;

import org.apache.hadoop.conf.Configuration;

/**
 * Created by Janita on 2017-04-18 14:15
 */
public class ConfigurationTest {

    public static void main(String[] args){

        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
        System.out.println("*******"+conf.get("color"));
    }
}
