package func.java.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;

public final class ExceptionHandlingCallable<T> implements Callable<T>
{
	public static <T> ExceptionHandlingCallable<T> from(Callable<T> toCall, UncaughtExceptionHandler exHandler)
	{
		return new ExceptionHandlingCallable<>(toCall, exHandler);
	}
	
	public T call() throws Exception
	{
		try
		{
			return decoratedCallable.call();
		}
		catch(Throwable e)
		{
			exHandler.uncaughtException(Thread.currentThread(), e);
			return null;
		}
	}
	
	private ExceptionHandlingCallable(Callable<T> decoratedCallable, UncaughtExceptionHandler exHandler)
	{
		this.decoratedCallable = decoratedCallable;
		this.exHandler = exHandler;
	}
	
	private final Callable<T> decoratedCallable;
	private final UncaughtExceptionHandler exHandler;
}