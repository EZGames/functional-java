package func.java.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;

//TODO make decorators AsDaemon, WithPriority
//DOC
public class ExtendableCallable<V> implements Callable<V>
{
	public ExtendableCallable(Callable<V> callableToExtend)
	{
		this.extendedCallable = callableToExtend;
	}
	
	public V call() throws Exception
	{
		return extendedCallable.call();
	}
	
	public ExtendableCallable<V> withThreadName(String nameOfThread)
	{
		return ThreadNamingCallable.namedThreadFrom(nameOfThread, this);
	}
	
	public ExtendableCallable<V> withExceptionHandling(UncaughtExceptionHandler exHandler)
	{
		return ExceptionHandlingCallable.from(this, exHandler);
	}
	
	protected final Callable<V> extendedCallable;
}
