package func.java.concurrency;

import static func.java.concurrency.ThreadPriorityVerifier.isLegal;

public class WithPriorityRunnable extends ExtendableRunnable
{
	public static ExtendableRunnable withPriority(Runnable toRun, int priority)
	{
		return new WithPriorityRunnable(toRun, priority);
	}
	
	public void run()
	{
		Thread currentThread = Thread.currentThread();
		int oldPriority = currentThread.getPriority();
		currentThread.setPriority(priority);
		try
		{
			super.run();
		}
		finally
		{
			currentThread.setPriority(oldPriority);
		}
	}
	
	private WithPriorityRunnable(Runnable runnableToExtend, int priority)
	{
		super(runnableToExtend);
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
