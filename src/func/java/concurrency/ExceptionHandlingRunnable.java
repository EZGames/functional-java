package func.java.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;
//TODO update readme to reflect these changes
final class ExceptionHandlingRunnable extends ExtendableRunnable
{
	public static ExceptionHandlingRunnable from(Runnable toRun, UncaughtExceptionHandler exHandler)
	{
		return new ExceptionHandlingRunnable(toRun, exHandler);
	}
	
	public void run()
	{
		try
		{
			super.run();
		}
		catch(Throwable e)
		{
			exHandler.uncaughtException(Thread.currentThread(), e);
		}
	}
	
	private ExceptionHandlingRunnable(Runnable decoratedRunnable, UncaughtExceptionHandler exHandler)
	{
		super(decoratedRunnable);
		this.exHandler = exHandler;
	}
	
	private final UncaughtExceptionHandler exHandler;
}