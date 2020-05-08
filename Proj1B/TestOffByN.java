import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy5 = new OffByN(5);

    @Test
    public void testOffByN() {
        OffByN ob5 = new OffByN(5);
        assertTrue(ob5.equalChars('a', 'f'));
        assertTrue(ob5.equalChars('f', 'a'));
        assertFalse(ob5.equalChars('a', 'b'));
    }
}
