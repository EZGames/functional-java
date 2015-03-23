package func.java.controlflow.statements.switches;

import java.util.function.Supplier;

// DOC TEST
class ParameterlessCase extends Case<ParameterlessSwitchStatement, Supplier<Boolean>>
{
	//***************************************************************************
	// Public constructor
	//***************************************************************************
	public ParameterlessCase(Supplier<Boolean> expr, ParameterlessSwitchStatement switch_)
	{
		super(expr, switch_);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public ParameterlessCase case_(Supplier<Boolean> expr)
	{
		switch_.addCase(this);
		return switch_.case_(expr);
	}
	
	//***************************************************************************
	// Internal use methods
	//***************************************************************************
	boolean isTrue()
	{
		return caseStatement.get();
	}
}
