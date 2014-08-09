package func.java.controlflow.expressions.switches;

import java.util.function.Predicate;

class PredicateCase<T, R> extends Case<PredicateSwitchExpression<T, R>, Predicate<T>, R>
{

	public PredicateCase(Predicate<T> pred, PredicateSwitchExpression<T, R> switch_)
	{
		super(pred, switch_);
	}
	
	boolean isTrue()
	{
		return caseStatement.test(switch_.getSwitchArgument());
	}
}
