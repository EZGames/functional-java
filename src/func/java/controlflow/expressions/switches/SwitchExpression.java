package func.java.controlflow.expressions.switches;

import java.util.ArrayList;

public abstract class SwitchExpression<F, R>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public abstract Case<?, F, R> case_(F caseStatement);
	
	public final Case<?, F, R> if_(F caseStatement)
	{
		return case_(caseStatement);
	}
	
	public final DefaultCase<R> default_()
	{
		return new DefaultCase<>(this);
	}
	
	public final DefaultCase<R> else_()
	{
		return default_();
	}
	
	public final DefaultCase<R> otherwise_()
	{
		return default_();
	}
	
	//***************************************************************************
	// Internal use methods
	//***************************************************************************
	@SuppressWarnings("unchecked")
	final R go_()
	{
		Case<?, F, R>[] caseArr = new Case[cases.size()];
		caseArr = cases.toArray(caseArr);
		return run(caseArr, 0);
	}
	
	final void addCase(Case<?, F, R> kase)
	{
		cases.add(kase);
	}
	
	final void setDefaultCase(DefaultCase<R> deFault)
	{
		defCase = deFault;
	}
	
	// *** private helpers *******************************************************
	private R run(Case<?, F, R>[] arr, int currIndex)
	{
		if(currIndex < arr.length)
		{
			Case<?, F, R> nCase = arr[currIndex];
			if(nCase.isTrue())
			{
				return nCase.getValue();
			}
			else
			{
				return run(arr, currIndex + 1);
			}
		}
		else if (defCase != null)
		{
			return defCase.runAction();
		}
		else
		{
			return null;
		}
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private ArrayList<Case<?, F, R>> cases = new ArrayList<>();
	private DefaultCase<R> defCase = null;
}
