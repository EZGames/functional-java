package func.java.concurrency;

import java.util.concurrent.Callable;
import static func.java.concurrency.ThreadPriorityVerifier.isLegal;

public class WithPriorityCallable<V> extends ExtendableCallable<V>
{
	public static <V> ExtendableCallable<V> withPriority(Callable<V> toCall, int priority)
	{
		return new WithPriorityCallable<>(toCall, priority);
	}
	
	public V call() throws Exception
	{
		Thread currentThread = Thread.currentThread();
		int oldPriority = currentThread.getPriority();
		currentThread.setPriority(priority);
		try
		{
			return super.call();
		}
		finally
		{
			currentThread.setPriority(oldPriority);
		}
	}

	private WithPriorityCallable(Callable<V> callableToExtend, int priority)
	{
		super(callableToExtend);
		if(isLegal(priority))
		{
			this.priority = priority;
		}
		else
		{
			throw new IllegalArgumentException(String.format("Thread priority must be between %d and %d", Thread.MIN_PRIORITY, Thread.MAX_PRIORITY));
		}
	}
	
	private final int priority;
}