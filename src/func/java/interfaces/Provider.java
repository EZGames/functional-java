package func.java.interfaces;

import java.util.function.Supplier;

/**
 * Strangely, and sadly, the Supplier functional interface doesn't have a nice
 * static method for creating them for a specific object (i.e. 
 * {@code Supplier.of(objectToSupply)}).  You can obviously do it with a lambda
 * expression (i.e. {@code () -> objectToSupply}), but I'm not a fan of seeing
 * lambdas in the front-facing code; I prefer descriptive names.
 * With this interface, you put {@code Provider.of(objectToSupply)} which is
 * more descriptive of its purpose.
 * <p>
 * I tried many different name and wording combinations, but I realized that 
 * "Supplier of _" just read the best.  Unfortunately, I couldn't use the name
 * as Supplier, so I used a word that is practically a synonym. Plus it's the
 * name that the DIY-DI system suggested before Java 8 came out.
 * @param <T> the type of object to return from the get() method
 */
@FunctionalInterface
public interface Provider<T> extends Supplier<T>
{
	public static <T> Supplier<? super T> of(T obj)
	{
		return () -> obj;
	}
}
