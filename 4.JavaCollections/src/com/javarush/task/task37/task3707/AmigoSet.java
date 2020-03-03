package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


public class AmigoSet<E> extends AbstractSet   implements Serializable ,Cloneable , Set {
    private final static Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capcity = (int) Math.max(16, (collection.size() / .75f) + 1);
        map = new HashMap<E, Object>(capcity);
        for (E e : collection
        ) {
            map.put(e, PRESENT);

        }
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {

        return map.containsKey(o);
    }

    @Override
    public Iterator iterator() {

        return map.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        Object obj = new Object();
        obj = map.put((E) o, PRESENT);
        if (obj != null) {
            return false;
        }
        return true;

    }

    @Override
    public boolean remove(Object o) {
        if (map.remove(o) != null)
            return true;
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() {

        try {
            AmigoSet seta = (AmigoSet) super.clone();
            seta.map = (HashMap) map.clone();
            return seta;
        } catch (Exception e) {
            throw new InternalError(e);
        }

    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeObject(map.size());
        for (E e : map.keySet()) {
            s.writeObject(e);
        }
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
    }

    private void readObject(ObjectInputStream s) throws Exception {
        s.defaultReadObject();

        int size = (int) s.readObject();
        Set<E> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set.add((E) s.readObject());
        }
        int capacity = (int) s.readObject();
        float loadFactor = (float) s.readObject();
        map = new HashMap<>(capacity, loadFactor);
        for (E e : set) {
            map.put(e, PRESENT);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Set))
            return false;
        AmigoSet c = (AmigoSet) o;
        if (c.size() != size())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}