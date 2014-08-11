package func.java;

import static func.java.controlflow.statements.switches.SwitchStatementHub.*;

//This test is outside of the controlflow package in order to test whether everything
// could be done with all the classes being private.
public class SwitchStatementQuickTest
{
	public static void main(String[] args)
	{
		switch_(5)
			.case_(3)
				.then_(SwitchStatementQuickTest::case1)
				.continue_()
			.case_(5)
				.then_(SwitchStatementQuickTest::case2pt1)
				.continue_()
			.default_()
				.then_(SwitchStatementQuickTest::defaultCase)
		.go_();
	}
	
	public static void case1()
	{
		System.out.println("case 1");
	}
	
	public static void case2pt1()
	{
		System.out.println("case 2");
	}
	
	public static void defaultCase()
	{
		System.out.println("default case");
	}
}
