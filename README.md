functional-java
===============

A small library of helpful classes made possible and useful thanks to Java 8 functional programming.

Items included in the library:
+ A Connection helper that wraps (and extends) Connections in a way that makes it so you don't have to remember to close them. (func.java.connections.RunnableConnection)
+ An interface that allows Java to simulate tail recursion (func.java.tailrecursion.TailCall)

Coming Soon:
+ Tuples - Very soon, I'll have tuples that hold 2, 3, or 4 objects.
+ Custom control flow code - I'm about halfway done making custom control flow systems.  One simulates Ruby's unless statement, another is several variations of how switches could be done.  Those are pretty much done, but I'm not releasing them until I have the switch and if EXPRESSIONS done, where a value is returned instead of (or in addition to, if you want to be picky) some action being performed when a certain case is run.
