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
