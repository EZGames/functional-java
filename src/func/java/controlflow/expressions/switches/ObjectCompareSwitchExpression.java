package func.java.controlflow.expressions.switches;

class ObjectCompareSwitchExpression<T, R> extends SwitchExpression<T, R>
{	
	ObjectCompareSwitchExpression(T switchObj)
	{
		this.switchObj = switchObj;
	}
	
	T getSwitchArgument()
	{
		return switchObj;
	}

	public ObjectCompareCase<T, R> case_(T caseStatement)
	{
		return new ObjectCompareCase<>(caseStatement, this);
	}
	
	private final T switchObj;
}
