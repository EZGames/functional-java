package func.java.stm;

import static org.junit.Assert.*;
import static ezgames.testing.matchers.exceptions.ThrowsA.throwsA;
import java.util.function.UnaryOperator;
import org.junit.Before;
import org.junit.Test;

public class CasContainerTest
{
	CasContainer<Integer> integerContainer;
	Integer startingValue = Integer.valueOf(0);
	Integer falseStartingValue = Integer.valueOf(1);
	Integer newValue = Integer.valueOf(2);
	
	@Before
	public void before()
	{
		integerContainer = new CasContainer<>(startingValue); 
	}
	
	@Test
	// update using the correct old value returns the new value
	public void testUpdate1()
	{
		Integer updateValue = integerContainer.update(startingValue, newValue);
		
		assertEquals(updateValue, newValue);
	}
	
	@Test
	// update using the incorrect old value returns the actual container value
	public void testUpdate2()
	{
		Integer updateValue = integerContainer.update(falseStartingValue, newValue);
		
		assertEquals(updateValue, startingValue);
	}
	
	@Test
	// transform will retry once if a single change happens during its update
	public void testTransform1()
	{
		//array for counting the number of times that the transform operator was run
		int[] counter = {0};
		Runnable transformation = () -> integerContainer.transform(setToNewValueAfterShortDelay(counter));
		Thread transformer = new Thread(transformation);
		
		transformer.start();
		//sleep long enough to be certain that transform has entered into the operator
		try { Thread.sleep(4); } catch (InterruptedException e) { }
		//set the container's state to a new value to cause transform to need to retry
		integerContainer.update(startingValue, falseStartingValue);
		//wait long enough for the counter to be fully updated
		try { Thread.sleep(5); } catch (InterruptedException e) { }
		
		assertEquals(counter[0], 2);
	}
	
		private UnaryOperator<Integer> setToNewValueAfterShortDelay(int[] count)
		{
			return i -> {
				count[0]++;
				try { Thread.sleep(5); } catch (InterruptedException e) { }
				return newValue; 
			};
		}
		
	@Test
	// transform eventually returns the new value
	public void testTransform2()
	{
		Integer result = integerContainer.transform(i -> newValue);
		
		assertEquals(result, newValue);
	}
	
	@Test
	// filtered transform will transform due to a correct predicate
	public void testFilteredTransform1()
	{
		Integer result = integerContainer.filteredTransform(i -> i.equals(startingValue), i -> newValue);
		
		assertEquals(result, newValue);
	}
	
	@Test
	// filtered transform will not transform due to a failing predicate
	public void testFilteredTransform2()
	{
		Integer result = integerContainer.filteredTransform(i -> i.equals(falseStartingValue), i -> newValue);
		
		//the starting value is returned when the predicate fails
		assertEquals(result, startingValue);
	}
	
	@Test
	// filtered transform retries once if a single change happens during its update
	public void testFilteredTransform3()
	{
		//array for counting the number of times that the transform operator was run
		int[] counter = {0};
		Runnable transformation = () -> integerContainer.filteredTransform(i -> true, setToNewValueAfterShortDelay(counter));
		Thread transformer = new Thread(transformation);
		
		transformer.start();
		//sleep long enough to be certain that transform has entered into the operator
		try { Thread.sleep(4); } catch (InterruptedException e) { }
		//set the container's state to a new value to cause transform to need to retry
		integerContainer.update(startingValue, falseStartingValue);
		//wait long enough for the counter to be fully updated
		try { Thread.sleep(5); } catch (InterruptedException e) { }
		
		assertEquals(counter[0], 2);
	}
	
	@Test
	// an update after the container gets a null value will throw an NPE
	public void testNull1()
	{
		// set the value to null
		integerContainer.update(startingValue, null);
		
		assertThat(this::updateContainerFromNull, throwsA(NullPointerException.class));
	}
	
		private void updateContainerFromNull()
		{
			integerContainer.update(null, newValue);
		}
	
	@Test
	// a transform after the container gets a null value will throw an NPE
	public void testNull2()
	{
		// set the value to null
		integerContainer.update(startingValue, null);
		
		assertThat(this::transformContainerFromNull, throwsA(NullPointerException.class));
	}
	
		private void transformContainerFromNull()
		{
			integerContainer.transform(i -> newValue);
		}
}
