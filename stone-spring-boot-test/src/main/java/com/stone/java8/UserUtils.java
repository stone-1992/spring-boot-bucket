package com.stone.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stone
 * @projectName spring-boot-bucket
 * @description: TODO
 * @date 2020/9/24 17:37
 */
public class UserUtils {

    public static List<User> getUsers() {
        User user1 = new User(1001L, "张三");
        User user2 = new User(1002L, "李四");
        User user3 = new User(1003L, "王五");
        User user4 = new User(1004L, "赵六");
        User user5 = new User();
        User user6 = null;
        ArrayList<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        return list;
    }
}
