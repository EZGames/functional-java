package func.java.controlflow.statements.switches;

import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SwitchStatementFactory<T>
{
	//***************************************************************************
	// Public static factories
	//***************************************************************************
	public static SwitchStatement<Callable<Boolean>> switch_()
	{
		return new ParameterlessSwitchStatement();
	}
	
	public static <T> SwitchStatementFactory<T> switch_(T obj)
	{
		return new SwitchStatementFactory<T>(obj);
	}
	
	//***************************************************************************
	// Public API methods for creating a new switch statement
	//***************************************************************************
	public Case<? extends SwitchStatement<Supplier<T>>, Supplier<T>> case_(Supplier<T> obj)
	{
		return new SupplierCompareCase<T>(obj, new SupplierCompareSwitchStatement<T>(switchObj));
	}
	
	public Case<? extends SwitchStatement<Supplier<T>>, Supplier<T>> if_(Supplier<T> obj)
	{
		return case_(obj);
	}
	
	public Case<? extends SwitchStatement<T>, T> case_(T obj)
	{
		return new ObjectCompareCase<>(obj, new ObjectCompareSwitchStatement<T>(switchObj)); 
	}
	
	public Case<? extends SwitchStatement<T>, T> if_(T obj)
	{
		return null;
	}
	
	public Case<? extends SwitchStatement<Predicate<T>>, Predicate<T>> case_(Predicate<T> pred)
	{
		return new PredicateCase<T>(pred, new PredicateSwitchStatement<T>(switchObj));
	}
	
	public Case<? extends SwitchStatement<Predicate<T>>, Predicate<T>> if_(Predicate<T> pred)
	{
		return case_(pred);
	}
	
	//***************************************************************************
	// Private constructor
	//***************************************************************************
	private SwitchStatementFactory(T obj)
	{
		switchObj = obj;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final T switchObj;
}
