package func.java.controlflow;

// TODO: document
public class Unless
{
	public static Unless unless(boolean expression)
	{
		return new Unless(expression);
	}
	
	public static void unless(boolean expression, Runnable action)
	{
		new Unless(expression).doThis(action);
	}
	
	public void doThis(Runnable action)
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
