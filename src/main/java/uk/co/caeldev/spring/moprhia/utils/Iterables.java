package uk.co.caeldev.spring.moprhia.utils;

import java.util.Iterator;
import java.util.List;

public class Iterables {

    public static <T> Iterable<T> from(final Iterator<T> source) {
        return () -> source;
    }

    public static <T> Iterable<T> from(final List<T> source) {
        return from(source.iterator());
    }
}
