package func.java.collections;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class LazyIterator<T> implements Iterator<T>
{	
	//***************************************************************************
	//Public constructors
	//***************************************************************************
	public LazyIterator(LazyIterable<T> iterable)
	{
		this.iterable = iterable;
		this.next = iterable.initialValue;
	}
	
	public LazyIterator(UnaryOperator<T> generator, Supplier<T> initialValue)
	{
		this(new LazyIterable<>(generator, initialValue));
	}
	
	//***************************************************************************
	//Public API methods
	//***************************************************************************
	@Override
	public boolean hasNext()
	{
		try
		{
			return next.get() != null;
		}
		catch(NullPointerException ex)
		{
			return false;
		}
	}
	
	@Override
	public T next()
	{
		T result = next.get();
		next = () -> iterable.generator.apply(result);
		return result;
	}
	
	/**
	 * Returns an iterable containing the <i>next</i> {@code n} values from this
	 * iterator (without moving this iterator forward).
	 * @param n
	 * @return
	 */
	public Iterable<T> limit(int n)
	{
		return new LimitedLazyIterator<>(this, n);
	}
	
	/**
	 * After calling this, the next call to {@code hasNext()} will return 
	 * {@code false} and {@code next()} will return {@code null}.
	 */
	public void stop()
	{
		next = () -> null;
	}
	
	/**
	 * Skip the "head" of this ahead {@code n} places
	 * @param n - the number of 
	 */
	public void skipAhead(int n)
	{
		next = iterable.skipSupplierForwardNTimes(next, n);
	}

	//***************************************************************************
	// Private fields
	//***************************************************************************
	private Supplier<T> next;
	private final LazyIterable<T> iterable;
	
	//***************************************************************************
	// Private class for implementing limit()
	//***************************************************************************
	private static class LimitedLazyIterator<T> implements Iterator<T>, Iterable<T>
	{
		public LimitedLazyIterator(LazyIterator<T> original, int limit)
		{
			this.base = new LazyIterator<>(original.iterable.generator, original.next);
			this.limit = limit;
		}
		
		@Override
		public Iterator<T> iterator()
		{
			return this;
		}
		
		@Override
		public boolean hasNext()
		{
			if(current >= limit)
				return false;
			
			current++;
			return base.hasNext();
		}
		
		@Override
		public T next()
		{
			return base.next();
		}
		
		private final LazyIterator<T> base;
		private final int limit;
		private int current = 0;
	}
}

