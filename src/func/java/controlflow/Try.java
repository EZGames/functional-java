package func.java.controlflow;

import java.util.concurrent.Callable;
//DOC TEST
public class Try
{
	public static <T> T nTimes(Callable<T> actionToCall, int numOfAttempts) throws Exception
	{		
		for(int currAttempt = 1; currAttempt <= numOfAttempts; currAttempt++)
		{
			try
			{
				return actionToCall.call();
			}
			catch(Exception ex)
			{
				if(currAttempt < numOfAttempts)
					continue;
				else
					throw ex;
			}
		}
		//to satisfy the compiler
		return null;
	}
	
	public static void nTimes(Runner actionToRun, int numOfAttempts) throws Exception
	{
		for(int currAttempt = 1; currAttempt <= numOfAttempts; currAttempt++)
		{
			try
			{
				actionToRun.run();
				break;
			}
			catch(Exception ex)
			{
				if(currAttempt < numOfAttempts)
					continue;
				else
					throw ex;
			}
		}
	}
	
	@FunctionalInterface
	public interface Runner
	{
		void run() throws Exception;
	}
}
