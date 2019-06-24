import javax.print.DocFlavor;

import static java.lang.Character.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;


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

    public void encryptFile(String path) throws IOException {
        FileOutputStream fileOut = null;
        try {
            String newPath = "/Users/aliciacarrion/dev/SimpleCrypt/src/test/java/text.enc";
            fileOut = new FileOutputStream(newPath);
            Path strToPath = Paths.get(path);
            List<String> output = Files.readAllLines(strToPath, StandardCharsets.UTF_8);
            for (String str : output) {
                String encrypt = encrypt(str) + "\n";
                byte[] strToBytes = encrypt.getBytes();
                fileOut.write(strToBytes);
            }
            fileOut.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) fileOut.close();
        }

    }

    public String encrypt(String text) {
        return encrypt(text, shiftAmount);
    }

    public String encrypt(String text, int shiftAmount) {
        Character[] chars = stringToCharacterArray(text);
        StringBuilder result = new StringBuilder();
        for (Character c : chars) {
            if (Character.isLowerCase(c)) {
                int shifted = 'a' + (c + shiftAmount - 'a') % 26;
                result.append((char)shifted);
            } else if (Character.isUpperCase(c)) {
                int shifted = 'A' + (c + shiftAmount - 'A') % 26;
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
        int decryptShift = 26 - shiftAmount;
        return encrypt(text, decryptShift);
    }

    public static String rotate(String s, Character c) {
        String[] characterSplit = s.split(String.valueOf(c));
        if (characterSplit.length <= 1) return s;
        return c + characterSplit[1] + characterSplit[0];
    }

}
