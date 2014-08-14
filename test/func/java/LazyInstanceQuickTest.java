package func.java;

import java.util.function.Supplier;
import func.java.lazyinstantiator.LazilyInstantiate;

public class LazyInstanceQuickTest
{
	
	public static void main(String[] args)
	{
		LazilyInstantiate<String> output = LazilyInstantiate.using(output());
		
		System.out.println("instantiator created \ncalling get()");
		System.out.println(output.get());
		System.out.println(output.get());
	}
	
	public static Supplier<String> output()
	{
		return new Supplier<String>(){
			public String get()
			{
				System.out.println("Supplier called...");
				return "ready to go";
			}
		};
	}
}
