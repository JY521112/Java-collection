package com.jy.collection.map;

import java.util.HashMap;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-15 15:56
 */
public class HashMap_ {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("123","张三");
        hashMap.put("456","李四");
        hashMap.put("123","王二");
        System.out.println("hashMap = " + hashMap);
        /**
         * HashMap 底层结构在jdk7之前是【数组+链表】，jdk8之后是【数组+链表+红黑树】 底层存储节点类型为HashMap$Node
         * 扩容机制与hashSet一致
         * 当变成红黑树之后 如果减去元素至不符合树的条件时 会“剪枝” 即退化成链表
         * HashMap是线程不安全的 键值可以为null
         * final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
         *                     boolean evict) {
         *          Node<K,V>[] tab; Node<K,V> p; int n, i; //此时的table是hashMap的一个属性 是一个Node[]
         *  //第一次新增元素 触发扩容
         *          if ((tab = table) == null || (n = tab.length) == 0)
         *              n = (tab = resize()).length;
         *  //每次新增时判断当前table下对应hash位置坐标下有没有元素 如果没有 将此元素放在此位置 最后参数null 为下个预留节点
         *          if ((p = tab[i = (n - 1) & hash]) == null)
         *              tab[i] = newNode(hash, key, value, null);‘
         *  //当已经有元素时 走else
         *          else {
         *  //此语句在if的时候已经进行赋值将p指向当前hash位置的元素(p = tab[i = (n - 1) & hash])
         *              Node<K,V> e; K k;
         *  //如果当前索引位置对应链表的第一次元素与准备添加的key的hash值保持一致
         *  //并且满足后面两个条件：1.准备加入的key和p指向的node节点的key是同一个对象。2.p指向的node节点的key的equals和准备加入的key比较后相同则不能加入
         *              if (p.hash == hash && //
         *                  ((k = p.key) == key || (key != null && key.equals(k))))
         *                  e = p;
         *  //再判断p是不是一颗红黑树 如果是红黑树则调用putTreeVal来进行添加 按照红黑树的方式来处理
         *              else if (p instanceof TreeNode)
         *                  e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
         *              else {
         *  //此时当前table节点可能是个链表 所以进行for循环 比较链表元素与添加元素是否相同，如果相同，添加失败直接break，不同则加在链表之后
         *                  for (int binCount = 0; ; ++binCount) {//死循环 至元素添加进去或有相同元素 break
         *                      if ((e = p.next) == null) {
         *  //加在链表之后
         *                          p.next = newNode(hash, key, value, null);
         * //此时如果当前链表已经满足超过8个 则进行树化。进入treeeifyBin方法会发现 不会首先树化 如果当前 table大小<64则先扩容
         *                          if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
         *                              treeifyBin(tab, hash);
         *                          break;
         *                      }
         *                      if (e.hash == hash &&
         *                          ((k = e.key) == key || (key != null && key.equals(k))))
         *                          break;
         *                      p = e;
         *                  }
         *              }
         *              if (e != null) { // existing mapping for key
         *    //如果当前节点已经存在元素 且key相同则直接替换新值
         *                  V oldValue = e.value;
         *                  if (!onlyIfAbsent || oldValue == null)
         *                      e.value = value;
         *                  afterNodeAccess(e);
         *                  return oldValue;
         *              }
         *          }
         *          ++modCount; //每增加一个就+1
         *          if (++size > threshold)//如果size大于了临界值 就扩容
         *              resize();
         *          //此方法是为让hashMap的子类去实现自己的方法
         *          afterNodeInsertion(evict);
         *          return null;
         *      }
         *
         *
         *  扩容：每次到达阈值  会扩容为原来的2倍
         *  final Node<K,V>[] resize() {
         *  //第一次新增元素时，table=null即oldTab=null 此时oldCap=0 oldThr=0.
         *          Node<K,V>[] oldTab = table;
         *          int oldCap = (oldTab == null) ? 0 : oldTab.length;
         *          int oldThr = threshold;
         *          int newCap, newThr = 0;
         *          if (oldCap > 0) {
         *              if (oldCap >= MAXIMUM_CAPACITY) {
         *                  threshold = Integer.MAX_VALUE;
         *                  return oldTab;
         *              }
         *              else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
         *                       oldCap >= DEFAULT_INITIAL_CAPACITY)
         *                  newThr = oldThr << 1; // double threshold
         *          }
         *          else if (oldThr > 0) // initial capacity was placed in threshold
         *              newCap = oldThr;
         *          else {               // zero initial threshold signifies using defaults
         *  //第一次新增元素时，走此方法 赋予默认值16的容量，以及 12=16*0.75 的阈值(当内部元素数量超过阈值触发扩容)
         *              newCap = DEFAULT_INITIAL_CAPACITY;
         *              newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
         *          }
         *          if (newThr == 0) {
         *              float ft = (float)newCap * loadFactor;
         *              newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
         *                        (int)ft : Integer.MAX_VALUE);
         *          }
         *          threshold = newThr;
         *
         *
         *  //创建新的Node[]长度为16的数组
         *          @SuppressWarnings({"rawtypes","unchecked"})
         *          Node<K, V>[] newTab = (Node<K,V>[])new Node[newCap];
         *          table = newTab;
         *          if (oldTab != null) {
         *              for (int j = 0; j < oldCap; ++j) {
         *                  Node<K,V> e;
         *                  if ((e = oldTab[j]) != null) {
         *                      oldTab[j] = null;
         *                      if (e.next == null)
         *                          newTab[e.hash & (newCap - 1)] = e;
         *                      else if (e instanceof TreeNode)
         *                          ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
         *                      else { // preserve order
         *                          Node<K,V> loHead = null, loTail = null;
         *                          Node<K,V> hiHead = null, hiTail = null;
         *                          Node<K,V> next;
         *                          do {
         *                              next = e.next;
         *                              if ((e.hash & oldCap) == 0) {
         *                                  if (loTail == null)
         *                                      loHead = e;
         *                                  else
         *                                      loTail.next = e;
         *                                  loTail = e;
         *                              }
         *                              else {
         *                                  if (hiTail == null)
         *                                      hiHead = e;
         *                                  else
         *                                      hiTail.next = e;
         *                                  hiTail = e;
         *                              }
         *                          } while ((e = next) != null);
         *                          if (loTail != null) {
         *                              loTail.next = null;
         *                              newTab[j] = loHead;
         *                          }
         *                          if (hiTail != null) {
         *                              hiTail.next = null;
         *                              newTab[j + oldCap] = hiHead;
         *                          }
         *                      }
         *                  }
         *              }
         *          }
         *          return newTab;
         *      }
         *
         */
    }
}
