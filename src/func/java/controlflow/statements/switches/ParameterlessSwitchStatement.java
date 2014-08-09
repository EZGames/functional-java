package func.java.controlflow.statements.switches;

import java.util.concurrent.Callable;

// TODO: document and test
/**
 * 
 * <p>
 * <b>Example Usage:</b><br>
 * <code>
 * <i>switch_</i>()<br>
 *	&nbsp;&nbsp; .case_(x < 3)<br>
 *	&nbsp;&nbsp; &nbsp;&nbsp; .then_(Example::dealWithX)<br>
 *	&nbsp;&nbsp; .case_(x == 5)<br>
 *	&nbsp;&nbsp; &nbsp;&nbsp; .then_(Example::dealWithY)<br>
 *	&nbsp;&nbsp; &nbsp;&nbsp; .then_(() -> x++)<br>
 *	&nbsp;&nbsp; &nbsp;&nbsp; .continue_()<br>
 *	&nbsp;&nbsp; .default_()<br>
 *	&nbsp;&nbsp; &nbsp;&nbsp; .then_(Example::dealWithZ)<br>
 *	.go_();
 * </code>
 * </p>
 * <b>Note:</b>
 * <p>
 * The methods all have a postfix underscore because, unfortunately, they almost
 * all have the same name as a Java keyword. I tried several workarounds, but 
 * eventually settled on this.  To read how I came to this, read the backstory.</p>
 * <b>Backstory:</b>
 * <p>
 * I started with silly spellings,(sort of how Java stuff uses {@code clazz} as the
 * name of a {@code Class} object), such as {@code swish} instead of {@code switch},
 * {@code kase} instead of {@code case}, etc.</p>
 * <p>
 * Then I tried just capitalizing the names, but I didn't like the break from
 * Java convention.  Then I tried prefixing with an underscore, which was okay,
 * especially since it had the added benefit of listing all the useful methods
 * before the built-in {@code Object} methods when getting the list of all the
 * methods.  But it just looked wrong when looking at it in use.  I tried using
 * postfix underscores, and those seemed to almost disappear when looking at them, 
 * so I stuck with that.</p>
 */
class ParameterlessSwitchStatement extends SwitchStatement<Callable<Boolean>>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public ParameterlessCase case_(Callable<Boolean> expr)
	{
		return new ParameterlessCase(expr, this);
	}	
	
	//***************************************************************************
	// Internal constructor
	//***************************************************************************
	ParameterlessSwitchStatement() {}
}