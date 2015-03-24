package func.java.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;

//DOC
public class ExtendableRunnable implements Runnable
{
	public ExtendableRunnable(Runnable runnableToExtend)
	{
		this.extendedRunnable = runnableToExtend;
	}
	
	public void run()
	{
		extendedRunnable.run();
	}
	
	public ExtendableRunnable withThreadName(String nameOfThread)
	{
		return ThreadNamingRunnable.namedThreadFrom(nameOfThread, this);
	}
	
	public ExtendableRunnable withExceptionHandling(UncaughtExceptionHandler exHandler)
	{
		return ExceptionHandlingRunnable.from(this, exHandler);
	}
	
	protected final Runnable extendedRunnable;
}
