package com.jy.collection.map;

import java.util.TreeMap;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-16 15:44
 */
public class TreeMap_ {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap((o1,o2)->
          //这里传入的构造器是比较key的长度大小 所以当key的长度相同时 视为key相同 则只会对该key-value的值进行替换
                o1.toString().length()-o2.toString().length()
        );
        for (int i = 0; i <20 ; i++) {
            treeMap.put(i,i);
        }
        System.out.println("treeMap = " + treeMap);
        /**
         * treeMap 是有序的key-value结构 其内部类entry right 与left 对新加入的元素根据自定义的比较器或者默认的比较器来决定存放位置
         * 不存在扩容 添加一个元素就新建一个entry按比较器计算顺序追加
         * 首次添加时 会添加root即第一个元素
         *
         public V put(K key, V value) {
         *         Entry<K,V> t = root;
         *         if (t == null) {
         *             compare(key, key); // type (and possibly null) check
         *  //这里的Entry时TreeMap的内部类
         *             root = new Entry<>(key, value, null);
         *             size = 1;
         *             modCount++;
         *             return null;
         *         }
         *         int cmp;
         *         Entry<K,V> parent;
         *         // split comparator and comparable paths
         *         Comparator<? super K> cpr = comparator;
         *   //在add时，这里的比较器就是我们自定义的比较器
         *         if (cpr != null) {
         *             do {
         *                 parent = t;
         *   //动态绑定到我们的匿名内部类(对象)即自定义的比较器
         *   //此时比较的结果只会返回三种值：正数,0,负数
         *                 cmp = cpr.compare(key, t.key);
         *                 if (cmp < 0)
         *                     t = t.left;
         *                 else if (cmp > 0)
         *                     t = t.right;
         *                 else
         *   //此时为零，则以为在比较器执行后视为两个元素相同
         *                     return t.setValue(value);
         *             } while (t != null);
         *         }
         *         else {
         *             if (key == null)
         *                 throw new NullPointerException();
         *             @SuppressWarnings("unchecked")
         *                 Comparable<? super K> k = (Comparable<? super K>) key;
         *             do {
         *                 parent = t;
         *                 cmp = k.compareTo(t.key);
         *                 if (cmp < 0)
         *                     t = t.left;
         *                 else if (cmp > 0)
         *                     t = t.right;
         *                 else
         *                     return t.setValue(value);
         *             } while (t != null);
         *         }
         *         Entry<K,V> e = new Entry<>(key, value, parent);
         *         if (cmp < 0)
         *             parent.left = e;
         *         else
         *             parent.right = e;
         *         fixAfterInsertion(e);
         *         size++;
         *         modCount++;
         *         return null;
         *     }
         */
    }
}
