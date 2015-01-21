package func.java.concurrency;

/**
 * {@code ThreadNamingRunnable} is used to make {@code Runnable}s that change the
 * name of the thread they're in to make debugging the thread easier.
 * <p>
 * To do so, simply make a call to {@code ThreadNamingRunnable.namedThreadFrom()},
 * passing in the name you want to give the thread and the runnable code you want
 * the thread to run.</p>
 * Example (with static import of {@code namedThreadFrom}):
 * <pre><code>executorService.submit(namedThreadFrom("my thread name", () -> System.out.println("in thread"));)
 * </code></pre>
 */
final class ThreadNamingRunnable extends ExtendableRunnable
{
	public static ThreadNamingRunnable namedThreadFrom(String threadName, Runnable toRun)
	{
		return new ThreadNamingRunnable(threadName, toRun);
	}
	
	public void run()
	{
		final Thread currThread = Thread.currentThread();
		final String oldThreadName = currThread.getName();
		currThread.setName(threadName);
		try
		{
			super.run();
		}
		finally
		{
			currThread.setName(oldThreadName);
		}
	}
	
	private ThreadNamingRunnable(String threadName, Runnable decoratedRunnable)
	{
		super(decoratedRunnable);
		this.threadName = threadName;
	}
	
	private final String threadName;
}