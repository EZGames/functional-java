package func.java.fields;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Field<T> implements Supplier<T>
{	
	public static <T> Field<T> holding(T initialValue, Consumer<T> dataChecker)
	{
		return new Field<>(dataChecker, initialValue);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Field<T> holding(T initialValue)
	{
		return new Field<>(nonChecker, initialValue);
	}
	
	public T get()
	{
		return getter.get();
	}
	
	public void set(T obj)
	{
		if(obj == null)
		{
			throw new IllegalArgumentException("null object passed into Field");
		}
		dataChecker.accept(obj);
		getter = () -> obj;
	}
	
	@SuppressWarnings("rawtypes")
	private static final Consumer nonChecker = new Consumer() { public void accept(Object t) {} };
	private Supplier<T> getter;
	private Consumer<T> dataChecker;
	
	private Field(Consumer<T> dataChecker, T initialValue)
	{
		this.dataChecker = dataChecker;
		set(initialValue);
	}
}
