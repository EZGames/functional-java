package func.java.recursion;

import java.util.stream.Stream;

/**
 * This interface was stolen from Functional Programming in Jave, written by
 * Venkat Subramaniam, published by Pragmatic Bookshelf.
 * <p>
 * {@code TailCall} is used to create recursive functions that can be run with
 * tail call optimization.</p>
 * <p> 
 * To have a function take advantage of this interface:<br>
 * 1. change the return type to {@code TailCall} with {@code T} being the 
 * original return type.<br>
 * 2. for the returned value of the exit clause, return {@code TailCall.done(value)},
 * with {@code value} being the original return value.<br>
 * 3. for recursive calls to the function, change it to a lambda that takes no
 * parameters and does the recursive call.<br>
 * For example, {@code return recursiveCall(2, 5)} becomes {@code return () -> 
 * recursiveCall(2, 5)}<br>
 * 4. call {@code invoke()} on what is returned by the recursive method</p>
 * <p>
 * Here's a full example:<br>
 * <code>
 * int factorial(final int number)<br>
 * {<br>
 * &nbsp;&nbsp;return factorial(number, 1);<br>
 * }<br><br>
 * 
 * int factorial(final int number, final int accumulation)<br>
 * {<br>
 * &nbsp;&nbsp;if(number == 1)<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;return accumulation;<br>
 * &nbsp;&nbsp;else<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;return factorial(number - 1, number * accumulation);<br>
 * }
 * </code><br><br>
 * becomes<br><br>
 * <code>
 * int factorial(final int number)<br>
 * {<br>
 * &nbsp;&nbsp;return factorial(number, 1).invoke();<br>
 * }<br><br>
 * 
 * TailCall<Integer> factorial(final int number, final int accumulation)<br>
 * {<br>
 * &nbsp;&nbsp;if(number == 1)<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;return done(accumulation);<br>
 * &nbsp;&nbsp;else<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;return () -> factorial(number - 1, number * accumulation);<br>
 * }
 * </code>
 * @param <T>
 */
@FunctionalInterface
public interface TailCall<T>
{
	/**
	 * The functional method of {@code TailCall}
	 * @return the next call
	 */
	TailCall<T> apply();
	
	/**
	 * Used to filter out {@code TailCall}s that aren't {@link #done()}
	 * @return false, unless it's the {@code TailCall} returned by {@code done()}
	 */
	default boolean isCompleted()
	{
		return false;
	}
	
	/**
	 * @return the result of the recursive function, unless it throws an exception.
	 * See Throws section
	 * @throws UnsupportedOperationException if not the {@code TailCall} returned
	 * by {@link #done()}
	 */
	default T result()
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Runs the {@code TailCall} recursive algorithm
	 * @return the result value
	 */
	default T invoke()
	{
		return Stream.iterate(this, TailCall::apply)
						 .filter(TailCall::isCompleted)
						 .findFirst() 	//returns the Optional type
						 .get() 			//returns the TailCall from the Optional
						 .result(); 	//returns the final result from the TailCall
	}
	
	/**
	 * Creates and returns the {@code TailCall} object that signifies the final
	 * result of the recursive algorithm and causes it to stop.
	 * @param value - the result of recursive algorithm
	 * @return the {@code TailCall} object that signifies the final result
	 */
	public static <T> TailCall<T> done(final T value)
	{
		return new TailCall<T>() {
			public boolean isCompleted() { return true;}
			public T result() { return value; }
			public TailCall<T> apply() { throw new UnsupportedOperationException(); }
		};
	}
}
