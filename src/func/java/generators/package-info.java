/**
 * Generators are a concept that I've taken from Python. It might exist in other
 * languages, but I hadn't heard of it elsewhere.
 * <p>
 * Generator functions are functions that can return values, one at a time. What
 * is returned by the generator function is the actual generator, which behaves
 * like an iterator, allowing you to go over those values, one at a time.</p>
 * <p>
 * An example of a generator function (if Java had built-in ones) is:</p>
 * <pre><code>public Generator&lt;Integer&gt; range(int from, int to) {
 *    for(int i = from; i <= to; i++)
 *       yield i;
 * }
 * </code></pre>
 * <p>
 * The {@code yield} keyword (and returning a {@code Generator}) is what makes
 * this a generator function.  {@code yield} works just like {@code return},
 * except that it just represents a step for the generator to return. You can
 * have multiple yield statements in a function, like this:</p>
 * <pre><code>public Generator&lt;Integer&gt; someNums() {
 *    yield 5;
 *    yield 37;
 *    yield -4039
 * }
 * </code></pre>
 * <p> 
 * One of the cooler things about generators is that you could use the above 
 * {@code range()} function in a for each loop to go through a set of  numbers
 * like so:</p>
 * <pre><code>for(Integer i : range(0, 10))
 * // do what you want with the numbers
 * </code></pre>
 * <p>
 * This is because generators are iterable. The generators in this package are
 * also streamable (they have the {@code stream()} method that returns a stream)
 * because generators are supposed to be used in list comprehensions, and streams
 * are the closest thing Java has to those (in some ways, I prefer streams over
 * comprehensions anyway).</p>
 * <p>
 * At this point, I have not figured out an easy way to implement a generator 
 * that can properly mimic the laziness of Python's generators. My main
 * generator type, {@link EagerGenerator} simply runs through the method that
 * produces a generator and it eagerly produces every result and adds it into a
 * collection, which is then used for producing the iterator and stream.</p>* 
 * <p>
 * Let's look at you how to use the {@code EagerGenerator}.</p>
 * <pre><code>public BuiltCollection&lt;Integer&gt; range(int from, int to) {
 *    Generator&lt;Integer&gt; generator = new EagerGenerator<>();
 *    for(int i = from; i <= to; i++)
 *       generator.yield(i);
 *    return generator.generate();
 * }
 * </code></pre>
 * <p>
 * That's how a {@code Generator} is created. As you can see, it's pretty close
 * to earlier code, but now we have to work with the generator object
 * throughout: creating it, calling {@code yield} on it, and returning it.</p>
 * <p>
 * You may notice that the {@link Generator} classes implement the 
 * {@link func.java.BuiltCollection BuiltCollection} interface, which is what is
 * returned by the {@code generate} method. This does not necessarily mean that 
 * the specific {@code Generator} will return itself. In many cases, it will, 
 * but you should still call the {@code generate} method and only return a 
 * {@code BuiltCollection} type, since it prevents you from calling the 
 * {@code yield} methods after you're done building the generated collection.</p>
 * <p>
 * You can then use the returned {@code func.java.BuiltCollection} to iterate 
 * through or, with a stream, to work with the generated values.</p>
 * <p>
 * At this point, you may be wondering how this is helpful, since you could just
 * build a collection in the function and return that instead.  And, in many
 * cases, you could do that.  But even the {@code EagerGenerator} has a lazy
 * piece up its sleeve. Since the generator takes in {@code Supplier}s of 
 * objects instead of straight objects, you can have a complex calculation in 
 * the supplier and save it until you need it. Otherwise, yes, the Generator is
 * a highly limited facade of a Collection.</p>
 * <p>
 * I'm still looking into being able to the possibility of setting up an easy
 * way to make the generator completely lazy, but low hopes.</p>
 * <p>
 * On a positive note, though, I figured out how to do an
 * {@link InfiniteGenerator}, but with some work and a fair bit of forethought
 * on the user's part.  See its documentation for how to exploit it.</p>
 */
package func.java.generators;