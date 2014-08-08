package func.java.controlflow;

import java.util.function.Predicate;

class PredicateSwitchStatement<T> extends SwitchStatement<Predicate<T>>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public PredicateCase<T> case_(Predicate<T> expr)
	{
		return new PredicateCase<T>(expr, this);
	}
	
	T getSwitchArgument()
	{
		return switchObj;
	}
	
	//***************************************************************************
	// Internal constructor
	//***************************************************************************
	PredicateSwitchStatement(T switchObj)
	{
		this.switchObj = switchObj;
	}
	
	//***************************************************************************
	// private fields
	//***************************************************************************
	private final T switchObj;
}
