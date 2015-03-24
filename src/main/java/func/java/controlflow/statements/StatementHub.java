package func.java.controlflow.statements;

import java.util.function.Supplier;
import func.java.controlflow.statements.switches.SwitchStatement;
import func.java.controlflow.statements.switches.SwitchStatementHub;

public class StatementHub
{
	public static Unless do_(Runnable runner)
	{
		return Unless.do_(runner);
	}
	
	public static <T> SwitchStatementHub<T> switch_(T obj)
	{
		return SwitchStatementHub.switch_(obj);
	}
	
	public static SwitchStatement<Supplier<Boolean>> switch_()
	{
		return SwitchStatementHub.switch_();
	}
}
