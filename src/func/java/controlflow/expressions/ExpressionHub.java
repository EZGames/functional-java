package func.java.controlflow.expressions;

import java.util.function.Supplier;
import func.java.controlflow.expressions.ifexpr.IfExpression;
import func.java.controlflow.expressions.ifexpr.NormalIfBlock;
import func.java.controlflow.expressions.switches.SwitchExpressionHub;

public class ExpressionHub<R>
{
	//***************************************************************************
	// Public static factory methods
	//***************************************************************************
	public static <R> ExpressionHub<R> returns_(Class<R> clazz)
	{
		return new ExpressionHub<>();
	}
	
	public static <R> UnlessExpression<R> returns_(Supplier<R> giver)
	{
		return UnlessExpression.returns_(giver);
	}
	
	//***************************************************************************
	// Public factory methods
	//***************************************************************************
	public <T> SwitchExpressionHub<T, R> switch_(T obj)
	{
		return SwitchExpressionHub.switch_(obj);
	}
	
	public NormalIfBlock<R> if_(Supplier<Boolean> expr)
	{
		return IfExpression.if_(expr);
	}
}
