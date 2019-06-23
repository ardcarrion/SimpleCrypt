import org.junit.Test;

import static org.junit.Assert.*;

public class CeasarCypherTest {
    @Test
    public void cryptTest1() {
        // Given
        CaesarCypher cipher = new CaesarCypher();

        String Q1 = "Why did the chicken cross the road?";
        String A1 = "Zkb glg wkh fklfnhq furvv wkh urdg?";

        String Q2 = "Wr jhw wr wkh rwkhu vlgh!";
        String A2 = "To get to the other side!";

        // When
        String actual = cipher.encrypt(Q1);
        System.out.println(Q1);
        System.out.println(A1);
        // Then
        assertEquals(actual, A1);

        // When
        String actual2 = cipher.decrypt(Q2);
        System.out.println(Q2);
        System.out.println(A2);

        // Then
        assertEquals(actual2, A2);
    }
    @Test
    public void cryptTest2() {
        // Given
        CaesarCypher cipher = new CaesarCypher();

        String Q1 = "Why did the chicken cross the road?";

        // When
        String actual = cipher.decrypt(cipher.crypt(Q1));
        // Then
        assertEquals(actual, Q1);
    }

}