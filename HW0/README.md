# HW 0: A Java Crash Course 
<a href="https://sp19.datastructur.es/materials/hw/hw0/hw0" target="_blank">Website</a>


## Creative Exercise 1a: Drawing a Triangle
Your goal is to create a program that prints the following figure. Your code should use loops (i.e. shouldn’t just be five print statements, that’s no fun).
```
*
**
***
****
*****
```
You may find `System.out.print` to be a useful alternative to `System.out.println`. The difference is that `System.out.print` does not include an automatic newline.


## Creative Exercise 1b: DrawTriangle
Starting from the default program at our <a href="https://cscircles.cemc.uwaterloo.ca//java_visualize/#" target="_blank">Java visualizer</a>, create a program with one additional method (in addition to the default main method that is there when you open the visualizer).

Name this new method `DrawTriangle` and give it a return type of `void` (this means that it doesn’t return anything at all).

The `DrawTriangle` method should take one parameter named `N`, and it should print out a triangle exactly like your triangle from exercise 1a, but `N` asterisks tall instead of 5.

After writing `DrawTriangle`, modify the main function so that it calls `DrawTriangle` with `N = 10`.


## Exercise 2
Using everything you’ve learned so far on this homework, you’ll now create a function with the signature `public static int max(int[] m)` that returns the maximum value of an int array. You may assume that all of the numbers are greater than or equal to zero.

Modify the code below so that `max` works as described. Furthermore, modify `main` so that the `max` method is called on the given array and its max printed out (in this case, it should print `22`).
```
public class ClassNameHere {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        return 0;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};      
    }
}
```


## Exercise 3
Rewrite your solution to Exercise 2 so that it uses a `for` loop.


## Optional: Exercise 4
Write a function `windowPosSum(int[] a, int n)` that replaces each element a[i] with the sum of a[i] through a[i + n], but only if a[i] is positive valued. If there are not enough values because we reach the end of the array, we sum only as many values as we have.

For example, suppose we call `windowPosSum` with the array `a = {1, 2, -3, 4, 5, 4}`, and `n = 3`. In this case, we’d:

Replace a[0] with a[0] + a[1] + a[2] + a[3].
Replace a[1] with a[1] + a[2] + a[3] + a[4].
Not do anything to a[2] because it’s negative.
Replace a[3] with a[3] + a[4] + a[5].
Replace a[4] with a[4] + a[5].
Not do anything with a[5] because there are no values after a[5].
Thus, the result after calling `windowPosSum` would be `{4, 8, -3, 13, 9, 4}`.

As another example, if we called `windowPosSum` with the array `a = {1, -1, -1, 10, 5, -1}`, and `n = 2`, we’d get `{-1, -1, -1, 14, 4, -1}`.
```
public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    /** your code here */ 
  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
}
```

Hint 1: Use two `for` loops.

Hint 2: Use `continue` to skip negative values.

Hint 3: Use `break` to avoid going over the end of the array.
