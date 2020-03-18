package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size=0;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            size+=entry.getValue().size();

            }
        return size;
        } //напишите тут ваш код


    @Override
    public V put(K key, V value) {
        if (map.containsKey(key) && map.get(key).size()<repeatCount ) {
            V val =null;
            if(map.get(key).size()!=0){
            val = map.get(key).get(map.get(key).size()-1);}
            map.get(key).add(value);
            return val;
        }
        else if(map.containsKey(key) && map.get(key).size()==repeatCount ){
            V val =null;
            if(map.get(key).size()!=0){
            val = map.get(key).get(map.get(key).size()-1);}
            map.get(key).remove(0);
            map.get(key).add(value);
            return val;
        }
        else { map.put(key,new ArrayList<V>(repeatCount));
                map.get(key).add(value);
                return null;}//напишите тут ваш код
    }

    @Override
    public V remove(Object key) {
       if (!map.containsKey(key)){
           return null;
       }
       else if (map.get(key).size()==0){
           map.remove(key);
            return null;
        }


       else{

           V value =map.get(key).get(0);
           map.get(key).remove(0);
            if (map.get(key).size()==0){
                map.remove(key);
           }
           return value;
       }//напишите тут ваш код//напишите тут ваш код
    }

    @Override
    public Set<K> keySet() {

        return map.keySet();//напишите тут ваш код
    }

    @Override
    public Collection<V> values() {

     ArrayList<V> list =new ArrayList<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {

            for (V v : entry.getValue()) {
               list.add(v) ;//напишите тут ваш код
            }
        }
        return list;
    }
    @Override
    public boolean containsKey(Object key) {
        if(map.containsKey(key)){
            return true;
        }
        return false;//напишите тут ваш код
    }

    @Override
    public boolean containsValue(Object value) {
     if(values().contains(value)){
         return true;
     }
     return false;//напишите тут ваш код
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}