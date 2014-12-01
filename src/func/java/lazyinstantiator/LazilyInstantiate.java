package func.java.lazyinstantiator;

import java.util.function.Supplier;

/**
 * {@code LazilyInstantiate} is a quick class to make lazily instantiating
 * objects easy.  All you need to know for working with this class is its two
 * public methods: one static factory for creating the object, and another for
 * initiating the instantiation and retrieval of the object.</p>
 * <p>
 * Also, another benefit is that it's thread safe, but only blocks when initially
 * instantiating the object. After that, it stops blocking and removes unnecessary
 * checks for whether the object is instantiated.</p>
 * <p>
 * Here's an example of it being used for implementing a singleton:
 * <pre><code>public class Singleton
 * {
 *    private static Supplier&lt;Singleton&gt; instance = LazilyInstantiate.using(() -> new Singleton());
 *    //other fields
 * 
 *    public static getInstance()
 *    {
 *       instance.get();
 *    }
 * 
 *    //other methods
 * 
 *    private Singleton()
 *    {
 *       //constructor stuff
 *    }
 * }
 * </code></pre>
 * </p>
 * So, here are the changes you'll need to apply in your code:
 * <ul>
 * <li>Change the type of the lazily instantiated object to a {@code Supplier} 
 * of that type</i>
 * <li>Have it set to LazilyInstantiate.using() where the argument is 
 * {@code () -> <instantiation code>} You could also use a method reference,
 * which, for the example above, would be {@code Singleton::new} instead of
 * {@code () -> new Singleton()}</li>
 * <li>Whatever asks for the object, asks for the {@code Supplier} object, 
 * then {@code .get()}</li>
 * </ul>
 * @param <T> the type of object that you're trying to lazily instantiate
 */
public class LazilyInstantiate<T> implements Supplier<T>
{
	public static <T> LazilyInstantiate<T> using(Supplier<T> supplier)
	{
		return new LazilyInstantiate<>(supplier);
	}
		
	/**
	 * If the object isn't yet instantiated, it instantiates it.  Then it returns
	 * the object.
	 * 
	 * @return the instantiated object
	 */
	@Override
	public T get()
	{
		return current.get();
	}
	
	private LazilyInstantiate(Supplier<T> supplier)
	{
		this.supplier = supplier;
		this.current = () -> swapper();
	}
	
	private final Supplier<T> supplier;
	private Supplier<T> current;
	
	//swaps the itself out for a supplier of an instantiated object
	private synchronized T swapper()
	{
		if(!Factory.class.isInstance(current))
		{
			T obj = supplier.get();
			current = new Factory<T>(obj);
		}
		return current.get();
	}
	
}

class Factory<T> implements Supplier<T> 
{
	Factory(T obj)
	{
		this.obj = obj;
	}
	
	public T get()
	{
		return obj;
	}
	
	private T obj;
}