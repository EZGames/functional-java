package func.java.controlflow.expressions.switches;

import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SwitchExpressionFactory<T, R>
{
	//***************************************************************************
	// Public static factories
	//***************************************************************************
	public static <R> SwitchExpression<Callable<Boolean>, R> switch_()
	{
		return new ParameterlessSwitchExpression<>();
	}
	
	public static <T, R> SwitchExpressionFactory<T, R> switch_(T obj)
	{
		return new SwitchExpressionFactory<>(obj);
	}
	
	//***************************************************************************
	// Public API methods for creating a new switch statement
	//***************************************************************************
	public Case<? extends SwitchExpression<Supplier<T>, R>, Supplier<T>, R> case_(Supplier<T> obj)
	{
		return new SupplierCompareCase<>(obj, new SupplierCompareSwitchExpression<>(switchObj));
	}
	
	public Case<? extends SwitchExpression<Supplier<T>, R>, Supplier<T>, R> if_(Supplier<T> obj)
	{
		return case_(obj);
	}
	
	public Case<? extends SwitchExpression<T, R>, T, R> case_(T obj)
	{
		return new ObjectCompareCase<>(obj, new ObjectCompareSwitchExpression<>(switchObj)); 
	}
	
	public Case<? extends SwitchExpression<T, R>, T, R> if_(T obj)
	{
		return null;
	}
	
	public Case<? extends SwitchExpression<Predicate<T>, R>, Predicate<T>, R> case_(Predicate<T> pred)
	{
		return new PredicateCase<>(pred, new PredicateSwitchExpression<>(switchObj));
	}
	
	public Case<? extends SwitchExpression<Predicate<T>, R>, Predicate<T>, R> if_(Predicate<T> pred)
	{
		return case_(pred);
	}
	
	//***************************************************************************
	// Private constructor
	//***************************************************************************
	private SwitchExpressionFactory(T obj)
	{
		switchObj = obj;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final T switchObj;
}
