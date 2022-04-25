package com.jy.collection.list;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2022-04-01 14:32
 */
public class CopyOnWriteArrayList_ {
    public static void main(String[] args) {

        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("123");
        copyOnWriteArrayList.get(1);
    }
}
