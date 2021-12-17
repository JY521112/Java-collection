package com.jy.collection.set;

import java.util.LinkedHashSet;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-15 14:06
 */
public class LinkedHashSet_ {
    public static void main(String[] args) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("张三");
        linkedHashSet.add("李四");
        linkedHashSet.add("王二");
        linkedHashSet.add(new HashSet_.A_(123));//1
        linkedHashSet.add("加菲");
        linkedHashSet.add("rose");
        linkedHashSet.add("pike");
        linkedHashSet.add(new HashSet_.A_(123));//2

        //上面新增中1和2在同一个链表中通过next将两个元素连接起来 但顺序是按照before与after

        System.out.println("linkedHashSet = " + linkedHashSet);

        /**
         *
         * LinkedHashSet底层时LinkedHashMap(hashMap的子类),实际上是一个哈希表和链表的结合，且是一个双向链表。
         * 其余与hashSet一样 达到条件时同样也会树化
         * 双向链表是链表的一种，他的每个数据节点都有两个指针分别指向直接后继和直接前驱，所以从双向链表的任意一个节点开始都可以很方便的访问它的前驱节点和后继节点。
         * 这是双向链表的优点，那么有优点就有缺点，缺点是每个节点都需要保存当前节点的next和prev两个属性(就是下面的before与after)，这样才能保证优点。
         * 所以需要更多的内存开销，并且删除和添加也会比较费时间。
         * 1.LinkedHashSet 创建时 调用super的构造器，底层时LinkedHashMap 初始化大小为16 负载因子为0.75
         *
         * HashSet(int initialCapacity, float loadFactor, boolean dummy) {
         *         map = new LinkedHashMap<>(initialCapacity, loadFactor);
         *     }
         * 2.添加元素时 底层也是调用hashMap的putVal方法 但 LinkedHashSet 每一个节点都有prev和next形成双向链表
         * 添加第一次时，直接将数组扩容到16，节点存放类型是LinkedHashMap$Entry 即数组是HashMap$Node[] 存放的元素/数据是LinkedHashMap$Entry类型
         * 多次添加时 在链表上用next进行连接 但实际有自己的指向通过before与after将前后新增顺序连接起来构成有序的
         * public V put(K key, V value) {
         *         return putVal(hash(key), key, value, false, true);
         *     }
         *
         *  //
         *  Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
         *         LinkedHashMap.Entry<K,V> p =
         *             new LinkedHashMap.Entry<K,V>(hash, key, value, e);
         *         linkNodeLast(p);
         *         return p;
         *     }
         * //LinkedHashMap的静态内部类 继承关系在类中完成
         *  static class Entry<K,V> extends HashMap.Node<K,V> {
         *         Entry<K,V> before, after;
         *         Entry(int hash, K key, V value, Node<K,V> next) {
         *             super(hash, key, value, next);
         *         }
         *     }
         *
         * //每次新增后 在此将新的LinkedHashMap.Entry<K,V>类型的节点 连接起来
         * head是首个节点  tail是尾节点
         * private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
         * //之前的最后一个节点 tail
         *         LinkedHashMap.Entry<K,V> last = tail;
         * //将新增的节点作为最后一个节点
         *         tail = p;
         *         if (last == null)
         *             head = p;
         *         else {
         * //此处将节点之前连接起来
         *             p.before = last;
         *             last.after = p;
         *         }
         *     }
         *
         */

    }
}
