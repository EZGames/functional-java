package func.java.controlflow.statements.switches;

// DOC TEST
public class DefaultCase
{
	//***************************************************************************
	// Public constructor
	//***************************************************************************
	DefaultCase(SwitchStatement<?> switch_)
	{
		this.switch_ = switch_;
		action = null;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public DefaultCase then_(Runnable action)
	{
		if(this.action == null)
			this.action = action;
		else
		{
			//tack the new action on after the old one
			final Runnable oldAction = this.action;
			final Runnable newAction = action;
			action = new Runnable() {
					public void run()
					{
						oldAction.run();
						newAction.run();
					}
				};
		}
		return this;
	}
	
	public DefaultCase do_(Runnable action)
	{
		return then_(action);
	}
	
	public void go_()
	{
		if(action == null)
		{
			throw new IllegalStateException("Cannot finish a ParameterlessSwitch with a default case that has no action to perform");
		}
		switch_.setDefaultCase(this);
		switch_.go_();
	}
	
	//***************************************************************************
	// Internal use methods
	//***************************************************************************
	void runAction()
	{
		if(action == null)
			return;
		else
			action.run();
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private SwitchStatement<?> switch_;
	private Runnable action;
}

