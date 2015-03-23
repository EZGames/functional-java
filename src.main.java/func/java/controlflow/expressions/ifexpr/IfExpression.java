package func.java.controlflow.expressions.ifexpr;

import java.util.ArrayList;
import java.util.function.Supplier;
//DOC
public class IfExpression<R>
{
	//***************************************************************************
	// Public static factories
	//***************************************************************************
	public static <R> NormalIfBlock<R> if_(Supplier<Boolean> expr)
	{
		IfExpression<R> if_ = new IfExpression<>();
		return NormalIfBlock.if_(expr, if_);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public NormalIfBlock<R> otherwise_if_(Supplier<Boolean> expr)
	{
		return NormalIfBlock.if_(expr, this);
	}
	
	public NormalIfBlock<R> else_if_(Supplier<Boolean> expr)
	{
		return otherwise_if_(expr);
	}
	
	public OtherwiseBlock<R> otherwise_()
	{
		return new OtherwiseBlock<R>(this);
	}
	
	public OtherwiseBlock<R> else_()
	{
		return otherwise_();
	}
	
	//***************************************************************************
	// Internal-use methods
	//***************************************************************************
	R go_()
	{
		for(IfBlock<R> block : ifBlocks)
		{
			if(block.isTrue())
			{
				return block.getValue();
			}
		}
		//just satisfying the compiler with this. With the possible work flows, 
		// the last block in the array is guaranteed to be true because it'll be 
		// an OtherwiseBlock, which always returns true for isTrue()
		return null;
	}
	
	void addIfBlock(IfBlock<R> ifBlock)
	{
		ifBlocks.add(ifBlock);
	}
	
	//***************************************************************************
	// Private constructor and fields
	//***************************************************************************
	private IfExpression()
	{
		ifBlocks = new ArrayList<>();
	}
	
	private final ArrayList<IfBlock<R>> ifBlocks;
}
