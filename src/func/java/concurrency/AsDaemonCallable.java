package func.java.concurrency;

import java.util.concurrent.Callable;

public class AsDaemonCallable<V> extends ExtendableCallable<V>
{
	public static <V> ExtendableCallable<V> asDaemon(Callable<V> toCall)
	{
		return new AsDaemonCallable<>(toCall);
	}

	@Override
	public V call() throws Exception
	{
		Thread.currentThread().setDaemon(true);
		return super.call();
	}	
	
	private AsDaemonCallable(Callable<V> callableToExtend)
	{
		super(callableToExtend);
	}
}
