package func.java.tuples;

import java.util.function.Consumer;

public interface Quad<T1, T2, T3, T4>
{
	/**
	 * @return the first object in the tuple
	 */
	T1 one();
	/**
	 * Provide a function that uses the first object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Duo#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the first object
	 * @return <code>this</code>, for method chaining.
	 */
	Quad<T1, T2, T3, T4> useOne(Consumer<? super T1> func);
	/**
	 * @return the second object in the tuple
	 */
	T2 two();
	/**
	 * Provide a function that uses the second object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Duo#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the second object
	 * @return <code>this</code>, for method chaining.
	 */
	Quad<T1, T2, T3, T4> useTwo(Consumer<? super T2> func);
	/**
	 * @return the third object in the tuple
	 */
	T3 three();
	/**
	 * Provide a function that uses the third object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Duo#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the third object
	 * @return <code>this</code>, for method chaining.
	 */
	Quad<T1, T2, T3, T4> useThree(Consumer<? super T3> func);
	/**
	 * @return the third object in the tuple
	 */
	T4 four();
	/**
	 * Provide a function that uses the fourth object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Duo#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the fourth object
	 * @return <code>this</code>, for method chaining.
	 */
	Quad<T1, T2, T3, T4> useFour(Consumer<? super T4> func);
	/**
	 * @return the same tuple in reverse order
	 */
	Quad<T4, T3, T2, T1> swap();
	
	//***************************************************************************
	// Static factory method
	//***************************************************************************
	//hides the existance of Tuple3Impl
	/**
	 * Creates and returns a new Tuple3 containing the given objects
	 * @param one - the first object of the tuple
	 * @param two - the second object of the tuple
	 * @param three - the third object of the tuple
	 * @return the created Tuple3 containing the given objects
	 */
	public static <T1, T2, T3, T4> Quad<T1, T2, T3, T4> of(T1 one, T2 two, T3 three, T4 four)
	{
		return new QuartetImpl<>(one, two, three, four);
	}
}
