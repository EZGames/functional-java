functional-java
===============

A small library of helpful classes made possible and useful thanks to Java 8 functional programming.

Items included in the library:
+ A Connection helper that wraps (and extends) Connections in a way that makes it so you don't have to remember to close them. (func.java.connections.RunnableConnection)
+ An interface that allows Java to simulate tail recursion (func.java.tailrecursion.TailCall)
+ Tuples - Tuples that hold 2, 3, or 4 objects.
+ Custom control flow code - I've created some custom control flow systems. Unfortunately, there isn't much useful documentation, so consult the __QuickTest classes to see them in use to figure out how to use them
  + One similar to Ruby's unless statement, 
  + Several variations of how switches could be done.  
  + There are also new control flow EXPRESSIONS (a value is returned), like there are in a lot of functional languages.
    + There are a few switch expressions, (since they return a value, they don't have the possibility to continue flow down the next case)
    + an if expression
    + an unless expression, which uses an Optional return value

Coming Soon:
+ Documentation for the custom control flow stuff
+ Lock helper, similar to the Connection helper in that it locks and unlocks for you
