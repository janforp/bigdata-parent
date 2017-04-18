package com.janita.hadoop.consts;

/**
 * Created by Janita on 2017-04-18 13:47
 */
public class Test {

    public static void main(String[] args){


        User user1 = new User();
        user1.setAddress("address1");
        user1.setName("name1");

        User user2 = new User();
        user2.setAddress(user1.getAddress()+1);
        user2.setName(user1.getName());

        System.out.println("*******"+user1);
        System.out.println("*******"+user2);
    }
}
