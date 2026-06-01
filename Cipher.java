public class Cipher {
    public String encrypt(String text, int shift) {
        return process(text, shift);
    }

    public String decrypt(String text, int shift) {
        return process(text, -shift);
    }

    private String process(String text, int shift) {
        StringBuilder result = new StringBuilder();
        int len = Alphabet.CHARS.length();
        int normalizedShift = (shift % len + len) % len;

        for (char ch : text.toCharArray()) {
            int index = Alphabet.CHARS.indexOf(ch);
            if (index != -1) {
                result.append(Alphabet.CHARS.charAt((index + normalizedShift) % len));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
