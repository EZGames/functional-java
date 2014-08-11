package func.java;

import static func.java.controlflow.expressions.ExpressionHub.returns_;
import java.util.Optional;

public class UnlessExpressionQuickTest
{
	public static void main(String[] args)
	{
		Optional<String> output = returns_(() -> "output").unless_(true);
		
		System.out.println(output.orElse("no go"));
	}
}
