package func.java.controlflow.expressions.switches;

import java.util.function.Supplier;

// DOC TEST
public class DefaultCase<R>
{
	//***************************************************************************
	// Public constructor
	//***************************************************************************
	DefaultCase(SwitchExpression<?, R> switch_)
	{
		this.switch_ = switch_;
		giver = null;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public R then_(Supplier<R> giver)
	{
		this.giver = giver;
		switch_.setDefaultCase(this);
		return switch_.go_();
	}
	
	public R return_(Supplier<R> giver)
	{
		return then_(giver);
	}
	
	//***************************************************************************
	// Internal use methods
	//***************************************************************************
	R runAction()
	{
		return giver.get();
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private SwitchExpression<?, R> switch_;
	private Supplier<R> giver;
}

