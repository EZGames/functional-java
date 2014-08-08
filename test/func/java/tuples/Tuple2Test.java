package func.java.tuples;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class Tuple2Test
{
	private static Pair<String, Exception> getTuple()
	{
		return Pair.of(string, exception);
	}
	
	private final static String string = "string";
	private final static Exception exception= new IllegalArgumentException("message", new NullPointerException());
	private final static Pair<String, Exception> tuple = getTuple();
	
	// ***** test of() **********************************************************
	@Test
	public void shouldGetNonNullValuesFromOf()
	{
		Pair<String, Exception> tuple = Pair.of(string, exception);
		
		assertThat(tuple.one(), is(notNullValue()));
	}
	
	@Test
	public void shouldGetNullObjectsBackFromOf()
	{
		Pair<String, Exception> tuple = Pair.of(null, null);
		
		assertThat(tuple.one(), is(nullValue()));
		assertThat(tuple.two(), is(nullValue()));
	}
	
	// ***** test one() *********************************************************
	@Test
	public void shouldGetStringBackFromOne()
	{
		assertThat(tuple.one(), is(sameInstance(string)));
	}
	
	// ***** test two() *********************************************************
	@Test
	public void shouldGetExceptionBackFromTwo()
	{
		assertThat(tuple.two(), is(sameInstance(exception)));
	}
	
	// ***** test swap() ********************************************************
	@Test
	public void shouldGetReversedValuesFromSwap()
	{
		Pair<Exception, String> revTuple = tuple.swap();
		
		assertThat(revTuple.one(), is(tuple.two()));
		assertThat(revTuple.two(), is(tuple.one()));
	}
	
	@Test
	public void shouldGetSameTupleBackWhenDoubleSwapped()
	{
		Pair<String, Exception> doubleSwapped = tuple.swap().swap();
		
		assertThat(doubleSwapped, is(sameInstance(tuple)));
	}
}
