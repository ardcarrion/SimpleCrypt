import static java.lang.Character.*;

public class ROT13  {

    int shiftAmount;

    ROT13(Character cs, Character cf) {
        shiftAmount = cf.compareTo(cs);
    }

    ROT13() {
        shiftAmount = 13;
    }


    public String crypt(String text) throws UnsupportedOperationException {
        return encrypt(text);
    }

    public String encrypt(String text) {
        Character[] chars = stringToCharacterArray(text);
        StringBuilder result = new StringBuilder();
        for (Character c : chars) {
            if (Character.isLowerCase(c)) {
                int shifted = c + shiftAmount;
                if (shifted > 122) shifted -= 26;
                result.append((char)shifted);
            } else if (Character.isUpperCase(c)) {
                int shifted = c + shiftAmount;
                if (shifted > 90) shifted -= 26;
                result.append((char)shifted);
            } else result.append(c);
        }
        return result.toString();
    }

    private Character[] stringToCharacterArray(String text) {
        char[] chars = text.toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < characters.length; i++) characters[i] = chars[i];
        return characters;
    }


    public String decrypt(String text) {
        return encrypt(text);
    }

    public static String rotate(String s, Character c) {
        String[] characterSplit = s.split(String.valueOf(c));
        if (characterSplit.length <= 1) return s;
        return c + characterSplit[1] + characterSplit[0];
    }

}
