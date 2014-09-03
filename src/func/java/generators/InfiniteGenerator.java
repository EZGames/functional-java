package func.java.generators;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import func.java.collections.BuiltCollection;

public class InfiniteGenerator<T> implements Generator<T>, BuiltCollection<T>
{
	public void yield(T obj)
	{
		yield(() -> obj);
	}
	
	public void yield(Supplier<T> obj)
	{
		this.next = obj;
	}
	
	public void yield(Function<Generator<T>, T> obj)
	{
		yield(() -> obj.apply(this));
	}
	
	private Supplier<T> next = null;
	
	public BuiltCollection<T> generate()
	{
		return this;
	}
	
	public Iterator<T> iterator()
	{
		return new InfiniteIterator<>(this);
	}
	
	/**
	 * NOTE: do not call the {@code parallel} method on the returned stream.
	 * There are no guarantees that it will work.
	 * @return
	 */
	public Stream<T> stream()
	{
		Spliterator<T> spliter = Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
		return StreamSupport.stream(spliter, false);
	}
	
	static class InfiniteIterator<T> implements Iterator<T>
	{
		InfiniteGenerator<T> gener;
		
		public InfiniteIterator(InfiniteGenerator<T> gener)
		{
			this.gener = gener;
		}
		
		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public T next()
		{
			return gener.next.get();
		}		
	}
}