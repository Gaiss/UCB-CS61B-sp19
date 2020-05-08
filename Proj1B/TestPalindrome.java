import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("persiflage"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("AAAAAB"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("aaa"));

        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("cat",cc));
        assertFalse(palindrome.isPalindrome("persiflage",cc));
        assertFalse(palindrome.isPalindrome("Aa",cc));
        assertFalse(palindrome.isPalindrome("AAAB",cc));
        assertTrue(palindrome.isPalindrome("a",cc));
        assertTrue(palindrome.isPalindrome("",cc));
        assertFalse(palindrome.isPalindrome("aaa",cc));
        assertTrue(palindrome.isPalindrome("flake",cc));

        CharacterComparator offBy5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome("a", offBy5));
        assertTrue(palindrome.isPalindrome("", offBy5));
        assertTrue(palindrome.isPalindrome("af", offBy5));
        assertFalse(palindrome.isPalindrome("aa", offBy5));
        assertFalse(palindrome.isPalindrome("afa", offBy5));
        assertFalse(palindrome.isPalindrome("ab", offBy5));
    }
}     // Uncomment this class once you've created your Palindrome class.