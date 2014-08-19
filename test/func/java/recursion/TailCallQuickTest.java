package func.java.recursion;

import static func.java.recursion.TailCall.*;

public class TailCallQuickTest
{
	private static TailCall<Boolean> tcisEven(int num)
	{
		if(num == 0)
			return done(true);
		else
			return () -> tcisOdd(num - 1);
	}
	
	private static TailCall<Boolean> tcisOdd(int num)
	{
		if(num == 0)
			return done(false);
		else
			return () -> tcisEven(num - 1);
	}
	
	public static boolean isEven(int num)
	{
		return tcisEven(num).invoke();
	}
	
	private static TailCall<Integer> tcFactorial(int num)
	{
		if(num <= 1)
			return done(1);
		else if(num == 5)
			throw new RuntimeException("Thrown to show a small stack trace, despite having called the recursive function 15 times by this point");
		else
			return () -> tcFactorial(num - 1);
	}
	
	public static int factorial(int num)
	{
		return tcFactorial(num).invoke();
	}
	
	public static void main(String[] args)
	{
		System.out.println(isEven(20));
		
		System.out.println("The following stack trace should not list the recursive function more than once");
		
		try
		{
			System.out.println(factorial(20));
		}
		catch(RuntimeException ex)
		{
			ex.printStackTrace();
		}
		
		System.out.println("unfortunately, the Stream api takes up a fair amount of stack");
	}
}
