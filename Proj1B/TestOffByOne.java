import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne(){
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('a', 'b'));  // true
        assertTrue(obo.equalChars('r', 'q'));  // true
        assertFalse(obo.equalChars('a', 'e'));  // false
        assertFalse(obo.equalChars('z', 'a'));  // false
        assertFalse(obo.equalChars('a', 'a'));  // false
    }

} // Uncomment this class once you've created your CharacterComparator interface and OffByOne class.