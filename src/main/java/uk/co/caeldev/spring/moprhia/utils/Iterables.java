package uk.co.caeldev.spring.moprhia.utils;

import com.google.common.base.Preconditions;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Iterables {

    public static <T> Iterable<T> from(final Iterator<T> source) {
        return new Iterable<T>() {
            private AtomicBoolean exhausted = new AtomicBoolean();
            @Override
            public Iterator<T> iterator() {
                Preconditions.checkState(!exhausted.getAndSet(true));
                return source;
            }
        };
    }

    public static <T> Iterable<T> from(final List<T> source) {
        return from(source.iterator());
    }
}
