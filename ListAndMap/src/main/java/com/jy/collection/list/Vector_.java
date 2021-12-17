package com.jy.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-13 15:05
 */
@SuppressWarnings("all")
public class Vector_ {
    public static void main(String[] args) {
        /**
         * vector 是线程安全的  方法用synchronized修饰 扩容为原来大小的两倍
         * 1.第一步创建vector对象 调用有参构造器赋予初始化值10 的数组此时溢出时增长因子为0
          public Vector() {
                  this(10);
              }
         this.elementData = new Object[initialCapacity];
         * 2.第二步往集合里面添加对象的时候
         * 此时会校验当前数组的大小是否超过最大容量 此时未扩容
         * 。。。
         * 3.当增加到第十一次时 超过数组最大容量 开始扩容 大小为原来的2倍
           private void grow(int minCapacity) {
                  // overflow-conscious code
                  int oldCapacity = elementData.length;
                  // 扩容之后的大小
                  int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                                   capacityIncrement : oldCapacity);
                  if (newCapacity - minCapacity < 0)
                      newCapacity = minCapacity;
                  if (newCapacity - MAX_ARRAY_SIZE > 0)
                      newCapacity = hugeCapacity(minCapacity);
                //将原数组的元素拷贝进新数组里
                  elementData = Arrays.copyOf(elementData, newCapacity);
              }
         *
         *
         *
         */
        List vector = new Vector();
        //此时调用有参构造器 将大小参数传入进去
        //List vector1 = new Vector(20);
        //新增
        vector.add("101");
        vector.add("102");
        vector.add("103");
        vector.add("104");
        vector.add("105");
        vector.add("106");
        vector.add("107");
        vector.add("108");
        vector.add("109");
        vector.add("110");
        //开始扩容
        vector.add("111");
        vector.add("112");
        vector.add("113");

        //查询 通过下标来查询
        System.out.println("vectorGet ="+vector.get(0));

        //查询是否包含某元素 返回Boolean值
        boolean contains = vector.contains("111");
        System.out.println("contains = " + contains);

        //查询是否包含某个列表集合里的元素 返回Boolean 如果包含目标集合全部元素则返回true 否则返回false
        ArrayList arrayList = new ArrayList();
        arrayList.add("112");
        arrayList.add("113");
        boolean b = vector.containsAll(arrayList);
        System.out.println("containsAll = " + b);

        //修改 修改指定下标下的值
        vector.set(0,"888");
        System.out.println("修改后的vector ="+vector);

        //删除
        // 方式1：指定下标删除元素  返回删除的元素值
        Object o = vector.remove(11);
        System.out.println("remove_index = " + o);

        //方式2：删除指定的值 返回Boolean值
        boolean remove = vector.remove("113");
        System.out.println("remove_value = " + remove);

        //清空集合
        vector.clear();
        System.out.println("清空后的vector = " + vector);




    }
}
