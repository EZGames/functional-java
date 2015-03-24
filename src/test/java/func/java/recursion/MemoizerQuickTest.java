package func.java.recursion;

public class MemoizerQuickTest
{
	private Memoizer<Integer, Integer> memo = new Memoizer<>(this::weirdRecursion);
	
	public int weirdRecursion(int num)
	{
		if (num <= 1)
			return 1;
		return memo.computeIfNotExists(num - 1) 
				+ memo.computeIfNotExists(num - 1);
	}
	
	public static void main(String[] args)
	{
		MemoizerQuickTest tester = new MemoizerQuickTest();
		
		System.out.println(tester.weirdRecursion(9));
	}
}
