package func.java.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

class BuiltFromSupplyingCollection<E> implements BuiltCollection<E>
{
	BuiltFromSupplyingCollection(Collection<Supplier<E>> collection)
	{
		this.collection = collection;
	}
	
	private final Collection<Supplier<E>> collection;
	
	@Override
	public Iterator<E> iterator()
	{
		return SupplyingIterator.from(collection.iterator());
	}
	
	@Override
	public Stream<E> stream()
	{
		return collection.stream().map(Supplier::get);
	}	
}
