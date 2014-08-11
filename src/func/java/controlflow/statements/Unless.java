package func.java.controlflow.statements;


public class Unless
{
	public static Unless do_(Runnable run)
	{
		return new Unless(run);
	}
	
	public void unless_(boolean condition)
	{
		if(!condition)
		{
			run.run();	
		}
	}
	
	private Unless(Runnable run)
	{
		this.run = run;
	}
	
	private final Runnable run;
}
