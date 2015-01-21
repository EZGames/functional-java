package func.java.concurrency;

public class ThreadPriorityVerifier
{
	public static boolean isLegal(int priority)
	{
		return priority < Thread.MAX_PRIORITY && priority > Thread.MIN_PRIORITY;
	}
}
