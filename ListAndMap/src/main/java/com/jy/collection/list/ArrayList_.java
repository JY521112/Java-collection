package com.jy.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-10 11:41
 */
@SuppressWarnings("all")
public class ArrayList_ {
    public static void main(String[] args) {
        /**
         * arrayList 是线程不安全的 扩容时 大小为原来的1.5倍
         * 1.初始化Arraylist 底层默认调用空参构造器赋值空的数组 this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
         * 2.第一次添加元素时,
         * 源码如下：
            private static int calculateCapacity(Object[] elementData, int minCapacity) {
            //第一次添加元素时 elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA,minCapacity =0
                return DEFAULT_CAPACITY= 10
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            return minCapacity;
            }
            //第一次扩容 minCapacity=10
            private void grow(int minCapacity) {
                   int oldCapacity = elementData.length;
                   int newCapacity = oldCapacity + (oldCapacity >> 1);
                   if (newCapacity - minCapacity < 0)
                       newCapacity = minCapacity;
                   if (newCapacity - MAX_ARRAY_SIZE > 0)
                       newCapacity = hugeCapacity(minCapacity);
            // 创建一个大小为新大小的数组并将原数组数据拷贝进新数组返回
                   elementData = Arrays.copyOf(elementData, newCapacity);
               }
            扩容完成后将该元素放在下表为0的位置  modCount++ Size++，
         * 3.第二次添加元素时 由于还未达到临界值 直接将元素放在对应下标为1的位置 modCount++ Size++，
         * ...
         * 4.第11次添加时，此时元素个数已经超过原数组的长度此时会触发扩容
         * //第二次扩容 minCapacity=10
            private void grow(int minCapacity) {
            int oldCapacity = elementData.length;
         // oldCapacity >> 1 即 oldCapacity值 二进制往右移一位 即此时扩容为原先的1.5倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
            if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
            // 创建一个大小为新大小的数组并将原数组数据拷贝进新数组返回
            elementData = Arrays.copyOf(elementData, newCapacity);
            }

         */
        List list = new ArrayList();
        //新增
        list.add("101");
        list.add("102");
        list.add("103");
        list.add("104");
        list.add("105");
        list.add("106");
        list.add("107");
        list.add("108");
        list.add("109");
        list.add("110");
        //开始扩容
        list.add("111");
        list.add("112");
        list.add("113");

        //查询 通过下标来查询
        System.out.println("listGet ="+list.get(0));

        //查询是否包含某元素 返回Boolean值
        boolean contains = list.contains("111");
        System.out.println("contains = " + contains);

        //查询是否包含某个列表集合里的元素 返回Boolean 如果包含目标集合全部元素则返回true 否则返回false
        ArrayList arrayList = new ArrayList();
        arrayList.add("112");
        arrayList.add("113");
        boolean b = list.containsAll(arrayList);
        System.out.println("containsAll = " + b);

        //修改 修改指定下标下的值
        list.set(0,"888");
        System.out.println("修改后的list ="+list);

        //删除
        // 方式1：指定下标删除元素  返回删除的元素值
        Object o = list.remove(11);
        System.out.println("remove_index = " + o);

        //方式2：删除指定的值 返回Boolean值
        boolean remove = list.remove("113");
        System.out.println("remove_value = " + remove);

        //清空集合
        list.clear();
        System.out.println("清空后的list = " + list);


    }
}
