# Project 1 Gold: Autograding

<a href="https://sp19.datastructur.es/materials/proj/proj1gold/proj1gold" target="_blank">Website</a>

## Randomized Testing
Here’s a fun secret: The autograder for project 1A largely relies on randomized tests. For example, our JUnit tests on Gradescope simply call random methods of your `LinkedListDeque` class and our correct implementation `LinkedListDequeSolution`, and as soon as we see any disagreement, the test fails and prints out a sequence of operations that caused the failure. In this part of the project, you’ll pretend you’re writing the autograder for the class using these same ideas.

There will be two new ideas:

* Randomized testing
* JUnit message generation

To start off this project, you should start by making sure your IntelliJ has properly imported the project. To do this, try running the `StudentArrayDequeLauncher.java` file. If it works you should see the numbers between 0 and 9 printed out (not necessarily in order). If you run into any issues, follow the instructions in lab2.

### Task I:
Next, create a JUnit test file called TestArrayDequeGold.java. Start your file with the needed import statements:
```java
import static org.junit.Assert.*;
import org.junit.Test;
```
In this file, write a single JUnit test marked with the `@Test` annotation like you did in lab3. The name of your test method does not matter. Your test should randomly call `StudentArrayDeque` and `ArrayDequeSolution` methods until they disagree on an output. You can generate random numbers using the `StdRandom` library. Use `StudentArrayDequeLauncher` as a guide, and if you copy and paste code from `StudentArrayDequeLauncher`, make sure to cite your source using an `@source` tag.

**For this project, you must use Integer as your type for the Deque**, i.e. `StudentArrayDeque<Integer>`. You should be able to find an error using only the `addFirst`, `addLast`, `removeFirst`, and `removeLast` methods, though you’re welcome to try out the other methods as well.

Your test should NOT cause a NullPointerException. Make sure that you never try to remove from an empty `ArrayDeque`, since `Integer x = ad.removeFirst()` will cause a NullPointerException. Additionally, for this project always use `Integer` instead of `int` when you are retrieving values from the deques, i.e. *do not do* `int x = ad.removeFirst()`. For an explanation of why this causes problems, please read the “Frequently Asked Questions” below.

### Task II:
Once you’ve managed to get the test consistently failing, the trickier part begins. Simply telling the student that their code fails is only going to lead to tears, sadness, confusion and late night Piazza posts. Thus, you’re going to modify your autograder so that it tells the student something useful.

To do this, we’ll take advantage of the `assertEquals(message, expected, actual)` method, which outputs a helpful message to the user.

For an example of how this method works, see `AssertEqualsStringDemo.java` in the examples folder.

Modify your `TestArrayDequeGold.java` so that the `message` parameter to `assertEquals` contains a list of operations that cause the `StudentArrayDeque` to output the wrong answer.

**The string message provided to assertEquals must be a series of method calls, where the last call in the sequence yields an incorrect return value.** For example, if adding 5 to the front, then 3 to the front, then removing from the front yields an incorrect value, then the String message passed to assertEquals should be **exactly** the following, with newlines in between each command.
```java
addFirst(5) 
addFirst(3)
removeFirst()
```
You do not need to supply the expected and actual values as part of the String message, since those are passed separately to the `assertEquals` statement as the `expected` and `actual` parameters. In other words, your message should NOT look like:
```java
addFirst(5) 
addFirst(3)
removeFirst(), student was 3, correct was 7
```
It should also not look like:
```java
addFirst(5) 
addFirst(3)
removeFirst(): 3
removeLast(): 4
```

## Tips
* **It’s probably not a good idea to write tests that compare entire Deques at once.** Suppose you write a `compareDeques(studentDeque, solutionDeque)` method that returns false. Even if this function returns false, that doesn’t give you an operation that causes a failure. It’s much easier to test the output of single operations (e.g. student.removeFirst() vs. solution.removeFirst()).

* If you insist on comparing entire Deques at once, `assertEquals` will not work the way you’d hope. For example assertEquals(deque1, deque2) will not return true if all the items are the same. You’ll need to write your own comparison method if you want to compare entire deques, though to be honest, there’s no reason to do this for this assignment.

* The StdRandom class is the easiest way to generate random numbers. See the official documentation for a list of methods.

* There’s no need to do any exception catching or throwing on this assignment (we haven’t learned this in 61B yet).

* Build a failure sequence as you perform operations! Don’t try to construct it only after a failure has been detected (this is really hard).
