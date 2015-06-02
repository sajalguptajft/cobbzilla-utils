package org.cobbzilla.util.collection;

import java.lang.reflect.Array;
import java.util.*;

import static org.cobbzilla.util.daemon.ZillaRuntime.empty;

public class ArrayUtil {

    public static final Object[] SINGLE_NULL_OBJECT = new Object[]{null};

    public static <T> T[] append (T[] array, T element) {
        if (array == null || array.length == 0) {
            final T[] newArray = (T[]) Array.newInstance(element.getClass(), 1);
            newArray[0] = element;
            return newArray;
        } else {
            final List<T> updated = new ArrayList<>(array.length);
            Collections.addAll(updated, array);
            updated.add(element);
            return updated.toArray(array);
        }
    }

    public static <T> T[] remove(T[] array, int indexToRemove) {
        if (array == null) throw new NullPointerException("remove: array was null");
        if (indexToRemove >= array.length || indexToRemove < 0) throw new IndexOutOfBoundsException("remove: cannot remove element "+indexToRemove+" from array of length "+array.length);
        final List<T> list = new ArrayList<>(Arrays.asList(array));
        list.remove(indexToRemove);
        final T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length-1);
        return list.toArray(newArray);
    }

    public static <T> T[] slice(T[] array, int from, int to) {
        if (array == null) throw new NullPointerException("slice: array was null");
        final T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), to-from);
        for (int i=from; i<=to; i++) newArray[from-i] = array[i];
        return newArray;
    }

    public static <T> List<T> merge(Collection<T>... collections) {
        if (empty(collections)) return Collections.emptyList();
        final Set<T> result = new HashSet<>();
        for (Collection<T> c : collections) result.addAll(c);
        return new ArrayList<>(result);
    }

}
