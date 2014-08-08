package func.java.tuples;

import java.util.function.Consumer;

/**
 * Represents a 3-part tuple, as seen in other programming languages, essentially
 * allowing for multiple values to be passed around together (usually in a return
 * statement) without being intrinsically related.
 * <p>
 * @see {@link Pair} for more information on the purpose and use of these
 * Tuples.</p> 
 * @param <T1> the type of the first object in the tuple
 * @param <T2> the type of the second object in the tuple
 * @param <T3> the type of the third object in the tuple
 */
public interface Triplet<T1, T2, T3>
{
	/**
	 * @return the first object in the tuple
	 */
	T1 one();
	/**
	 * Provide a function that uses the first object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Pair#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the first object
	 * @return <code>this</code>, for method chaining.
	 */
	Triplet<T1, T2, T3> useOne(Consumer<? super T1> func);
	/**
	 * @return the second object in the tuple
	 */
	T2 two();
	/**
	 * Provide a function that uses the second object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Pair#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the second object
	 * @return <code>this</code>, for method chaining.
	 */
	Triplet<T1, T2, T3> useTwo(Consumer<? super T2> func);
	/**
	 * @return the third object in the tuple
	 */
	T3 three();
	/**
	 * Provide a function that uses the third object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Pair#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the third object
	 * @return <code>this</code>, for method chaining.
	 */
	Triplet<T1, T2, T3> useThree(Consumer<? super T3> func);
	/**
	 * @return the same tuple in reverse order
	 */
	Triplet<T3, T2, T1> swap();
	
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
	public static <T1, T2, T3> Triplet<T1, T2, T3> of(T1 one, T2 two, T3 three)
	{
		return new TripletImpl<>(one, two, three);
	}
}
