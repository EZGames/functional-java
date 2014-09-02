package func.java.collections;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * {@code SupplyingIterator} is a specialized iterator for dealing with
 * collections of {@link Supplier}s. To create it, you supply it with an
 * iterator that returns {@code Supplier} objects. After that, it becomes an
 * iterator that returns the object type that the {@code Supplier}s supplied.
 * <p>
 * The class is just a simply convenience class that was primarily build for my
 * own use in the creation of {@link func.java.generators generators}. I decided
 * that someone else might have a use for it down the road as well, so I made it
 * public and shared it.</p>
 * @param <E> - the type of element to be returned by the iterator
 */
public class SupplyingIterator<E> implements Iterator<E>
{
	public static <E> Iterator<E> from(Iterator<Supplier<E>> supplierIterator)
	{
		return new SupplyingIterator<>(supplierIterator);
	}
	
	@Override
	public boolean hasNext()
	{
		return supplierIterator.hasNext();
	}
	
	@Override
	public E next()
	{
		return supplierIterator.next().get();
	}
	
	private final Iterator<Supplier<E>> supplierIterator;
	
	private SupplyingIterator(Iterator<Supplier<E>> supplierIterator)
	{
		this.supplierIterator = supplierIterator;
	}
}
