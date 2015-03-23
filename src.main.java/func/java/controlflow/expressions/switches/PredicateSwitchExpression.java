package func.java.controlflow.expressions.switches;

import java.util.function.Predicate;

class PredicateSwitchExpression<T, R> extends SwitchExpression<Predicate<T>, R>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public PredicateCase<T, R> case_(Predicate<T> expr)
	{
		return new PredicateCase<>(expr, this);
	}
	
	T getSwitchArgument()
	{
		return switchObj;
	}
	
	//***************************************************************************
	// Internal constructor
	//***************************************************************************
	PredicateSwitchExpression(T switchObj)
	{
		this.switchObj = switchObj;
	}
	
	//***************************************************************************
	// private fields
	//***************************************************************************
	private final T switchObj;
}
