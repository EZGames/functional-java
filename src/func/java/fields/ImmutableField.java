package func.java.fields;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ImmutableField<T> implements Supplier<T>
{
	public static <T> ImmutableField<T> holding(T obj, Consumer<T> dataChecker)
	{
		dataChecker.accept(obj);
		return new ImmutableField<>(obj);
	}
	
	public static <T> ImmutableField<T> holding(T obj)
	{
		return new ImmutableField<>(obj);
	}
	
	public T get()
	{
		return getter.get();
	}
	
	private ImmutableField(T obj)
	{
		getter = () -> obj;
	}
	
	private final Supplier<T> getter;
}
