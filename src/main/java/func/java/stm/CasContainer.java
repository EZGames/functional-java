package func.java.stm;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * A CAS container is a reusable device that allows for thread-safe, "mutable"
 * updates to an immutable object. It stores an immutable object within that can
 * be swapped out in a thread-safe way, using the compare-and-swap algorithm.
 * @param <I> the immutable type stored within the CAS container
 */
public class CasContainer<I>
{
	private final ReentrantLock lock = new ReentrantLock();
	I state;
	
	/**
	 * Construct a new {@code CasContainer} that stores the given value
	 * @param initialState - the starting value stored in the container
	 */
	public CasContainer(I initialState)
	{
		state = initialState;
	}
	
	/**
	 * Updates the container's value using the CAS algorithm.
	 * @param oldI - the last value you received from the container 
	 * @param newI - the value you want to update to
	 * @return newI if oldI is the value actually inside the container, otherwise
	 * it will return the actual current value inside the container, which can be
	 * used to recalculate and/or retry the update. 
	 */
	public I update(I oldI, I newI)
	{
		lock.lock();
		if(state.equals(oldI))
		{
			state = newI;
			lock.unlock();
			return newI;
		}
		else
		{
			lock.unlock();
			return state;
		}
	}
	
	/**
	 * Applies the given {@code UnaryOperator} to the container's current value,
	 * then attempting to update the container with the newly calculated value, 
	 * retrying if the update fails. 
	 * @param function - a function to transform the value to the new value
	 * @return the final transformed value
	 */
	public I transform(UnaryOperator<I> function)
	{
		return filteredTransform(i -> true, function);
	}
	
	/**
	 * The same as {@link #transform()}, except that it also runs a predicate
	 * every time it tries to do the update to test if it really wants to do the
	 * update.
	 * @param pred - a predicate to determine if it is desirable to run the
	 * {@code UnaryOperator} function
	 * @param function - a {@code UnaryOperator} function that transforms the
	 * current state to the desired state.
	 * @return the final transformed value, or the untransformed value if the
	 * predicate failed
	 */
	public I filteredTransform(Predicate<I> pred, UnaryOperator<I> function)
	{
		I start = state;
		I result;
		do
		{
			if(!pred.test(start)) 
				break;
			
			result = function.apply(start);
			start = update(start, result);
		}while(!start.equals(result));
		return start;
	}
	
	/**
	 * @return the current state value
	 */
	public I value()
	{
		return state;
	}
}