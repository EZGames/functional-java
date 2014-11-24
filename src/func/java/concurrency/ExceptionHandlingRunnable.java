package func.java.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;

public final class ExceptionHandlingRunnable implements Runnable
{
	public static ExceptionHandlingRunnable from(Runnable toRun, UncaughtExceptionHandler exHandler)
	{
		return new ExceptionHandlingRunnable(toRun, exHandler);
	}
	
	public void run()
	{
		try
		{
			decoratedRunnable.run();
		}
		catch(Throwable e)
		{
			exHandler.uncaughtException(Thread.currentThread(), e);
		}
	}
	
	private ExceptionHandlingRunnable(Runnable decoratedRunnable, UncaughtExceptionHandler exHandler)
	{
		this.decoratedRunnable = decoratedRunnable;
		this.exHandler = exHandler;
	}
	
	private final Runnable decoratedRunnable;
	private final UncaughtExceptionHandler exHandler;
}