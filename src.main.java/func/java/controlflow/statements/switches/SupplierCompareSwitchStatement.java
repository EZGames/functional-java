package func.java.controlflow.statements.switches;

import java.util.function.Supplier;


class SupplierCompareSwitchStatement<T> extends SwitchStatement<Supplier<T>>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public SupplierCompareCase<T> case_(Supplier<T> obj)
	{
		return new SupplierCompareCase<T>(obj, this);
	}
	
	T getSwitchArgument()
	{
		return switchObj;
	}
	
	//***************************************************************************
	// Internal constructor
	//***************************************************************************
	SupplierCompareSwitchStatement(T switchObj)
	{
		this.switchObj = switchObj;
	}
	
	private final T switchObj;
}
