package com.jy.collection.set;

import java.util.TreeSet;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-15 15:56
 */
public class TreeSet_ {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet((o1,o2)->{
            return o2.toString().compareTo(o1.toString());
        });
        for (int i = 0; i <10 ; i++) {
            treeSet.add(new HashSet_.A_(i));

        }
        treeSet.add("Jack");
        treeSet.add("Rose");
        treeSet.add("Rose");
        treeSet.add("Tom");
        treeSet.add("A");
        System.out.println("treeSet = " + treeSet);
        /**
         * TreeSet 底层时TreeMap 
         * TreeSet 可以添加比较器 来对添加的元素进行自定义排序
         * 底层调用treeMap的有参构造器
         * public TreeSet(Comparator<? super E> comparator) {
         *         this(new TreeMap<>(comparator));
         *     }
         *
         *
         *  public V put(K key, V value) {
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
