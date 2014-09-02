package func.java.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A Collection whose sole purpose is to be iterated through.  You can no longer
 * add, remove (maybe remove, if the iterator implements it), or change out
 * objects in the collection.  It is effectively an immutable collection.
 * @param <E> - the type of elements stored in the collection
 */
public interface BuiltCollection<E> extends Iterable<E>
{
	Iterator<E> iterator();
	/**
	 * @return a {@link Stream} for the collection.
	 */
	Stream<E> stream();
	
	/**
	 * Easily build a {@code BuiltCollection} from any {@link Collection}
	 * @param collection - collection to transform into a {@code BuiltCollection}
	 * @return the new {@code BuiltCollection}
	 */
	public static <E> BuiltCollection<E> from(Collection<E> collection)
	{
		return new BuiltFromCollection<>(collection);
	}
	
	/**
	 * Easily build a {@BuiltCollection} from any {@link Collection} that stores
	 * {@link Supplier}s of objects.  It automatically transforms the 
	 * {@code Supplier}s into the supplied objects during the iterating or
	 * streaming process.
	 * @param suppCollection
	 * @return
	 */
	public static <E> BuiltCollection<E> fromSupplying(Collection<Supplier<E>> suppCollection)
	{
		return new BuiltFromSupplyingCollection<>(suppCollection);
	}
}
