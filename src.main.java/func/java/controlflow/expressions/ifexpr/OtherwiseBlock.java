package func.java.controlflow.expressions.ifexpr;

import java.util.function.Supplier;

public class OtherwiseBlock<R> extends IfBlock<R>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public R return_(Supplier<R> giver)
	{
		this.giver = giver;
		if_.addIfBlock(this);
		return if_.go_();
	}
	
	public R then_(Supplier<R> giver)
	{
		return return_(giver);
	}
	
	//***************************************************************************
	// Internal-use methods and constructors
	//***************************************************************************
	boolean isTrue()
	{
		return true;
	}
	
	OtherwiseBlock(IfExpression<R> if_)
	{
		super(if_);
	}	
}
