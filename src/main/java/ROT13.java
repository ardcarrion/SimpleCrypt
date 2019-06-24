import javax.print.DocFlavor;

import static java.lang.Character.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
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
        PrintWriter writer = null;
        try {
            Stream<String> stringStream = Files.lines(Paths.get("sonnet18.txt"), StandardCharsets.UTF_8);
            writer =  new PrintWriter(new FileOutputStream("text.enc"));
            String fileString = stringStream.collect(Collectors.joining("\n"));
            writer.write(encrypt(fileString));
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
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
