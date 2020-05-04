# Project 1A: Data Structures

## Introduction
In project 1A, we will build implementations of a “Double Ended Queue” using both lists and arrays. In project 1B (next week), we will learn how to write our own tests for those data structures, and will use the Double Ended Queue to solve some small real world probelms.

In this part of the project you will create exactly two Java files: `LinkedListDeque.java` and `ArrayDeque.java`, with public methods listed below.

Unlike project 0, we will provide relatively little scaffolding. In other words, we’ll say what you should do, but not how.

For this project, you must work alone! Please carefully read the Policy on Collaboration and Cheating to see what this means exactly.

**We strongly encourage you to switch to IntelliJ for this project.** While it’s not absolutely required, you will have a better time. The ability to visually debug your code can be incredibly useful, it’s also nice to have a development environment that catches syntax errors as you are typing, and it avoids the need to type javac and java (or pressing arrow keys) a bajillion times. If you need a refresher on how to import a project, you can follow the Intellij setup guide

Additionally, **we will be enforcing style**. You must follow the style guide or you will lose points on the autograder.

## Getting the Skeleton Files
As with project 0, you should start by downloading the skeleton files. The directions are repeated below.

To do this, head to the folder containing your copy of your repository. For example, if your login is ‘s101’, then head to the ‘sp19-s101’ folder (or any subdirectory).

Now we’ll make sure you have the latest copy of the skeleton files with by using `git pull skeleton master`. If you’re using a newer version of git, you might need to do `git pull skeleton master -allow-unrelated-histories`.

If you find yourself faced with a strange text editor or a merge conflict, see the project 0 directions for how to proceed.

Once you’ve successfully merged, you should see a proj1a directory appear with files that match the skeleton repository.

If you get some sort of error, STOP and either figure it out by carefully reading the git guide or seek help at OH or Piazza. You’ll potentially save yourself a lot of trouble vs. guess-and-check with git commands. If you find yourself trying to use commands recommended by Google like `force push`, don’t. **Don’t use force push, even if a post you found on Stack Overflow says to do it!**

The only provided file in the skeleton is `LinkedListDequeTest.java`. This file provides examples of how you might write tests to verify the correctness of your code. We strongly encourage you try out the given tests, as well as to write at least one of your own.

You may find writing tests quite annoying. However, in part B of this project, we will use a library called JUnit, which will make writing tests much easier and organized. We will talk more about testing in a later section.

## The Deque API
The double ended queue is very similar to the SLList and AList classes that we’ve discussed in class. Here is a definition from cplusplus.com.

> Deque (usually pronounced like “deck”) is an irregular acronym of double-ended queue. Double-ended queues are sequence containers with dynamic sizes that can be expanded or contracted on both ends (either its front or its back).

Specifically, any deque implementation must have exactly the following operations:

* `public void addFirst(T item)`: Adds an item of type `T` to the front of the deque.
* `public void addLast(T item)`: Adds an item of type `T` to the back of the deque.
* `public boolean isEmpty()`: Returns true if deque is empty, false otherwise.
* `public int size()`: Returns the number of items in the deque.
* `public void printDeque()`: Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
* `public T removeFirst()`: Removes and returns the item at the front of the deque. If no such item exists, returns null.
* `public T removeLast()`: Removes and returns the item at the back of the deque. If no such item exists, returns null.
* `public T get(int index)`: Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!

Your class should accept any generic type (not just integers). For information on creating and using generic data structures, see lecture 5. Make sure to pay close attention to the rules of thumb on the last slide about generics.

## Project Tasks
### 1. Linked List Deque
*Note: We covered everything needed in lecture to do this part in Lectures 4 and 5 (1/30 and 2/1).*

Create a file called `LinkedListDeque.java` in your project directory.

As your first deque implementation, you’ll build the `LinkedListDeque` class, which will be linked list based.

Your operations are subject to the following rules:

* `add` and `remove` operations must not involve any looping or recursion. A single such operation must take “constant time”, i.e. execution time should not depend on the size of the deque.
* `get` must use iteration, not recursion.
* `size` must take constant time.
* The amount of memory that your program uses at any given time must be proportional to the number of items. For example, if you add 10,000 items to the deque, and then remove 9,999 items, the resulting size should be more like a deque with 1 item than 10,000. Do not maintain references to items that are no longer in the deque.

Implement all the methods listed above in “The Deque API” section.

In addition, you also need to implement:

* `public LinkedListDeque()`: Creates an empty linked list deque.
* `public LinkedListDeque(LinkedListDeque other)`: Creates a deep copy of `other`.
   * Creating a deep copy means that you create an entirely new `LinkedListDeque`, with the exact same items as `other`. However, they should be different objects, i.e. if you change `other`, the new `LinkedListDeque` you created should not change as well. (Edit 2/6/2018: A walkthrough that provides a solution for this copy constructor is available at https://www.youtube.com/watch?v=JNroRiEG7U4)
* `public T getRecursive(int index)`: Same as get, but uses recursion.

You may add any private helper classes or methods in `LinkedListDeque.java` if you deem it necessary.

While this may sound simple, there are many design issues to consider, and you may find the implementation more challenging than you’d expect. Make sure to consult the lecture on doubly linked lists, particularly the slides on sentinel nodes: two sentinel topology, and circular sentinel topology. I prefer the circular approach. You are not allowed to use Java’s built in LinkedList data structure (or any data structure from `java.util.*`) in your implementation.

### 2. Array Deque
*Note: We’ll have covered everything needed in lecture to do this part by Feb 4th (lecture 6).*

Create a file called `ArrayDeque.java` in your project directory.

As your second deque implementation, you’ll build the `ArrayDeque` class. This deque must use arrays as the core data structure.

For this implementation, your operations are subject to the following rules:

* `add` and `remove` must take constant time, except during resizing operations.
* `get` and `size` must take constant time.
* The starting size of your array should be 8.
* The amount of memory that your program uses at any given time must be proportional to the number of items. For example, if you add 10,000 items to the deque, and then remove 9,999 items, you shouldn’t still be using an array of length 10,000ish. For arrays of length 16 or more, your usage factor should always be at least 25%. For smaller arrays, your usage factor can be arbitrarily low.

Implement all the methods listed above in “The Deque API” section.

In addition, you also need to implement:

* `public ArrayDeque()`: Creates an empty array deque.
* `public ArrayDeque(ArrayDeque other)`: Creates a deep copy of `other`.
   * Creating a deep copy means that you create an entirely new `ArrayDeque`, with the exact same items as `other`. However, they should be different objects, i.e. if you change `other`, the new `ArrayDeque` you created should not change as well.
   
You may add any private helper classes or methods in `ArrayDeque.java` if you deem it necessary.

We *strongly* recommend that you treat your array as circular for this exercise. In other words, if your front pointer is at position zero, and you `addFirst`, the front pointer should loop back around to the end of the array (so the new front item in the deque will be the last item in the underlying array). This will result in far fewer headaches than non-circular approaches. See the project 1 demo slides for more details.

Correctly resizing your array is **very tricky**, and will require some deep thought. Try drawing out various approaches by hand. It may take you quite some time to come up with the right approach, and we encourage you to debate the big ideas with your fellow students or TAs. Make sure that your actual implementation **is by you alone**.

## Testing
Testing is an imporant part of code writing in industry and academia. It is an essential skill that can prevent monetary loss and hazardous bugs in industry, or in your case, losing points. Learning how to write good, comprehensive unit tests, and developing a good habit of always testing code before shipping are some core objectives of CS 61B.

We will focus more on testing in the next part of this project, project 1B. For now, we have provided you a very simple sanity check, `LinkedListDequeTest.java`. To use the sample testing file, you must uncomment the lines in the sample tests. Only uncomment a test once you have implemented all of the methods used by that test (otherwise it won’t compile). Execute the main method to run the tests. When testing your project, **remember you can use the visualizer from inside IntelliJ**!

You will not submit `LinkedListDequeTest.java`. It is for your benefit to write more comprehensive tests for both LinkedListDeque and ArrayDeque before submitting. Note, passing the given tests in `LinkedListDequeTest`.java does *not necessarily* mean that you will pass all of the autograder tests or receive full credit.

## Deliverables
`LinkedListDeque.java`

`ArrayDeque.java`
