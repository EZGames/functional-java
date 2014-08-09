package func.java.controlflow.statements.switches;

public abstract class Case<S extends SwitchStatement<F>, F>
{
	public Case(F caseStatement, S switch_)
	{
		this.caseStatement = caseStatement;
		this.switch_ = switch_;
		action = null;
		continues = false;
	}
	
	final boolean continues()
	{
		return continues;
	}
	
	abstract boolean isTrue();
	
	final void runAction()
	{
		action.run();
	}
	
	protected final F caseStatement;
	protected final S switch_;
	private Runnable action;
	private boolean continues;
	
	
	
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public Case<S, F> then_(Runnable action)
	{
		if(this.action == null)
			this.action = action;
		else
		{
			//tack the new action on after the old one
			final Runnable oldAction = this.action;
			final Runnable newAction = action;
			this.action = new Runnable() {
					public void run()
					{
						oldAction.run();
						newAction.run();
					}
				};
		}
		return this;
	}
	
	public Case<S, F> do_(Runnable action)
	{
		return then_(action);
	}
	
	public S continue_()
	{
		continues = true;
		switch_.addCase(this);
		return switch_;
	}
	
	public abstract Case<S, F> case_(F expr);
	
	public Case<S ,F> if_(F expr)
	{
		return case_(expr);
	}
	
	public DefaultCase default_()
	{
		switch_.addCase(this);
		return switch_.default_();
	}
	
	public DefaultCase else_()
	{
		return default_();
	}
	
	public DefaultCase otherwise_()
	{
		return default_();
	}
	
	public void go_()
	{
		if(action == null)
		{
			throw new IllegalStateException("Cannot finish a ParameterlessSwitch with a case that has no action to perform");
		}
		switch_.addCase(this);
		switch_.go_();
	}
}
