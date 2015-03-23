package func.java.generators;

import java.util.Iterator;

public class InfiniteGenerator<T> implements Generator<T>, Iterable<T>, Iterator<T>
{
	public void yield(T obj)
	{
		yield(Supplier.from(() -> obj));
	}
	
	public void yield(java.util.function.Supplier<T> obj)
	{
		this.next = Supplier.from(obj);
	}
	
	public void yield(Supplier<T> obj)
	{
		this.next = obj;
	}
	
	private Supplier<T> next = null;
	private boolean continuing = true;
	
	public Iterable<T> generate()
	{
		return this;
	}
	
	public Iterator<T> iterator()
	{
		return this;
	}
	
	public void stop()
	{
		continuing = false;
	}
	
	@Override
	public boolean hasNext()
	{
		return continuing;
	}

	@Override
	public T next()
	{
		T retVal = next.get();
		next = next.next(retVal);
		return retVal;
	}
	
	@FunctionalInterface
	public interface Supplier<T>
	{
		T get();
		
		default Supplier<T> next(T previous)
		{
			return this;
		}
		
		public static <T> Supplier<T> from(java.util.function.Supplier<T> supplier)
		{
			return () -> supplier.get();
		}
	}
}