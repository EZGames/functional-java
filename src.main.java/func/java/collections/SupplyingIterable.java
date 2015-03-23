package func.java.collections;

import java.util.Iterator;
import java.util.function.Supplier;
//DOC TEST
public class SupplyingIterable<E> implements Iterable<E>
{
	public static <E> SupplyingIterable<E> from(Iterable<Supplier<E>> supplierList)
	{
		return new SupplyingIterable<>(supplierList);
	}
	
	@Override
	public Iterator<E> iterator()
	{
		return SupplyingIterator.from(supplierList.iterator());
	}
	
	private Iterable<Supplier<E>> supplierList;
	
	private SupplyingIterable(Iterable<Supplier<E>> supplierList)
	{
		this.supplierList = supplierList;
	}
}
