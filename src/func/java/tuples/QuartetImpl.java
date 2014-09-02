package func.java.tuples;

import java.util.function.Consumer;

//TODO equals and hashcode
class QuartetImpl<T1, T2, T3, T4> implements Quartet<T1, T2, T3, T4>
{
	public T1 one()
	{
		return one;
	}
	
	public Quartet<T1, T2, T3, T4> useOne(Consumer<? super T1> func)
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
	
	public Quartet<T1, T2, T3, T4> useTwo(Consumer<? super T2> func)
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
	
	public Quartet<T1, T2, T3, T4> useThree(Consumer<? super T3> func)
	{
		if(func != null)
		{
			func.accept(three);
		}
		return this;
	}
	
	public T4 four()
	{
		return four;
	}
	
	public Quartet<T1, T2, T3, T4> useFour(Consumer<? super T4> func)
	{
		if(func != null)
		{
			func.accept(four);
		}
		return this;
	}
	
	public Quartet<T4, T3, T2, T1> swap()
	{
		return new SwappedTuple4<>(this);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(one).append(", ").append(two).append(", ").append(three).append(", ").append(four).append(")");
		return sb.toString();
	}
	
	QuartetImpl(T1 one, T2 two, T3 three, T4 four)
	{
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
	}
	
	private final T1 one;
	private final T2 two;
	private final T3 three;
	private final T4 four;
	
}

class SwappedTuple4<T1, T2, T3, T4> implements Quartet<T1, T2, T3, T4>
{
	public T1 one()
	{
		return normal.four();
	}

	public Quartet<T1, T2, T3, T4> useOne(Consumer<? super T1> func)
	{
		normal.useFour(func);
		return this;
	}

	public T2 two()
	{
		return normal.three();
	}

	public Quartet<T1, T2, T3, T4> useTwo(Consumer<? super T2> func)
	{
		normal.useThree(func);
		return this;
	}

	public T3 three()
	{
		return normal.two();
	}

	public Quartet<T1, T2, T3, T4> useThree(Consumer<? super T3> func)
	{
		normal.useTwo(func);
		return this;
	}

	public T4 four()
	{
		return normal.one();
	}

	public Quartet<T1, T2, T3, T4> useFour(Consumer<? super T4> func)
	{
		normal.useOne(func);
		return this;
	}

	public Quartet<T4, T3, T2, T1> swap()
	{
		return normal;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(one()).append(", ").append(two()).append(", ").append(three()).append(", ").append(four()).append(")");
		return sb.toString();
	}
	
	SwappedTuple4(Quartet<T4, T3, T2, T1> normal)
	{
		this.normal = normal;
	}
	
	private final Quartet<T4, T3, T2, T1> normal;
}
