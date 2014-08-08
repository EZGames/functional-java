package func.java.controlflow;


public class DoUnless
{
	public static DoUnless do_(Runnable run)
	{
		return new DoUnless(run);
	}
	
	public void unless_(boolean condition)
	{
		if(condition)
		{
			run.run();	
		}
	}
	
	private DoUnless(Runnable run)
	{
		this.run = run;
	}
	
	private final Runnable run;
}
