package func.java;

import static func.java.controlflow.expressions.ExpressionHub.*;

public class IfExpressionQuickTest
{
	public static void main (String[] args)
	{
		String output = returns_(String.class)
				.if_(() -> true)
					.return_(() -> "expr one")
				.otherwise_if_(() -> false)
					.then_(() -> "expr two")
				.otherwise_()
					.return_(() -> "expr three");
		
		System.out.println(output);
	}
}
