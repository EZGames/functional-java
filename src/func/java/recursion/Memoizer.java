package func.java.recursion;
<<<<<<< HEAD
import java.util.*;
import java.util.function.Function;

/**
 * {@code Memoizer} is a caching class used in recursive algorithms that end up
 * repeating the same calculation over and over again.  It can also be used to
 * simply cache the results of an algorithm for a given input, but that's
 * generally a waste.
 * <p>
 * To use {@code Memoizer}, simple create an instance of it with {@code new},
 * then in the recursive function, instead of simply calling the next step of
 * the recursion, you use the memoizer, calling {@code computeIfNotExists()},
 * providing the arguments for the next call as well as a reference to the
 * function being called.</p>
 * <p>
 * For example, using the typical factorial recursive method (not a function that
 * generally needs it, but it's so familiar that you can concentrate on the
 * important stuff and ignore the algorithm):
 * <pre><code>public int factorial(int num)
 * {
 *    if(num <= 1)
 *    {
 *       return 1;
 *    }
 *    return memoizer.computeIfNotExists(num - 1, this::factorial);
 * }
 * </code></pre>
 * This code assumes that the factorial function is part of an class containing a
 * {@code Memoizer} object for the function, called {@code memoizer}.
 * <p>
 * If a recursive function requires more than one parameter, you can use a tuple
 * ({@link func.java.tuples} package) as the parameter type, and then use that to
 * pass the values into the memoizer.</p>  
 * 
 * @param <T> - the input type
 * @param <R> - the return type
 */
// DOC update TEST
public class Memoizer<T, R>
{
   private final Map<T, R> store = new HashMap<>();
   private final Function<T, R> computer;
   
   public Memoizer(Function<T, R> function)
   {
   	computer = function;
   }
   
   /**
    * Takes in the parameters for the next recursive call and checks whether 
    * the result of the parameters has been calculated yet.
    * If it has, it returns the cached value.  If it hasn't, it runs the 
    * function to calculate, store, and returns the value.
    * @param input
    */
   public R computeIfNotExists(T input)
   {
	  if(store.containsKey(input))
		  return store.get(input);
      
      else
      {
      	store.put(input, computer.apply(input));
      	return store.get(input);
      }
   }
}
