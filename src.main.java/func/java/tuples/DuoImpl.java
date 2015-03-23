package func.java.tuples;

import java.util.function.Consumer;

final class DuoImpl<T1, T2> implements Duo<T1, T2>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public T1 one()
	{
		return one;
	}
	
	public Duo<T1, T2> useOne(Consumer<? super T1> func)
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

	public Duo<T1, T2> useTwo(Consumer<? super T2> func)
	{
		if(func != null)
		{
			func.accept(two);
		}
		return this;
	}
	
	public Duo<T2, T1> swap()
	{
		return new SwappedTuple2<>(this);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(one).append(", ").append(two).append(")");
		return sb.toString();
	}
	
	public boolean equals(Object o)
	{
		if(o == this)
		{
			return true;
		}
		
		if(o instanceof DuoImpl)
		{
			Duo<?,?>other = (Duo<?,?>)o;
			return other.one().equals(one) && other.two().equals(two);
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return (one.hashCode() + 651654746) ^ (two.hashCode() * 17);
	}
	
	//***************************************************************************
	// Package-private constructors
	//***************************************************************************
	DuoImpl(T1 first, T2 second)
	{
		one = first;
		two = second;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final T1 one;
	private final T2 two;	
}

final class SwappedTuple2<T1, T2> implements Duo<T1, T2>
{
	public SwappedTuple2(Duo<T2, T1> original)
	{
		this.original = original;
	}

	public T1 one()
	{
		return original.two();
	}

	public Duo<T1, T2> useOne(Consumer<? super T1> func)
	{
		original.useTwo(func);
		return this;
	}

	public T2 two()
	{
		return original.one();
	}

	public Duo<T1, T2> useTwo(Consumer<? super T2> func)
	{
		original.useOne(func);
		return this;
	}

	public Duo<T2, T1> swap()
	{
		return original;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(one()).append(", ").append(two()).append(")");
		return sb.toString();
	}
	
	public boolean equals(Object o)
	{
		if(o == this)
		{
			return true;
		}
		
		if(o instanceof DuoImpl)
		{
			Duo<?,?>other = (Duo<?,?>)o;
			return other.one().equals(original.one()) && other.two().equals(original.two());
		}
		
		return false;
	}
	
	public int hashCode()
	{
		return (one().hashCode() + 651654746) ^ (two().hashCode() * 17);
	}
	
	private final Duo<T2, T1> original;	
}
