package func.java.concurrency;

import java.util.concurrent.Callable;

/**
 * {@code ThreadNamingRunnable} is used to make {@code Callable}s that change the
 * name of the thread they're in to make debugging the thread easier.
 * <p>
 * To do so, simply make a call to {@code ThreadNamingRunnable.namedThreadFrom()},
 * passing in the name you want to give the thread and the callable code you want
 * the thread to run.</p>
 * Example (with static import of {@code namedThreadFrom}):
 * <pre><code>executorService.submit(namedThreadFrom("my thread name", () -> "returned String");)
 * </code></pre>
 */
final class ThreadNamingCallable<T> extends ExtendableCallable<T>
{
	public static <T> ThreadNamingCallable<T> namedThreadFrom(String threadName, Callable<T> toCall)
	{
		return new ThreadNamingCallable<>(threadName, toCall);
	}
	
	public T call() throws Exception
	{
		final Thread currThread = Thread.currentThread();
		final String oldName = currThread.getName();
		currThread.setName(threadName);
		
		try
		{
			return super.call();
		}
		finally
		{
			currThread.setName(oldName);
		}
	}
	
	private ThreadNamingCallable(String threadName, Callable<T> decoratedCallable)
	{
		super(decoratedCallable);
		this.threadName = threadName;
	}
	
	private final String threadName;
}