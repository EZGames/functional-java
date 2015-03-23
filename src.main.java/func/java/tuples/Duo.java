package func.java.tuples;

import java.util.function.Consumer;

/**
 * Represents a 2-part tuple, as seen in other programming languages, essentially
 * allowing for multiple values to be passed around together (usually in a return
 * statement) without being intrinsically related.
 * <p>
 * Its main purpose is to serve as a return object, allowing methods to, in effect,
 * return more than one object.</p>
 * <p>
 * If one of the objects being returned is only sometimes returned, it is
 * suggested that you return an {@link java.util.Optional Optional} of that type,
 * forcing those that use your returned tuple to think about the possibility of
 * the object containing no value.</p> 
 * @param <T1> the type of the first object in the tuple
 * @param <T2> the type of the second object in the tuple
 */
public interface Duo<T1, T2>
{
	/**
	 * @return the first object in the tuple
	 */
	T1 one();	
	/**
	 * Provide a function that uses the first object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 * <p>
	 * The primary purpose of this is to allow in-line assignment.</p>
	 * <p>
	 * For example:<br>
	 * <code>
	 * String foo;<br>
	 * String bar;<br>
	 * //the method, baz(), returns a Tuple2&lt;String, String&gt;<br>
	 * baz().useOne((s) -> foo = s).useTwo((s) -> bar = s);
	 * </code>
	 * @param func - the function to "consume" the first object
	 * @return <code>this</code>, for method chaining.
	 */
	Duo<T1, T2> useOne(Consumer<?  super T1> func);	
	/**
	 * @return the second object in the tuple
	 */
	T2 two();	
	/**
	 * Provide a function that uses the second object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link #useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the second object
	 * @return <code>this</code>, for method chaining.
	 */
	Duo<T1, T2> useTwo(Consumer<? super T2> func);	
	/**
	 * @return the same tuple in reverse order
	 */
	Duo<T2, T1> swap();
	
	//***************************************************************************
	// Public static factories
	//***************************************************************************
	/**
	 * Creates and returns a Tuple2 containing the given objects
	 * @param one - the first object of the tuple
	 * @param two - the second object of the tuple
	 * @return the created Tuple2 containing the given objects
	 */
	public static <T1,T2> Duo<T1, T2> of(T1 one, T2 two)
	{
		return new DuoImpl<T1,T2>(one, two);
	}
}
