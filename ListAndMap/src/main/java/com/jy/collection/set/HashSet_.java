package com.jy.collection.set;

import java.util.HashSet;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-14 14:59
 */
public class HashSet_ {
    public static void main(String[] args) {
        /**
         * HashSet集合存储数据的结构为哈希表,哈希表即为底层采用数组+链表实现
         * HashSet底层不是单纯的简单数组 是可以存放node节点的数组 当前node可以指向下个node
         * (1).HashSet底层是HashMap 存储是无序的
         * (2).添加一个元素时，先得到hash值->转换成索引值
         * (3).找到存储数据表table,看这个索引位置是否已经窜官方的有元素，如果没有则直接加入。如果有，调用equals比较，如果相同，则放弃添加，如果不相同则添加到最后
         * (4).在JAVA8中，如果一条链表的元素个数到达TREEIFY_THRESHOLD(默认是8),并且table的大小>=MIN_TREEIFY_CAPACITY(默认64),就会进行树化(红黑树)
         * 1.创建hashSet() 底层是hashMap 创建一个空的hashMap,loadFactor负载因子为 0.75
         * public HashSet() {
         *         map = new HashMap<>();
         *     }
         * 2.往hashSet中添加第一个元素 底层为hashMap并将元素作为map的key, 静态对象PRESENT作为value
         * 源码如下
         * public boolean add(E e) {
         *         return map.put(e, PRESENT)==null;
         *     }
         *
         * public V put(K key, V value) {
         *         return putVal(hash(key), key, value, false, true);
         *     }
         *
         *
         * 算法计算hash值 根据hash值确定当前元素存放在数组的哪个位置 此时的hash值是经过处理的 并不是简单的hashCode
         * static final int hash(Object key) {
         *         int h;
         *         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
         *     }
         *
         *
         * 赋值：
         * final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
         *                    boolean evict) {
         *         Node<K,V>[] tab; Node<K,V> p; int n, i; //此时的table是hashMap的一个属性 是一个Node[]
         * //第一次新增元素 触发扩容
         *         if ((tab = table) == null || (n = tab.length) == 0)
         *             n = (tab = resize()).length;
         * //每次新增时判断当前table下对应hash位置坐标下有没有元素 如果没有 将此元素放在此位置 最后参数null 为下个预留节点
         *         if ((p = tab[i = (n - 1) & hash]) == null)
         *             tab[i] = newNode(hash, key, value, null);‘
         * //当已经有元素时 走else
         *         else {
         * //此语句在if的时候已经进行赋值将p指向当前hash位置的元素(p = tab[i = (n - 1) & hash])
         *             Node<K,V> e; K k;
         * //如果当前索引位置对应链表的第一次元素与准备添加的key的hash值保持一致
         * //并且满足后面两个条件：1.准备加入的key和p指向的node节点的key是同一个对象。2.p指向的node节点的key的equals和准备加入的key比较后相同则不能加入
         *             if (p.hash == hash && //
         *                 ((k = p.key) == key || (key != null && key.equals(k))))
         *                 e = p;
         * //再判断p是不是一颗红黑树 如果是红黑树则调用putTreeVal来进行添加
         *             else if (p instanceof TreeNode)
         *                 e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
         *             else {
         * //此时当前table节点可能是个链表 所以进行for循环 比较链表元素与添加元素是否相同，如果相同，添加失败直接break，不同则加在链表之后
         *                 for (int binCount = 0; ; ++binCount) {
         *                     if ((e = p.next) == null) {
         *                     //加在链表之后
         *                         p.next = newNode(hash, key, value, null);
         *                    //此时如果当前链表已经满足超过8个 则进行树化。进入treeeifyBin方法会发现 不会首先树化 如果当前 table大小<64则先扩容
         *                         if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
         *                             treeifyBin(tab, hash);
         *                         break;
         *                     }
         *                     if (e.hash == hash &&
         *                         ((k = e.key) == key || (key != null && key.equals(k))))
         *                         break;
         *                     p = e;
         *                 }
         *             }
         *             if (e != null) { // existing mapping for key
         *                 V oldValue = e.value;
         *                 if (!onlyIfAbsent || oldValue == null)
         *                     e.value = value;
         *                 afterNodeAccess(e);
         *                 return oldValue;
         *             }
         *         }
         *         ++modCount;
         *         if (++size > threshold)
         *             resize();
         *         //此方法是为让hashMap的子类去实现自己的方法
         *         afterNodeInsertion(evict);
         *         return null;
         *     }
         *
         *
         * 扩容：每次到达阈值  会扩容为原来的2倍
         * final Node<K,V>[] resize() {
         * //第一次新增元素时，table=null即oldTab=null 此时oldCap=0 oldThr=0.
         *         Node<K,V>[] oldTab = table;
         *         int oldCap = (oldTab == null) ? 0 : oldTab.length;
         *         int oldThr = threshold;
         *         int newCap, newThr = 0;
         *         if (oldCap > 0) {
         *             if (oldCap >= MAXIMUM_CAPACITY) {
         *                 threshold = Integer.MAX_VALUE;
         *                 return oldTab;
         *             }
         *             else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
         *                      oldCap >= DEFAULT_INITIAL_CAPACITY)
         *                 newThr = oldThr << 1; // double threshold
         *         }
         *         else if (oldThr > 0) // initial capacity was placed in threshold
         *             newCap = oldThr;
         *         else {               // zero initial threshold signifies using defaults
         * //第一次新增元素时，走此方法 赋予默认值16的容量，以及 12=16*0.75 的阈值(当内部元素数量超过阈值触发扩容)
         *             newCap = DEFAULT_INITIAL_CAPACITY;
         *             newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
         *         }
         *         if (newThr == 0) {
         *             float ft = (float)newCap * loadFactor;
         *             newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
         *                       (int)ft : Integer.MAX_VALUE);
         *         }
         *         threshold = newThr;
         *
         *
         * //创建新的Node[]长度为16的数组
         *         @SuppressWarnings({"rawtypes","unchecked"})
         *         Node<K, V>[] newTab = (Node<K,V>[])new Node[newCap];
         *         table = newTab;
         *         if (oldTab != null) {
         *             for (int j = 0; j < oldCap; ++j) {
         *                 Node<K,V> e;
         *                 if ((e = oldTab[j]) != null) {
         *                     oldTab[j] = null;
         *                     if (e.next == null)
         *                         newTab[e.hash & (newCap - 1)] = e;
         *                     else if (e instanceof TreeNode)
         *                         ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
         *                     else { // preserve order
         *                         Node<K,V> loHead = null, loTail = null;
         *                         Node<K,V> hiHead = null, hiTail = null;
         *                         Node<K,V> next;
         *                         do {
         *                             next = e.next;
         *                             if ((e.hash & oldCap) == 0) {
         *                                 if (loTail == null)
         *                                     loHead = e;
         *                                 else
         *                                     loTail.next = e;
         *                                 loTail = e;
         *                             }
         *                             else {
         *                                 if (hiTail == null)
         *                                     hiHead = e;
         *                                 else
         *                                     hiTail.next = e;
         *                                 hiTail = e;
         *                             }
         *                         } while ((e = next) != null);
         *                         if (loTail != null) {
         *                             loTail.next = null;
         *                             newTab[j] = loHead;
         *                         }
         *                         if (hiTail != null) {
         *                             hiTail.next = null;
         *                             newTab[j + oldCap] = hiHead;
         *                         }
         *                     }
         *                 }
         *             }
         *         }
         *         return newTab;
         *     }
         */
        HashSet hashSet = new HashSet();
        hashSet.add("123");
        hashSet.add("456");
        hashSet.add("123");
        hashSet.add("888");
        hashSet.add("999");
        hashSet.add("777");
        System.out.println("hashSet = " + hashSet);
        hashSet.clear();

        /**
         * 模拟扩容树化
         * 由于重写了A_的hashCode 所以在添加时 都会添加在同一位置的链表中 此时可模拟链表
         * 当同一位置的链表数量达到8个 会首先触发扩容 将table扩容为原来的2倍 等到table大小达到64时 进行树化 由Node->TreeNode
         * 注意：只要往set中添加12个元素 无论是同一数组位置链表中还是每个数组 只要总数超过12个元素就会扩容 而不只是数组位置超过12个
         */
        for (int i = 0; i < 20; i++) {
            hashSet.add(new A_(i));
        }
    }

    static class A_{
        int a;

        public A_(int a) {
            this.a = a;
        }

        //重写hashCode此时所有对象的hashCode值相同
        @Override
        public int hashCode() {
            return 100;
        }

        @Override
        public String toString() {
            return "A_{" +
                    "a=" + a +
                    '}';
        }
    }
}
