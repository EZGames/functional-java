package func.java.controlflow;

import java.util.function.Supplier;

class SupplierCompareCase<T> extends Case<SupplierCompareSwitchStatement<T>, Supplier<T>>
{
	public SupplierCompareCase(Supplier<T> obj, SupplierCompareSwitchStatement<T> switch_)
	{
		super(obj, switch_);
	}
	
	boolean isTrue()
	{
		return caseStatement.get().equals(switch_.getSwitchArgument());
	}

	public SupplierCompareCase<T> case_(Supplier<T> expr)
	{
		switch_.addCase(this);
		return switch_.case_(expr);
	}
}
