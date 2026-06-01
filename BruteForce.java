public class BruteForce {
    public int findKey(String encryptedText) {
        int bestKey = 0;
        int maxMatches = 0;
        int len = Alphabet.CHARS.length();
        Cipher cipher = new Cipher();
        String[] commonWords = {" и ", " в ", " на ", " с ", " что ", ", "};

        for (int key = 0; key < len; key++) {
            String decrypted = cipher.decrypt(encryptedText, key);
            int matches = 0;
            for (String word : commonWords) {
                int index = 0;
                while ((index = decrypted.indexOf(word, index)) != -1) {
                    matches++;
                    index += word.length();
                }
            }
            if (matches > maxMatches) {
                maxMatches = matches;
                bestKey = key;
            }
        }
        return bestKey;
    }
}
