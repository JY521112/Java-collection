package com.jy.collection.map;

import java.util.Hashtable;

/**
 * @author JIA.YU
 * @version 1.0.0
 * @date 2021-12-16 14:39
 */
public class HashTable_ {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();

        //验证不会树化
        for (int i = 0; i <10 ; i++) {
            hashtable.put(new B_(i),"123");
            if (i==8||i==7){
                System.out.println("hashtable = " + hashtable);
            }
        }
        System.out.println("hashtable = " + hashtable);
//        hashtable.put("123","789");
//        hashtable.put("789","1222");
//        hashtable.put("123","777");
//        hashtable.put("1","777");
//        hashtable.put("12","777");
//        hashtable.put("13","777");
//        hashtable.put("132","777");
//        hashtable.put("133","777");
//        hashtable.put("144","777");
//        hashtable.put("177","777");
        /**
         * HashTable是线程不安全的 键与值不允许为null
         * 1.底层是数组HashMap$Entry[] 初始化大小为11 threshold为8=11*0.75 达到临界值8进行扩容
         * 2.新增：
         *  private void addEntry(int hash, K key, V value, int index) {
         *         modCount++;
         *
         *         Entry<?,?> tab[] = table;
         *  //当前数组的元素已经超过临界值 则进行扩容并重新机算原元素的存储位置
         *         if (count >= threshold) {
         *             // Rehash the table if the threshold is exceeded
         *             rehash();
         *
         *             tab = table;
         *             hash = key.hashCode();
         *             index = (hash & 0x7FFFFFFF) % tab.length;
         *         }
         *
         *         // Creates the new entry.
         *         @SuppressWarnings("unchecked")
         *         Entry<K, V> e = (Entry<K,V>) tab[index];
         * //新加的元素放在首位
         *         tab[index] = new Entry<>(hash, key, value, e);
         *         count++;
         *     }
         * 扩容。
         *     protected void rehash() {
         *         int oldCapacity = table.length;
         *         Entry<?,?>[] oldMap = table;
         *
         *         // overflow-conscious code
         *  //扩容的新大小为 原大小二进制左移一位并加1
         *         int newCapacity = (oldCapacity << 1) + 1;
         *         if (newCapacity - MAX_ARRAY_SIZE > 0) {
         *             if (oldCapacity == MAX_ARRAY_SIZE)
         *                 // Keep running with MAX_ARRAY_SIZE buckets
         *                 return;
         *             newCapacity = MAX_ARRAY_SIZE;
         *         }
         *   //创建新的大小的entry集合
         *         Entry<?,?>[] newMap = new Entry<?,?>[newCapacity];
         *
         *         modCount++;
         *   //重新计算新的临界值
         *         threshold = (int)Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
         *         table = newMap;
         *   //取原数组大小遍历 将原元素取出重新计算hash 放入到新数组中位置
         *         for (int i = oldCapacity ; i-- > 0 ;) {
         *             for (Entry<K,V> old = (Entry<K,V>)oldMap[i] ; old != null ; ) {
         *                 Entry<K,V> e = old;
         *                 old = old.next;
         *
         *                 int index = (e.hash & 0x7FFFFFFF) % newCapacity;
         *                 //此时数组节点处的链表会从后往前重新组装
         *                 e.next = (Entry<K,V>)newMap[index];
         *                 newMap[index] = e;
         *             }
         *         }
         *     }
         */

    }

    static class B_{
        int a;

        public B_(int a) {
            this.a = a;
        }

        //重写hashCode此时所有对象的hashCode值相同
        @Override
        public int hashCode() {
            return 100;
        }

        @Override
        public String toString() {
            return "B_{" +
                    "a=" + a +
                    '}';
        }
    }
}
