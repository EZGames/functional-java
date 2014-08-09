package func.java;

import func.java.controlflow.expressions.switches.SwitchExpressionFactory;

public class SwitchExpressionQuickTest
{
	public static void main(String[] args)
	{
		String output = 
				SwitchExpressionFactory.<String>switch_()
					.if_(() -> false)
						.return_(() -> "expr 1")
					.if_(() -> false)
						.return_(() -> "expr 2")
					.if_(() -> false)
						.return_(() -> "expr 3")
					.otherwise_()
						.return_(() -> "expr else");
		
		System.out.println(output);
	}
}
