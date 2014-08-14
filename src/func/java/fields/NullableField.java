package func.java.fields;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NullableField<T> implements Supplier<Optional<T>>
{
	public static <T> NullableField<T> holding(T initialValue, Consumer<T> dataChecker)
	{
		return new NullableField<>(dataChecker, initialValue);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> NullableField<T> holding(T initialValue)
	{
		return new NullableField<>(nonChecker, initialValue);
	}
	
	public static <T> NullableField<T> startNull(Consumer<T> dataChecker)
	{
		return new NullableField<>(dataChecker, null);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> NullableField<T> startNull()
	{
		return new NullableField<>(nonChecker, null);
	}
	
	public Optional<T> get()
	{
		return getter.get();
	}
	
	public void set(T obj)
	{
		dataChecker.accept(obj);
		Optional<T> newValue = Optional.ofNullable(obj);
		getter = () -> newValue;
	}
	
	@SuppressWarnings("rawtypes")
	private static final Consumer nonChecker = new Consumer(){ public void accept(Object o) {} };
	private Supplier<Optional<T>> getter;
	private Consumer<T> dataChecker;
	
	@SuppressWarnings("unchecked")
	private NullableField(Consumer<T> dataChecker, T initialValue)
	{
		if(dataChecker == null)
		{
			this.dataChecker = nonChecker;
		}
		this.dataChecker = dataChecker;
		set(initialValue);
	}
}
