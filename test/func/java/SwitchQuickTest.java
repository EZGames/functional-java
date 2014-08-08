package func.java;

import static func.java.controlflow.SwitchStatementFactory.*;

//This test is outside of the controlflow package in order to test whether everything
// could be done with all the classes being private.
public class SwitchQuickTest
{
	public static void main(String[] args)
	{
		switch_(5)
			.case_(3)
				.then_(SwitchQuickTest::case1)
				.continue_()
			.case_(5)
				.then_(SwitchQuickTest::case2pt1)
				.continue_()
			.default_()
				.then_(SwitchQuickTest::defaultCase)
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
