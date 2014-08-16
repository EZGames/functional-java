package func.java.recursion;
import java.util.*;

public class jMemoizer<T, R>
{
   private Map<T, R> store = new HashMap<>();
   
   public R computeIfNotExists(T input, Function<T, R> computer)
   {
	  if(store.containsKey(input)
		 return store.get(input);
      
      else
		 store.put(input, computer.apply(input));
   }
}
