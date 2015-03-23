package func.java.controlflow.expressions;

import java.util.Optional;
import java.util.function.Supplier;
//DOC
public class UnlessExpression<R>
{
	public static <R> UnlessExpression<R> returns_(Supplier<? extends R> giver)
	{
		return new UnlessExpression<>(giver);
	}
	
	/**
	 * @param expr - boolean expression to evaluate
	 * @return {@code Optional} of value supplied by given {@code Supplier} if
	 * {@code expr} is false, otherwise an empty {@code Optional}. An empty
	 * {@code Optional} will also be returned if the {@code Supplier} returns a
	 * {@code null}.
	 */
	public Optional<R> unless_(boolean expr)
	{
		if(!expr)
		{
			return Optional.ofNullable(giver.get());
		}
		else
		{
			return Optional.empty();
		}
	}
	
	private UnlessExpression(Supplier<? extends R> giver)
	{
		this.giver = giver;
	}
	
	private final Supplier<? extends R> giver;
}
