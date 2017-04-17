package com.janita.hdfs.pathfilter;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * Created by Janita on 2017-04-17 15:56
 */
public class RegexExcludePathFilter implements PathFilter {

    private final String regex;

    public RegexExcludePathFilter(String regex){
        this.regex = regex ;
    }

    public boolean accept(Path path) {
        return !path.toString().matches(regex);
    }
}
