package func.java.controlflow.statements.switches;

class ObjectCompareCase<T> extends Case<ObjectCompareSwitchStatement<T>, T>
{
	
	ObjectCompareCase(T caseStatement, ObjectCompareSwitchStatement<T> switch_)
	{
		super(caseStatement, switch_);
	}

	@Override
	boolean isTrue()
	{
		return caseStatement.equals(switch_.getSwitchArgument());
	}
	
	@Override
	public ObjectCompareCase<T> case_(T expr)
	{
		switch_.addCase(this);
		return switch_.case_(expr);
	}
	
}
