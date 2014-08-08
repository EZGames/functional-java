package func.java.tuples;

import java.util.function.Consumer;

final class TripletImpl<T1, T2, T3> implements Triplet<T1, T2, T3>
{
	public T1 one()
	{
		return one;
	}
	
	public Triplet<T1, T2, T3> useOne(Consumer<? super T1> func)
	{
		if(func != null)
		{
			func.accept(one);
		}
		return this;
	}

	public T2 two()
	{
		return two;
	}

	public Triplet<T1, T2, T3> useTwo(Consumer<? super T2> func)
	{
		if(func != null)
		{
			func.accept(two);
		}
		return this;
	}

	public T3 three()
	{
		return three;
	}

	public Triplet<T1, T2, T3> useThree(Consumer<? super T3> func)
	{
		if(func != null)
		{
			func.accept(three);
		}
		return this;
	}

	public Triplet<T3, T2, T1> swap()
	{
		return new SwappedTuple3<>(this);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(one).append(", ").append(two).append(", ").append(three).append(")");
		return sb.toString();
	}
	
	TripletImpl(T1 one, T2 two, T3 three)
	{
		this.one = one;
		this.two = two;
		this.three = three;
	}
	
	private final T1 one;
	private final T2 two;
	private final T3 three;
}

final class SwappedTuple3<T1, T2, T3> implements Triplet<T1, T2, T3>
{
	public T1 one()
	{
		return original.three();
	}

	public Triplet<T1, T2, T3> useOne(Consumer<? super T1> func)
	{
		original.useThree(func);
		return this;
	}

	public T2 two()
	{
		return original.two();
	}

	public Triplet<T1, T2, T3> useTwo(Consumer<? super T2> func)
	{
		original.useTwo(func);
		return this;
	}

	public T3 three()
	{
		return original.one();
	}

	public Triplet<T1, T2, T3> useThree(Consumer<? super T3> func)
	{
		original.useOne(func);
		return this;
	}

	public Triplet<T3, T2, T1> swap()
	{
		return original;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(one()).append(", ").append(two()).append(", ").append(three()).append(")");
		return sb.toString();
	}
	
	SwappedTuple3(Triplet<T3, T2, T1> original)
	{
		this.original = original;
	}
	
	private final Triplet<T3, T2, T1> original;
}
