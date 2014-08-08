package func.java.controlflow;

import java.util.ArrayList;

public abstract class SwitchStatement<T>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public abstract Case<?, T> case_(T caseStatement);
	
	public final Case<?, T> if_(T caseStatement)
	{
		return case_(caseStatement);
	}
	
	public final DefaultCase default_()
	{
		return new DefaultCase(this);
	}
	
	public final DefaultCase else_()
	{
		return default_();
	}
	
	public final DefaultCase otherwise_()
	{
		return default_();
	}
	
	@SuppressWarnings("unchecked")
	public final void go_()
	{
		Case<?, T>[] caseArr = new Case[cases.size()];
		caseArr = cases.toArray(caseArr);
		run(caseArr, 0);
	}
	
	//***************************************************************************
	// Internal use methods
	//***************************************************************************
	final void addCase(Case<?, T> kase)
	{
		cases.add(kase);
	}
	
	final void setDefaultCase(DefaultCase deFault)
	{
		defCase = deFault;
	}
	
	// *** private helpers *******************************************************
	private void run(Case<?, T>[] arr, int currIndex)
	{
		if(currIndex < arr.length)
		{
			Case<?, T> nCase = arr[currIndex];
			if(nCase.isTrue())
			{
				nCase.runAction();
				if(nCase.continues())
				{
					cuntinue(arr, currIndex + 1);
				}
			}
			else
			{
				run(arr, currIndex + 1);
			}
		}
		else if (defCase != null)
		{
			defCase.runAction();
		}
	}
	
	private void cuntinue(Case<?, T>[] arr, int currIndex)
	{
		if(currIndex < arr.length)
		{
			Case<?, T> nCase = arr[currIndex];
			nCase.runAction();
			if(nCase.continues())
			{
				cuntinue(arr, currIndex + 1);
			}
		}
		else if(defCase != null)
		{
			defCase.runAction();
		}
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private ArrayList<Case<?, T>> cases = new ArrayList<>();
	private DefaultCase defCase = null;
}
