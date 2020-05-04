# Lab 2: Unit Testing with JUnit and IntLists

## Introduction
In this lab, you will learn about basic IntelliJ features, destructive vs. non-destructive methods, and IntLists.

Your job for this assignment is to run through the debugging exercises and to create methods for `IntList.java`.

## Application: IntLists
### Introduction/Review of IntLists
As discussed in Monday’s lecture, an `IntList` is our CS61B implementation for a naked recursive linked list of integers. Each `IntList` has a `first` and `rest` variable. The `first` is the `int` element contained by the node, and the `rest` is the next chain in the list (another `IntList`!).

In the IntList directory for this lab, we’ve provided a much larger `IntList.java` than the one we created in class. It has five important new static methods, two of which you’ll fill in:

* `void dSquareList(IntList L)`: modifies the list so that all of its elements are squared.
* `IntList squareListIterative(IntList L)`: returns a version of the list with all elements squared, using iteration. The list is not modified.
* `IntList squareListRecursive(IntList L)`: returns a version of the list with all elements squared, using recursion. The list is not modified.
* `dcatenate(IntList A, IntList B)`: returns a list consisting of all elements of A, followed by all elements of B. May modify A. To be completed by you.
* `catenate(IntList A, IntList B)`: returns a list consisting of all elements of A, followed by all elements of B. May not modify A. To be completed by you.

The class also includes additional methods that you are not supposed to read or understand. They are omitted from being described in this lab.

### Destructive vs. Non-Destructive
For a given piece of desired functionality, there are often many ways to write the same function. For example, consider the task of squaring every item in a list of numbers. In the provided `IntList` java.

Let’s consider a method `dSquareList` that will “destructively” square every item in a list (similar to the extra problem from discussion in week 2.
```
IntList origL = IntList.of(1, 2, 3)
dSquareList(origL);
// origL is now (1, 4, 9)
```
By destructive, we mean that the original list changes. The term “mutative” is also sometimes used (as in discussion from week 2). By contrast a non-destructive method like `squareListIterative` does not affect the original list, e.g.
```
IntList origL = IntList.of(1, 2, 3)
IntList squaredList = squareListIterative(origL);
// origL is still (1, 2, 3)
// squaredList is (1, 4, 9)
```
### dSquareList Implementation
Here is one possible implementation of `dSquareList()`, along with a call to `dSquareList`:
```
public static void dSquareList(IntList L) {
    while (L != null) {
        L.first = L.first * L.first;
        L = L.rest;
    }
}

IntList origL = IntList.of(1, 2, 3)
dSquareList(origL);
// origL is now (1, 4, 9)
```
The reason that `dSquareList` is destructive is because we change the values of the **original input** `IntList`. As we go along, we square each value, and the action of changing the internal data persists.

It is also important to observe that the bits in the `origL` box do not change, i.e. the variable still points to exactly the same object in memory when `dSquareList` completes.

To ensure that these ideas all make sense, set a breakpoint in `dSquareList` and run the `IntListTest` class in *debug* mode. **Use the Java Visualizer** (which you installed in lab2setup) **to visualize the IntList as you step through with the debugger.** The visualizer is an icon of a blue coffee cup with an eye, and is the tab next to the “Console” tab in the debugger panel). See the CS 61B plugin guide if you can’t figure out how to get the visualizer to show.

NOTE: The choice to return void rather than a pointer to `L` was an arbitrary decision. Different languages and libraries use different conventions (and people get quite grumpy about which is the “right” one).

### Non-destructive Squaring
The provided `squareListIterative()` and `squareListRecursive()` methods are both non-destructive. That is, the underlying `IntList` passed into the methods does **not** get modified, and instead a fresh new copy is modified and returned.

Look at `squareListIterative` and `squareListRecursive`. Ideally, you should spend some time trying to really understand them, including possibly using the visualizer. However, if you don’t have time this iterative version is much messier.

The iterative version of a non-destructive method is often (but not always) quite a bit messier than the recursive version, since it takes some careful pointer action to create a new `IntList`, build it up, and return it.

### Test Code
Optionally, look at the test method `testDSquareList` in `IntListTest.java`. This gives you a feeling for how tests will be written in this course moving forwards, and we’ll be requiring you to write tests starting in next week’s lab. You might also find them handy when writing your project 1A next week.

One major difference that the tests rely upon is that we’ve added a method to our `IntList` class called `of` that makes it easier to create IntLists. For example, to create an `IntList` containing the numbers 0, 1, 2, and 3, we could use the method as follows:
```
IntList myList = IntList.of(0, 1, 2, 3);
// Creates the IntList 0 -> 1 -> 2 -> 3 -> null
```
* `myList.first` returns 0
* `myList.rest` returns 1 -> 2 -> 3 -> null
* `myList.rest.rest.rest` returns 3 -> null
* `myList.rest.rest.rest.rest` returns null
* Pop quiz: what happens for `myList.rest.rest.rest.rest.rest`? (Hint: it doesn’t successfully return something)

Observe that the `IntList.of()` method makes it much easier to create IntLists compared to the brute force approach.
```
IntList myList = new IntList(0, null);
myList.rest = new IntList(1, null);
myList.rest.rest = new IntList(2, null);
myList.rest.rest.rest = new IntList(3, null);
// One line of using IntList.of() can do the job of four lines!
```

### Implementing Destructive vs. Non-destructive Methods
To complete the lab, you should write methods `dcatenate` and `catenate` as described below. You may find the squaring methods from above to be useful as you write your code.

Both methods take in two IntLists, and concatenate them together. So `catenate(IntList A, IntList B)` and `dcatenate(IntList A, IntList B)` both result in an `IntList` which contains the elements of `A` followed by the elements of `B`.

The only difference between these two methods is that `dcatenate` modifies the original `IntList A` (i.e. it’s destructive) and `catenate` does not.

To complete the lab:
* Fill in one of `dcatenate()` or `catenate()`, and run them against our tests. Revise your code until it passes our tests.
* Repeat for the method you haven’t yet completed. (We recommend you do one first and finish it before you start the next, because then you’ll be able to take advantage of the similar logic).

`IntList` problems can be tricky to think about, and there are always several approaches which can work. Don’t be afraid to pull out pen and paper or go to the whiteboard and work out some examples! If you get stuck, drawing out the pointers can probably stimulate you back onto the path of progress. And, as always, the debugger (and visualizer) is a great option!

Feel free to use either recursion or iteration. For extra practice, try both!

It’s also often useful to first think about base cases (when `A` is `null`, for example) - this works especially well for building up a recursive solution. In other words, write up a solution that would work for the base case, then stop and think about how to expand this solution into something that works for other bigger cases.
