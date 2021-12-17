package com.jy.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-13 16:13
 */
public class LinkedList_ {
    public static void main(String[] args) {

        /**
         * linkedList底层是双向链表 以prev|element|next 将各个node节点连接起来 有序的
         * 1.创建linkedList时 调用空参构造器 linkedList底层时链表结构 初始化linkedList内部属性的值
          transient int size = 0; //size=0


          transient Node<E> first;//first=null


          transient Node<E> last;//last=null

           public LinkedList() {
              }
         *
         * 2.第一次往集合添加元素时，将元素作为最后一个元素来存储
          void linkLast(E e) {
                //获取添加前的链表最后的node
                 final Node<E> l = last;
                //将当前元素与最后的node连接一起
                 final Node<E> newNode = new Node<>(l, e, null);
                 last = newNode;
                //第一次添加元素时走if语句将新增node作为第一个元素
                 if (l == null)
                     first = newNode;
                 else
                     l.next = newNode;
                 size++;
                 modCount++;
             }

             private static class Node<E> {
             E item;
             Node<E> next;
             Node<E> prev;

            //此时prev是当前链表最后的一个元素 e是当前添加元素 next是null
             Node(Node<E> prev, E element, Node<E> next) {
             this.item = element;
             this.next = next;
             this.prev = prev;
             }
             }
         *
         * 3.第二次之后的添加
          void linkLast(E e) {
                    //获取添加前的链表最后的node 此时last是第一个元素
                    final Node<E> l = last;
                    //将当前元素与最后的node连接一起 即 新node的 prev=last
                    final Node<E> newNode = new Node<>(l, e, null);
                    last = newNode;
                    //第二次添加元素时走else语句将链表连接起来
                    if (l == null)
                    first = newNode;
                    else
                    l.next = newNode;
                    size++;
                    modCount++;
               }
         */

         LinkedList linkedList = new LinkedList();
         linkedList.add("101");
         linkedList.add("102");

        /**
         * 新增首节点
            public void addFirst(E e) {
                linkFirst(e);
            }
             private void linkFirst(E e) {
         //取当前链表的首个元素
                final Node<E> f = first;
         //将新增元素组装在首元素之前 即新增node的next=first
                final Node<E> newNode = new Node<>(null, e, f);
                first = newNode;
         //此时f不为空即走else语句 之前的首节点node的prev = newNode
                if (f == null)
                last = newNode;
                else
                f.prev = newNode;
                size++;
                modCount++;
            }

         */
        linkedList.addFirst("100");

        /**
         * 新增尾节点
          此情况与add方法情况一致 即新增元素追加到最后位置 然后将lastNode的next指向新Node ，新Node的prev 指向lastNode
          public void addLast(E e) {
                  linkLast(e);
              }
          void linkLast(E e) {
                  final Node<E> l = last;
                  final Node<E> newNode = new Node<>(l, e, null);
                  last = newNode;
                  if (l == null)
                      first = newNode;
                  else
                      l.next = newNode;
                  size++;
                  modCount++;
              }
         */
        linkedList.addLast("103");

        /**
         * 指定下标元素添加
         public void add(int index, E element) {
         //校验当前下标是否越界
            checkPositionIndex(index);
        //判断当前下标是否为最后一位 如果是就按照新增来插入元素 否则走else
            if (index == size)
            linkLast(element);
            else
            linkBefore(element, node(index));
         }

         //查找当前下标位置之前的node节点
         Node<E> node(int index) {
            // assert isElementIndex(index);

         //此时用中间法查找 判断当前index靠近链表前方还是靠近后方 size >> 1 即将size的二进制值右移一位
            if (index < (size >> 1)) {
         //此时靠近前方，遍历前半段链表取出指定index之前的一个node节点的next返回 当前的next就是插入index位置之前的元素
                Node<E> x = first;
                for (int i = 0; i < index; i++)
                x = x.next;
                return x;
                } else {
         //此时靠近后方，遍历后半段链表 取出指定index之后的node节点的prev返回 当前的prev就是插入index位置之前的元素
                Node<E> x = last;
                for (int i = size - 1; i > index; i--)
                x = x.prev;
                return x;
            }
         }

         //在原index位置插入元素
         void linkBefore(E e, Node<E> succ) {
            // assert succ != null;
         //取出原index位置的prev即前一个node
            final Node<E> pred = succ.prev;
         //将新插入的index位置的元素与前一个node和原index位置连接 即 newNode:prev=原index.prev,element=e,next=原index
            final Node<E> newNode = new Node<>(pred, e, succ);
         //原index之前位置的node.prev修改为插入的node
            succ.prev = newNode;
            if (pred == null)
            first = newNode;
            else
            pred.next = newNode;
            size++;
            modCount++;
         }
         */
        linkedList.add(2,"111");

        System.out.println("新增后linkedList = " + linkedList);

        //清除元素
        linkedList.clear();

        /**
         *
         * 直接添加整个collection
         * 此时的index就是当前数组已有元素的长度即从当前长度后开始将元素新增进去
         public boolean addAll(int index, Collection<? extends E> c) {
                 checkPositionIndex(index);

                 Object[] a = c.toArray();
                 int numNew = a.length;
                 if (numNew == 0)
                     return false;

         //定义两个Node节点对象
                 Node<E> pred, succ;
         //若原集合有元素 将元素的最后一个Node赋值给pred
                 if (index == size) {
                     succ = null;
                     pred = last;
                 } else {
                     succ = node(index);
                     pred = succ.prev;
                 }

         //将pred与当前新增集合组装成一个链表
                 for (Object o : a) {
                     @SuppressWarnings("unchecked") E e = (E) o;
                     Node<E> newNode = new Node<>(pred, e, null);
                     if (pred == null)
                         first = newNode;
                     else
                         pred.next = newNode;
                     pred = newNode;
                 }
         //此时走if将当前链表最后一个Node赋值给last
                 if (succ == null) {
                     last = pred;
                 } else {
                     pred.next = succ;
                     succ.prev = pred;
                 }

                 size += numNew;
                 modCount++;
                 return true;
             }
         */
        linkedList.add("123");
        ArrayList arrayList = new ArrayList();
        arrayList.add("789");
        arrayList.add("789");
        arrayList.add("788");
        linkedList.addAll(arrayList);
        System.out.println("addCollection = " + linkedList);
        /**
         *
         * 指定下标添加整个collection
         * 此时的index是指定的index值
         public boolean addAll(int index, Collection<? extends E> c) {
         //校验下标是否越界
            checkPositionIndex(index);

            Object[] a = c.toArray();
            int numNew = a.length;
            if (numNew == 0)
            return false;

            //定义两个Node节点对象
            Node<E> pred, succ;
            //若原集合有元素且指定下标与size大小相同 将元素的最后一个Node赋值给pred
            if (index == size) {
            succ = null;
            pred = last;
            } else {
         //指定下标与size大小不同 即除最后的某个位置插入 succ=当前指定下标的元素的原node节点 prev=原node节点的prev即前个节点。
         //此时即将把新的集合插在定义的两个node节点之间
            succ = node(index);
            pred = succ.prev;
         }

            //将pred与当前新增集合组装成一个链表
         for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
            first = newNode;
            else
            pred.next = newNode;
            pred = newNode;
        }
        }
         //此时走else将for循环后组装的链表的最后一个node.next指向自定义的succ(此时的succ就是指向原来index位置的Node节点的引用对象)
         //将succ.prev指向pred 即for循环后的最后一个node
         if (succ == null) {
            last = pred;
            } else {
            pred.next = succ;
            succ.prev = pred;
            }

            size += numNew;
            modCount++;
            return true;
         }
         */
        arrayList.clear();
        arrayList.add("001");
        arrayList.add("002");
        arrayList.add("002");
        linkedList.addAll(2,arrayList);
        System.out.println("addAllCollection = " + linkedList);

        /**
         * 移除指定元素
         * public boolean remove(Object o) {
         *         if (o == null) {
         *             for (Node<E> x = first; x != null; x = x.next) {
         *                 if (x.item == null) {
         *                     unlink(x);
         *                     return true;
         *                 }
         *             }
         *         } else {
         *         //从first开始遍历Node 如果Node.item = 指定元素 调用unlink 将该node从链表移除
         *             for (Node<E> x = first; x != null; x = x.next) {
         *                 if (o.equals(x.item)) {
         *                     unlink(x);
         *                     return true;
         *                 }
         *             }
         *         }
         *         return false;
         *     }
         *
         *     E unlink(Node<E> x) {
         *         // assert x != null;
         *         final E element = x.item;
         *         final Node<E> next = x.next;
         *         final Node<E> prev = x.prev;
         *      //校验如果当前x.prev=null则证明此节点为第一个元素 则将此节点的下个Node作为首节点
         *      //否则将prev的next指定x的next
         *         if (prev == null) {
         *             first = next;
         *         } else {
         *             prev.next = next;
         *             x.prev = null;
         *         }
         *      //校验如果当前节点x.next=null则证明此节点为最后一个元素， 将last指向prev
         *         if (next == null) {
         *             last = prev;
         *         } else {
         *             next.prev = prev;
         *             x.next = null;
         *         }
         *
         *         x.item = null;
         *         size--;
         *         modCount++;
         *         return element;
         *     }
         */
        linkedList.remove("888");
        System.out.println("remove指定元素 = " + linkedList);

        /**
         * 移除第一个Node
         */
        linkedList.removeFirst();
        System.out.println("removeFirst = " + linkedList);
        //移除指定元素第一次出现的Node 底层调用remove(o)方法 从前往后遍历查找第一个符合条件的元素
        linkedList.removeFirstOccurrence("002");
        System.out.println("removeFirstOccurrence = " + linkedList);

        //移除最后一个Node
        linkedList.removeLast();
        System.out.println("removeLast = " + linkedList);

        //移除指定元素最后一次出现的Node 底层逻辑与remove(o)的遍历方式相反 从后往前遍历查找第一个符合条件的元素
        linkedList.removeLastOccurrence("002");
        System.out.println("removeLastOccurrence = " + linkedList);

        //更新指定下标的值
        linkedList.set(2,"999");

        //查询指定下标的值
        System.out.println("linkedList.get(2) = " + linkedList.get(2));


    }
}
