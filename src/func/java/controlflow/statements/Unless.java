package func.java.controlflow.statements;

// TODO: document
public class Unless
{
	public static Unless unless_(boolean expression)
	{
		return new Unless(expression);
	}
	
	public static void unless_(boolean expression, Runnable action)
	{
		new Unless(expression).do_(action);
	}
	
	public void do_(Runnable action)
	{
		if(!expression)
		{
			action.run();
		}
	}	
	
	private boolean expression;
	
	private Unless(boolean expression)
	{
		this.expression = expression;
	}
}
