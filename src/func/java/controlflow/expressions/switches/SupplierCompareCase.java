package func.java.controlflow.expressions.switches;

import java.util.function.Supplier;

class SupplierCompareCase<T, R> extends Case<SupplierCompareSwitchExpression<T, R>, Supplier<T>, R>
{
	public SupplierCompareCase(Supplier<T> obj, SupplierCompareSwitchExpression<T, R> switch_)
	{
		super(obj, switch_);
	}
	
	boolean isTrue()
	{
		return caseStatement.get().equals(switch_.getSwitchArgument());
	}
}
