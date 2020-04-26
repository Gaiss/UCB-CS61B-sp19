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
