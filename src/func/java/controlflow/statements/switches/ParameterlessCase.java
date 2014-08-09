package func.java.controlflow.statements.switches;

import java.util.concurrent.Callable;

// TODO document and test
class ParameterlessCase extends Case<ParameterlessSwitchStatement, Callable<Boolean>>
{
	//***************************************************************************
	// Public constructor
	//***************************************************************************
	public ParameterlessCase(Callable<Boolean> expr, ParameterlessSwitchStatement switch_)
	{
		super(expr, switch_);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public ParameterlessCase case_(Callable<Boolean> expr)
	{
		switch_.addCase(this);
		return switch_.case_(expr);
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
