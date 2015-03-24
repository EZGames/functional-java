package func.java;

import static func.java.controlflow.expressions.ExpressionHub.returns_;

public class SwitchExpressionQuickTest
{
	public static void main(String[] args)
	{
		String output = returns_(String.class)
				.switch_(true)
					.if_(false)
						.return_(() -> "expr 1")
					.if_(false)
						.return_(() -> "expr 2")
					.if_(false)
						.return_(() -> "expr 3")
					.otherwise_()
						.return_(() -> "expr else");
		
		System.out.println(output);
	}
}
