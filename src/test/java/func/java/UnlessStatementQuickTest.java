package func.java;

import static func.java.controlflow.statements.StatementHub.do_;

public class UnlessStatementQuickTest
{
	
	public static void main(String[] args)
	{
		do_(() -> System.out.println("did it")).unless_(true);
	}
	
}
