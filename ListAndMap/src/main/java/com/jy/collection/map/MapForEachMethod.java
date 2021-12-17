package com.jy.collection.map;

import java.util.*;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-17 14:45
 */
public class MapForEachMethod {
    public static void main(String[] args) {
        Map map = new HashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i,i*i);
        }
        //方式1：根据keySet()方法 获取所有key,根据key去取value
        System.out.println("--------------方式一------------------");
        Set set = map.keySet();
        for (Object key:set){
            System.out.println("Key= " +key+ ",value= " +map.get(key));

        }

        //方式二：直接通过values()方法 取出所有值
        System.out.println("--------------方式二------------------");
        Collection values = map.values();
        System.out.println("values = " + values);


        //方式三：根据entrySet()取出entrySet集合 迭代器遍历然后转换成Map.Entry类型直接取出key-value
        System.out.println("--------------方式三------------------");
        Set forEntrySet = map.entrySet();
        Iterator iterator2 = forEntrySet.iterator();
        while (iterator2.hasNext()){
            Map.Entry next = (Map.Entry) iterator2.next();
            System.out.println("Key= " +next.getKey()+ ",value= " +next.getValue());
        }


        //方式四：lambda表达式ForEach遍历循环 实质上底层用的还是迭代器
        System.out.println("--------------方式四------------------");
        map.forEach((o1,o2)-> System.out.println("Key= " +o1+ ",value= " +o2));


    }
}
