package func.java.controlflow;

public class ObjectCompareSwitchStatement<T> extends SwitchStatement<T>
{
	
	ObjectCompareSwitchStatement(T switchObj)
	{
		this.switchObj = switchObj;
	}
	
	T getSwitchArgument()
	{
		return switchObj;
	}

	public ObjectCompareCase<T> case_(T caseStatement)
	{
		return new ObjectCompareCase<>(caseStatement, this);
	}
	
	private final T switchObj;
}
