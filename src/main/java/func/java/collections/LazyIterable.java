package func.java.collections;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import func.java.lazyinstantiator.LazilyInstantiate;

/**
 * {@code LazyIterable} is a class for creating generated and/or infinite iterables
 * lazily. You provide a starting value (directly or via {@code Supplier} and a 
 * {@code UnaryOperator} that will transform that value and each successive value
 * to the next value to be iterated over.
 * <p>
 * Being lazy, this and its corresponding iterator do not evaluate any value
 * until it is asked for. It acts as a focused type of generator 
 * ({@link func.java.generators}).</p>
 * <p>
 * 
 *  
 * @param <T> - the type of object returned by this iterable
 */
public class LazyIterable<T> implements Iterable<T>
{
	//***************************************************************************
	// Default variations
	//***************************************************************************
	/**An iterable going up from 0 indefinitely*/
	public static final LazyIterable<Integer> ZERO_BASED_COUNTER = new LazyIterable<>(i -> i + 1, 0);
	/**An iterable going up from 1 indefinitely*/
	public static final LazyIterable<Integer> ONE_BASED_COUNTER = new LazyIterable<>(i -> i + 1, 1);
	/**An iterable going up through even numbers indefinitely, starting at 0.*/
	public static final LazyIterable<Integer> ZERO_BASED_EVENS = new LazyIterable<>(i -> i + 2, 0);
	/**An iterable going up through even numbers indefinitely, starting at 2*/	
	public static final LazyIterable<Integer> TWO_BASED_EVENS = new LazyIterable<>(i -> i + 2, 2);
	/**An iterable going up through odd numbers indefinitely, starting at 1*/
	public static final LazyIterable<Integer> ODDS = new LazyIterable<>(i -> i + 2, 1);
	/**
	 * Returns a lazy iterable (not a {@code LazyIterable}) that, when iterated 
	 * over, will return the values 0 to {@ code exclusiveMax} (exclusive, as the
	 *  name implies).
	 * @param exclusiveMax - the exclusive maximum value of the range of values in
	 * the returned iterable.
	 * @return a lazy iterable with values in order from 0 to the given max, 
	 * exclusively
	 */
	public static Iterable<Integer> range(int exclusiveMax)
	{
		return ZERO_BASED_COUNTER.limit(exclusiveMax);
	}
	
	//***************************************************************************
	// Public constructors
	//***************************************************************************
	public LazyIterable(UnaryOperator<T> generator, T initialValue)
	{
		this(generator, () -> initialValue);
	}
	
	public LazyIterable(UnaryOperator<T> generator, Supplier<T> initialValue)
	{
		this.generator = generator;
		this.initialValue = initialValue;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	/**
	 * Returns an iterator over elements of type T.
	 * <p>
	 * The resulting iterator is likely to be infinite, so avoid using it in a
	 * loop without an escape clause ({@code break} or loops a specified number of
	 * times, which can be set with the returned iterator's {@code limit()} method)
	 * unless you know that it isn't infinite.</p>
	 */
	@Override
	public LazyIterator<T> iterator()
	{
		return new LazyIterator<>(this);
	}
	
	/**
	 * Moves the "head" of the iterable ahead a 1 place
	 * <p><b>Note:</b> if this is not infinite, skipping forward past the "end" 
	 * of this will result in the "head" being null.</p>
	 * @return a new LazyIterable whose "head" is at the new position
	 */
	public LazyIterable<T> skipForward()
	{
		return new LazyIterable<>(generator, next(initialValue));
	}
	
	/**
	 * Moves the "head" of the iterable ahead a number of places equal to numTimes
	 * <p><b>Note:</b> if this is not infinite, skipping forward past the "end" 
	 * of this will result in the "head" being null; 
	 * @param numTimes - The number of places to move the "head" forward. Providing
	 * a number less than 1 will have unexpected results
	 * @return a new LazyIterable whose "head" is at the new position
	 */
	public LazyIterable<T> skipForwardNTimes(int numTimes)
	{
		return new LazyIterable<>(generator, skipSupplierForwardNTimes(initialValue, numTimes));
	}
	
	/**
	 * Returns an iterable with the first {@code n} values from this. Does not
	 * change the "head" position.
	 * <p><b>Note:</b> if this is not infinite and {@code n} is greater than the
	 * number of values that can be generated from this, the size of the returned
	 * iterable will be less than {@code n}</p>
	 * @param n - the maximum number of values to read from this
	 * @return an iterable with the first {@code n} values from this
	 */
	public Iterable<T> limit(int n)
	{
		return new LazyIterator<>(this).limit(n);
	}
	
	//***************************************************************************
	// package-private fields
	//***************************************************************************
	final UnaryOperator<T> generator;
	final Supplier<T> initialValue;
	
	
	//***************************************************************************
	// Private helper methods
	//***************************************************************************
	/* 
	 * Recursive function to get a Supplier that provides a value {numTimes} steps
	 * into this. The value will not be evaluated until the resulting Supplier's
	 * get() is called.
	 */
	Supplier<T> skipSupplierForwardNTimes(Supplier<T> value, int numTimes)
	{
		if(numTimes <= 1)
			//after the value is evaluated the first time, we don't want to have to
			// evaluate it again, since it may be many steps of heavy calculations
			return LazilyInstantiate.using(next(value));
		else
			return skipSupplierForwardNTimes(next(value), numTimes - 1);
	}
	
	/*
	 * Function that returns a Supplier of the value after the one supplied by the
	 * given Supplier. 
	 */
	private Supplier<T> next(Supplier<T> value)
	{
		return () -> generator.apply(value.get());
	}
}