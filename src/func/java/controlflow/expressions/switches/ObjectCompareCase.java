package func.java.controlflow.expressions.switches;

class ObjectCompareCase<T, R> extends Case<ObjectCompareSwitchExpression<T, R>, T, R>
{
	
	ObjectCompareCase(T caseStatement, ObjectCompareSwitchExpression<T, R> switch_)
	{
		super(caseStatement, switch_);
	}

	@Override
	boolean isTrue()
	{
		return caseStatement.equals(switch_.getSwitchArgument());
	}
	
}
