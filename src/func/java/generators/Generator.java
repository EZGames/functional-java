package func.java.generators;

import java.util.function.Supplier;
import func.java.collections.BuiltCollection;

public interface Generator<T>
{
	/**
	 * Specify a value to be returned 
	 * @param valueToYield
	 */
	void yield(T valueToYield);
	
	void yield(Supplier<T> valueToYield);
	
	BuiltCollection<T> generate();
}
