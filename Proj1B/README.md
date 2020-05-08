# Project 1B: Applying and Testing Data Structures version 1.0
<a href="https://sp19.datastructur.es/materials/proj/proj1b/proj1b" target="_blank">Website</a> 

## Task 1: Deque Interface
This first task is going to be a little tedious, but it won’t take long.

Create an interface in a new file named `Deque.java` that contains all of the methods that appear in both `ArrayDeque` and `LinkedListDeque`. See the project 1a spec for a concise list. In IntelliJ, use “New->Java Class”. IntelliJ will assume you want a class, so make sure to replace the `class` keyword with `interface`.

Create this interface and add all the methods. For the `isEmpty()` method, give it a `default` implementation, which returns `true` if the `size()` is `0`. Notice that since both your `LinkedListDeque` and `ArrayDeque` probably use an implementation like this, now that we have this default implementation, we could delete the duplicate implementations inside of the two aforementioned concrete classes (you can go ahead and delete them if you like).

Modify your `LinkedListDeque` and/or `ArrayDeque` so that they implement the `Deque` interface by adding `implements Deque<Item>` to the line declaring the existence of the class. If you used something other than `Item` for your generic type parameter, use that instead. Add `@Override` tags to each method that overrides a `Deque` method.

Note: If you’re using the provided solution for `LinkedListDeque`, which relies on some inheritance black magic, your class definition should look like `public class LinkedListDeque<Item> extends LinkedList<Item> implements Deque<Item>`.

## Task 2: wordToDeque
Create a new file called `Palindrome.java`, and add a method with the signature shown below:

* `public Deque<Character> wordToDeque(String word)`

**Don’t write any code yet for this method.** For now, just have it return `null` so that `Palindrome.java` can compile.

Given a `String`, `wordToDeque` should return a `Deque` where the characters appear in the same order as in the String. For example, if the word is “persiflage”, then the returned `Deque` should have ‘p’ at the front, followed by ‘e’, and so forth. **Don’t implement wordToDeque yet!**

Uncomment the code in `TestPalindrome` and run the test contained in the file (e.g. by right-clicking on it and picking `Run TestPalindrome`). You should fail the provided test. Your goal is to now pass this test by correctly implementing `wordToDeque`. Once you’ve passed the test, move on to the next part of this assignment. Make sure that you don’t delete the weird line `static Palindrome palindrome = new Palindrome();`. It’s not useful for this task of the assignment, but it’ll be necessary later.

*Tip: Search the web to see how to get the i-th character in a String.*

*Tip: Inserting chars into a Deque<Character> is just like inserting ints into a LinkedListDeque<Integer>.*

*Note: The careful reader of testWordToDeque might wonder why we didn’t just create a correct Deque and then call assertEquals. The reason is that our Deque class does not provide an equals method and thus it won’t work the way you expect. We’ll be talking about this in class soon.*

**Tip: If you’re failing your own test and can’t figure why, remember you have a debugger. Use it! Don’t just stare at your code looking for the bug. That’s too slow and tedious.**

## Task 3: isPalindrome
### Task 3A: isPalindrome Testing
Now that you’re passing the test and getting to enjoy the nice green glow of the success bar, let’s break things again.

Modify your `Palindrome.java` so that it now has a second method with the signature below.

* `public boolean isPalindrome(String word)`

For now, have it return a dummy value. A dummy value is just some arbitrary thing you select, i.e. either true or false. Before we write the method itself, we’re going to write a test. Let’s start by discussing what `isPalindrome` is supposed to do.

The `isPalindrome` method should return `true` if the given word is a palindrome, and `false` otherwise. A palindrome is defined as a word that is the same whether it is read forwards or backwards. For example “a”, “racecar”, and “noon” are all palindromes. “horse”, “rancor”, and “aaaaab” are not palindromes. *Any word of length 1 or 0 is a palindrome.*

‘A’ and ‘a’ should not be considered equal; you don’t need to do anything special for capital letters to work properly. In fact, if you forget that capital letters exist, your code will work fine.

Add at least one test to `TestPalindrome` that tests the `isPalindrome` method. You’ll probably find the `assertTrue` and `assertFalse` methods to be useful. You’re also welcome to use any other methods in the JUnit documentation. Ideally, you should write several tests, and not just one, but it’s up to you. It’s ok to have multiple asserts in one test, though don’t go too wild. Make sure to annotate your tests with `@Test`, otherwise JUnit won’t run your tests.

When you run TestPalindrome again, your code should fail the (hopefully multiple) tests.

*Tip: As an example, `assertFalse(palindrome.isPalindrome("cat"));` tests to ensure that “cat” is not considered a palindrome.*

*Tip: If you’re looking for more interesting things to test, read this section carefully for any interesting corner cases, and ensure that your tests check those corner cases.*

### Task 3B: isPalindrome
Now that you have a failing test, implement the `isPalindrome` method. Use your `wordToDeque` method to give yourself an easier time. While you can technically not use a `Deque` at all, we strongly encourage you to do so. It’s a good exercise in understanding how your choice of data structures (in this case, `Deque`) will have a profound effect on how you write your code.

Once you’ve passed your own tests, you’re ready to move on. Keep in mind that our autograder is going to be very quiet, so you’ll want to make sure your tests are thorough so that you feel good about your code. At the very least, you should have at least one test that checks that some word is a palindrome, and one that checks that some word is not a palindrome, as well as two interesting corner cases.

*Tip: Consider recursion. There’s a really beautiful solution that uses recursion. You’ll need to create a private helper method for this to work.*

*Tip: Don’t use the `get` method of Deque. That will just make things unnecessarily complicated.*

*Just for fun: Uncomment the code in the provided `PalindromeFinder.java` class and you’ll get a list of all palindromes of length 4 or more in English (assuming you also downloaded the provided words file).*

**Tip: If you’re failing your own test and can’t figure why, remember you have a debugger. Use it! Don’t just stare at your code looking for the bug. That’s too slow and tedious.**

## Task 4: Generalized Palindrome and OffByOne
For this task, you will do six things. Our suggested order is given below:

* Create a class called `OffByOne` that implements `CharacterComparator`.
* Add tests to `TestOffByOne` for the `equalChars` method in the `OffByOne` class.
* Complete the `equalChars` method and verify that it works.
* Add a new method that overloads `isPalindrome`.
* Add tests to `TestPalindrome` that tests your new method in `isPalindrome`. It’s ok to use `new OffByOne()` for these tests.
* Complete the new method in `isPalindrome` and verify that it works.

However, you’re welcome to do them in any order you choose. For this task, rather than carefully enumerating everything you’re supposed to do, as in the tasks above, we will simply give a general description of your goal.

In this task, your ultimate goal is to add a third public method to your `Palindrome` class with the following signature:
* `public boolean isPalindrome(String word, CharacterComparator cc)`

The method will return `true` if the word is a palindrome according to the character comparison test provided by the `CharacterComparator` passed in as argument `cc`. A character comparator is defined as shown below:
```java
/** This interface defines a method for determining equality of characters. */
public interface CharacterComparator {

    /** Returns true if characters are equal by the rules of the implementing class. */
    public boolean equalChars(char x, char y);
}
```
For this task, you’ll also create a class called `OffByOne.java`, which should implement `CharacterComparator` such that `equalChars` returns `true` for characters that are different by exactly one. For example the following calls to `obo` should return `true`. Note that characters are delineated in Java by single quotes, in contrast to Strings, which use double quotes.
```java
OffByOne obo = new OffByOne();
obo.equalChars('a', 'b');  // true
obo.equalChars('r', 'q');  // true
```
However, the three calls below should return `false`:
```java
obo.equalChars('a', 'e');  // false
obo.equalChars('z', 'a');  // false
obo.equalChars('a', 'a');  // false
```
*Note: Characters in Java include non-alphabetical characters. For example ‘%’ and ‘&’ are off by one. This might seem strange (especially since they’re not even next to each other on the keyboard), but char values in Java are really just integers. For example ‘%’ is actually just another way of writing 37, and ‘&’ is another way of writing 38. That is, the code below is valid and the first print statement will print 38. The second two print statements are exactly equivalent, and they both simply print 1.*
```java
int x = '&';
System.out.println(x);         // prints 38
System.out.println(38 - 37);   // prints 1
System.out.println('&' - '%'); // prints 1
```
*Thus the method call below should return true:*
```java
obo.equalChars('&', '%');
```
*Similarly, ‘a’ and ‘B’ are NOT off by one, since ‘a’ - ‘B’ is 31. If you’re curious about the specific values for many familiar characters, see the table at the bottom of this wikipedia article.*

**To allow for odd length palindromes, we do not check the middle character for equality with itself.** So “flake” is an off-by-1 palindrome, even though ‘a’ is not one character off from itself.

As with our earlier `isPalindrome` method, any zero or 1 character word is considered a palindrome.

*Tip: Make sure to include `@Override` when implementing `equalChars`. While it has no effect on the function of your program, it’s a good habit for the reasons detailed in lecture.*

*Tip: To calculate the difference between two chars, simply compute their difference in java. For example `int diff = 'd' - 'a';` would return `diff` as `-3`.*

*Tip: Even though a good solution for Palindrome and OffByOne should not explicitly worry about non-alphabetical characters or uppercase letters, your tests could hypothetically be run on a poor solution, and thus in that case should try to cause errors that only apply to non-alphabetical characters.*

*Just for fun: Try printing out all off-by-one palindromes of length 4 or more in English (assuming you also downloaded the provided dictionary) by modifying `PalindromeFinder.java`. For example “flake” is an off-by-1 palindrome since “f” and “e” are one letter apart, and “k” and “l” are one letter apart.*

**Tip: If you’re failing your own test and can’t figure why, remember you have a debugger. Use it! Don’t just stare at your code looking for the bug. That’s too slow and tedious.**

## Task 5: OffByN
In this last part, you will implement a class `OffByN`, which should implement the `CharacterComparator` interface, as well as a single argument constructor which takes an integer. In other words, the callable methods and constructors will be:

* `OffByN(int N)`
* `equalChars(char x, char y)`

The `OffByN` constructor should create objects whose `equalChars` method return `true` for characters that are off by `N`. For example the call to equal chars below should return `true`, since “a” and “f” are off by 5 letters, but the second call would return `false` since “f” and “h” are off by 4 letters.
```java
OffByN offBy5 = new OffByN(5);
offBy5.equalChars('a', 'f');  // true
offBy5.equalChars('f', 'a');  // true
offBy5.equalChars('f', 'h');  // false
```
For this task, if you choose to write tests, you’ll need to make your own test file. Make sure to include the appropriate `import` statements, which you can copy and paste from our two provided test files. Unlike the other test files, you’re welcome to use `new` in your tests. Due to technical limitations we will not test your `TestOffByN.java` file if you create one, but you still might find it useful to create such tests.

*Just-for-fun: Try modifying `PalindromeFinder.java` so that it outputs a list of offByN palindromes for the N of your choosing.*

*Just-for-more-fun: For what `N` are there the most palindromes in English? What is the longest offByN palindrome for any `N`?*
