package func.java.generators;

import java.util.ArrayList;
import java.util.function.Supplier;
import func.java.collections.SupplyingIterable;

public class EagerGenerator<T> implements Generator<T>
{
	public EagerGenerator()
	{
		yieldedValues = new ArrayList<>();
	}
	
	public void yield(T valueToYield)
	{
		yield(() -> valueToYield);
	}
	
	public void yield(Supplier<T> valueToYield)
	{
		yieldedValues.add(valueToYield);
	}
	
	public Iterable<T> generate()
	{
		return SupplyingIterable.from(yieldedValues);
	}
	
	private ArrayList<Supplier<T>> yieldedValues;
}