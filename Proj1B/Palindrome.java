public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++){
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /* non-recursive
    public boolean isPalindrome(String word){
        for (int i= 0; i < word.length()-i; i++){
            if (word.charAt(i) != word.charAt(word.length()-i-1)){
                return false;
            }
        }
        return true;
    }*/

    public boolean isPalindrome(String word){
        return isPalindrome(wordToDeque(word));
    }

    public boolean isPalindrome(Deque<Character> word){
         while (word.size() > 1) {
             return word.removeFirst() == word.removeLast() && isPalindrome(word);
         }
         return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() > 1) {
            for (int i = 0; i < word.length() - i - 1; i++) {
                if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                    return false;
                }
            }
        }
        return true;
    }

}

