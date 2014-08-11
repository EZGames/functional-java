package func.java.controlflow.expressions.ifexpr;

import java.util.function.Supplier;

public abstract class IfBlock<R>
{
	//***************************************************************************
	// Internal-use methods
	//***************************************************************************
	R getValue()
	{
		return giver.get();
	}
	
	abstract boolean isTrue();
	
	//***************************************************************************
	// Inherited-use constructor and fields
	//***************************************************************************
	protected IfBlock(IfExpression<R> if_)
	{
		this.if_ = if_;
	}
	
	protected final IfExpression<R> if_;
	protected Supplier<R> giver;
}
