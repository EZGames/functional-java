package func.java.controlflow.expressions.switches;

import java.util.function.Supplier;

public abstract class Case<S extends SwitchExpression<F, R>, F, R>
{
	Case(F caseStatement, S switch_)
	{
		this.caseStatement = caseStatement;
		this.switch_ = switch_;
		giver = null;
	}
	
	abstract boolean isTrue();
	
	final R getValue()
	{
		return giver.get();
	}
	
	protected final F caseStatement;
	protected final S switch_;
	private Supplier<R> giver;
	
	
	
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public SwitchExpression<F, R> then_(Supplier<R> giver)
	{
		this.giver = giver;
		switch_.addCase(this);
		return switch_;
	}
	
	public SwitchExpression<F, R> return_(Supplier<R> giver)
	{
		return then_(giver);
	}
}
