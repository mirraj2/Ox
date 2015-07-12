package jasonlib.util;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Iterables.size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Functions {

  public static <A, B> List<B> map(A[] array, Function<A, B> function) {
    return map(Arrays.asList(array), function);
  }

  public static <A, B> List<B> map(Iterable<A> list, Function<A, B> function) {
    checkNotNull(list, "list");
    checkNotNull(function, "function");

    List<B> ret = new ArrayList<B>(size(list));
    for (A element : list) {
      ret.add(function.apply(element));
    }
    return ret;
  }

  public static <T> Consumer<T> noConsumer() {
    return new Consumer<T>() {
      @Override
      public void accept(T t) {
      }
    };
  }

  /**
   * Iterates through both iterables at the same time, calling the consumer for each pair of elements.
   * 
   * This will iterate until either one of the iterables runs out of elements.
   */
  public static <A, B> void splice(Iterable<A> iterA, Iterable<B> iterB, BiConsumer<A, B> consumer) {
    Iterator<A> a = iterA.iterator();
    Iterator<B> b = iterB.iterator();

    while (a.hasNext() && b.hasNext()) {
      consumer.accept(a.next(), b.next());
    }
  }

}