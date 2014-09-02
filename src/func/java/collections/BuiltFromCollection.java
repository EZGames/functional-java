package func.java.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

class BuiltFromCollection<E> implements BuiltCollection<E>
{
	BuiltFromCollection(Collection<E> collection)
	{
		this.collection = collection;
	}
	
	private final Collection<E> collection;
	
	@Override
	public Iterator<E> iterator()
	{
		return collection.iterator();
	}
	
	@Override
	public Stream<E> stream()
	{
		return collection.stream();
	}	
}
