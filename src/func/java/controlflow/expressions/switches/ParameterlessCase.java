package func.java.controlflow.expressions.switches;

import java.util.concurrent.Callable;

// TODO document and test
class ParameterlessCase<R> extends Case<ParameterlessSwitchExpression<R>, Callable<Boolean>, R>
{
	//***************************************************************************
	// Public constructor
	//***************************************************************************
	public ParameterlessCase(Callable<Boolean> expr, ParameterlessSwitchExpression<R> switch_)
	{
		super(expr, switch_);
	}
	
	//***************************************************************************
	// Internal use methods
	//***************************************************************************
	boolean isTrue()
	{
		try
		{
			return caseStatement.call();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
