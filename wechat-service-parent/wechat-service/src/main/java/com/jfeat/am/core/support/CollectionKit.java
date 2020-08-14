package com.jfeat.am.core.support;

import java.util.HashSet;

public class CollectionKit{

    private CollectionKit() {
    }

    public static <T> HashSet<T> newHashSet() {
        return new HashSet();
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        HashSet<T> set = new HashSet();
        Object[] var2 = ts;
        int var3 = ts.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            T t = (T) var2[var4];
            set.add(t);
        }

        return set;
    }
}