package func.java.controlflow.expressions.ifexpr;

import java.util.function.Supplier;

public class NormalIfBlock<R> extends IfBlock<R>
{	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public IfExpression<R> return_(Supplier<R> giver)
	{
		this.giver = giver;
		if_.addIfBlock(this);
		return if_;
	}
	
	public IfExpression<R> then_(Supplier<R> giver)
	{
		return return_(giver);
	}
	
	//***************************************************************************
	// Internal-use static factories methods
	//***************************************************************************
	static <R> NormalIfBlock<R> if_(Supplier<Boolean> expr, IfExpression<R> if_)
	{
		return new NormalIfBlock<>(expr, if_);
	}
	
	boolean isTrue()
	{
		return expr.get();
	}
	
	//***************************************************************************
	// Private constructors and fields
	//***************************************************************************
	private NormalIfBlock(Supplier<Boolean> expr, IfExpression<R> if_)
	{
		super(if_);
		this.expr = expr;
	}
	
	private Supplier<Boolean> expr;
}
