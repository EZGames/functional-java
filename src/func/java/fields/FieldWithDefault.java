package func.java.fields;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FieldWithDefault<T> implements Supplier<T>
{
	public static <T> FieldWithDefault<T> holding(T initialValue, T defaultValue, Consumer<T> dataChecker)
	{
		return new FieldWithDefault<>(dataChecker, initialValue, defaultValue);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> FieldWithDefault<T> holding(T initialValue, T defaultValue)
	{
		return new FieldWithDefault<>(nonChecker, initialValue, defaultValue);
	}
	
	public T get()
	{
		return getter.get();
	}
	
	public void set(T obj)
	{
		try
		{
			dataChecker.accept(obj);

			if(obj == null)
			{
				getter = () -> default_;
			}
			else
			{
				getter = () -> obj;
			}
		}
		catch(Exception e)
		{
			getter = () -> default_;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static final Consumer nonChecker = new Consumer() { public void accept(Object t) {} };
	private Supplier<T> getter;
	private Consumer<T> dataChecker;
	private final T default_;
	
	private FieldWithDefault(Consumer<T> dataChecker, T initialValue, T defaultValue)
	{
		default_ = defaultValue;
		this.dataChecker = dataChecker;
		set(initialValue);
	}	
}
