/**
 * This package adds control flow expressions to Java.
 * <p>
 * If you don't want to learn so much <i>about</i> what's in the package, but 
 * more so how to use it, skip ahead to <a href="#using">Using the Expressions</a>.</p>
 * <p>
 * The only control flow <i>expression</i> in Java is the ternary operator ( 
 * {@code __?__:__} ). The rest are just statements (meaning they don't "return"
 * to a value). Functional programming languages often provide control flow expressions
 * though, since functional programming is supposed to shun side-effects as much
 * as possible, which statements are guaranteed to have.  If Java is to become 
 * functional, we should make it at least as useful as a typical functional 
 * language with some control flow expressions.</p>
 * <p>
 * This package includes several different kinds of control flow expressions:
 * <ul>
 * <li><a href="#unless">Unless Expression</a> - similar to Ruby's 
 *  {@code unless} statement, but returns a value</li>
 * <li><a href="#if">If Expression</a> - essentially an expanded ternary 
 *  operator that looks like an if statement.  It provides additional branches 
 *  through "else if" blocks like an if statement, but returns a value like the 
 *  ternary operator</li>
 * <li>Switch Expressions:
 * 	<ul>
 * 	<li><a href="#compare">Typical switch</a>, where is compares the value in 
 * 	 the case to the one passed into the switch originally, but returns a 
 * 	 value instead of defining a behavior.  Because it returns a value, it's 
 * 	 not possible for the flow to drop down to the next case, though.</li>
 * 	<li><a href="#predicate">Predicate switch</a>, where the value you passed 
 * 	 into the switch gets compared or checked however you want to do it.  For 
 * 	 example, you could check whether it's less than 3.  And, obviously, the 
 * 	 value it returns is dependent on which case is triggered first.</li>
 * 	</ul>
 * </li>
 * </ul>
 * <h2 id="using">Using the Expressions</h2>
 * Every expression in this package can be started from one spot: the 
 * {@link ExpressionHub} class.  They can all be started other ways, but going
 * through {@code ExpressionHub} allows you to simply statically import its
 * methods, making for more fluent usage and only one import.</p>
 * <p>
 * <i>A note on method names:</i><br>
 * All of the relevant methods that you'll need to call have an underscore ( _ )
 * suffix. This is largely due to the fact that most of the method names are
 * also keywords in the java language. So, to prevent collision or any silly 
 * names, I used the underscore suffix throughout. This has an unintentional
 * benefit of making it easier to find what methods you should use next.  I tried
 * using an underscore prefix, which would have the added benefit of placing all
 * the methods at the top of the list, but it was somehow much harder to read
 * that way.</p>
 * <h3 id="unless">Unless Expression</h3>
 * The {@code unless} statement in Ruby is an interesting creature.  It defines
 * a default behavior that happens unless the boolean expression after unless 
 * evaluates to true.</p>
 * <p><code>
 * <i>doThis</i> unless <i>thisIsTrue</i>
 * </code></p>
 * This is a very fluent, but limited control flow statement that is equivalent
 * to<br>
 * <p><code>
 * if(!<i>thisIsTrue</i>)<br>
 *  &nbsp; <i>doThis</i>
 * </code></p>
 * <p>
 * This is exceptionally handy as a statement, but I liked it so much that I 
 * decided to make an expression out of it too, which is decidedly more
 * difficult, but still quite rewarding.</p>
 * <p>
 * The problem is that {@code unless} defines a default, but not an alternate.
 * So it uses something else that's new to Java 8: {@code Optional}.  When the
 * boolean expression evaluates as true, then the {@code unless} expression
 * returns an Empty {@code Optional}.</p>
 * <b>Example Usage</b><br>
 * <code>
 * import static func.java.controlflow.expressions.ExpressionHub.*;<br><br>
 * Optional<String> value = return_(() -> 
 */
package func.java.controlflow.expressions;