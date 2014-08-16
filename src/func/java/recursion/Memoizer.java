package func.java.recursion;

public class Memoizer
{
   public static <T, R> R callMemoized(final BiFunction<Function<T, R>, T, R> function, final T input)
   {
	  Function<T, R> memoized = new Memoized(function);
	  return memoized.apply(input);
   }
   
   private Memoizer(){} //don't instantiate
}

static class Memoized<T, R> implements Function<T, R>
{
   private final Map<T, R> store = new HashMap<>();
   private final BiFunction<Function<T, R>, T, R> function;
   
   Memoized(BiFunction<Function<T, R>, T, R> function)
   {
	  this.function = function;
   }

   public R apply(final T input)
   {
      return store.computeIfAbsent(input, key -> function.apply(this, key);
   }
}
