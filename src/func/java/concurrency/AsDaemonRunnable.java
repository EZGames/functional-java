package func.java.concurrency;

public class AsDaemonRunnable extends ExtendableRunnable
{
	public ExtendableRunnable asDaemon(Runnable toRun)
	{
		return new ExtendableRunnable(toRun);
	}
	
	public void run()
	{
		Thread.currentThread().setDaemon(true);
		super.run();
	}
	
	private AsDaemonRunnable(Runnable runnableToExtend)
	{
		super(runnableToExtend);
	}
}