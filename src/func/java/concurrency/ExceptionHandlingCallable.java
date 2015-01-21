package func.java.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;
// DOC
// TODO add to readme (same applies to Runnable version)
public final class ExceptionHandlingCallable<T> extends ExtendableCallable<T>
{
	public static <T> ExceptionHandlingCallable<T> from(Callable<T> toCall, UncaughtExceptionHandler exHandler)
	{
		return new ExceptionHandlingCallable<>(toCall, exHandler);
	}
	
	public T call() throws Exception
	{
		try
		{
			return super.call();
		}
		catch(Throwable e)
		{
			exHandler.uncaughtException(Thread.currentThread(), e);
			return null;
		}
	}
	
	private ExceptionHandlingCallable(Callable<T> decoratedCallable, UncaughtExceptionHandler exHandler)
	{
		super(decoratedCallable);
		this.exHandler = exHandler;
	}
	
	private final UncaughtExceptionHandler exHandler;
}