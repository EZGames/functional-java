package func.java.controlflow.expressions;

import java.util.function.Supplier;
import func.java.controlflow.expressions.ifexpr.IfExpression;
import func.java.controlflow.expressions.ifexpr.NormalIfBlock;
import func.java.controlflow.expressions.switches.SwitchExpressionHub;

/**
 * {@code ExpressionHub} is a starting point for all the control flow expressions
 * in the functional-java library.  
 * @param <R>
 */
public class ExpressionHub<R>
{
	//***************************************************************************
	// Public static factory methods
	//***************************************************************************
	public static <R> ExpressionHub<R> returns_(Class<R> clazz)
	{
		return new ExpressionHub<>();
	}
	
	public static <R> UnlessExpression<R> return_(Supplier<? extends R> giver)
	{
		return UnlessExpression.returns_(giver);
	}
	
	//***************************************************************************
	// Public factory methods
	//***************************************************************************
	public <T> SwitchExpressionHub<T, ? extends R> switch_(T obj)
	{
		return SwitchExpressionHub.switch_(obj);
	}
	
	public NormalIfBlock<? extends R> if_(Supplier<Boolean> expr)
	{
		return IfExpression.if_(expr);
	}
}
