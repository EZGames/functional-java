package func.java.controlflow.expressions.switches;

import java.util.function.Supplier;


class SupplierCompareSwitchExpression<T, R> extends SwitchExpression<Supplier<T>, R>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public SupplierCompareCase<T, R> case_(Supplier<T> obj)
	{
		return new SupplierCompareCase<>(obj, this);
	}
	
	T getSwitchArgument()
	{
		return switchObj;
	}
	
	//***************************************************************************
	// Internal constructor
	//***************************************************************************
	SupplierCompareSwitchExpression(T switchObj)
	{
		this.switchObj = switchObj;
	}
	
	private final T switchObj;
}
