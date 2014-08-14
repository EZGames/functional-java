package func.java.lazyinstantiator;

import java.util.function.Supplier;

public class LazilyInstantiate<T> implements Supplier<T>
{
	public static <T> LazilyInstantiate<T> using(Supplier<T> supplier)
	{
		return new LazilyInstantiate<>(supplier);
	}
	
	public synchronized T get()
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
	
	private T swapper()
	{
		T obj = supplier.get();
		current = () -> obj;
		return obj;
	}
	
}
