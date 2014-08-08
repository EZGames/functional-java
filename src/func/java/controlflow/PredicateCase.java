package func.java.controlflow;

import java.util.function.Predicate;

class PredicateCase<T> extends Case<PredicateSwitchStatement<T>, Predicate<T>>
{

	public PredicateCase(Predicate<T> pred, PredicateSwitchStatement<T> switch_)
	{
		super(pred, switch_);
	}
	
	boolean isTrue()
	{
		return caseStatement.test(switch_.getSwitchArgument());
	}

	public Case<PredicateSwitchStatement<T>, Predicate<T>> case_(Predicate<T> expr)
	{
		switch_.addCase(this);
		return switch_.case_(expr);
	}	
}
