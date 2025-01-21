package test.java.jp.ac.uryukyu.ie.e245740;


import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class HitAndBlowTest {

    @Test
    void testGenerateRandomNumbers() {
        ArrayList<Integer> numbers = HitandBlow.generateRandomNumbers();
        assertEquals(4, numbers.size(), "生成された数列の長さが間違っています。");
        for (int number : numbers) {
            assertTrue(number >= 0 && number <= 9, "数列に範囲外の値が含まれています。");
        }
        assertEquals(4, numbers.stream().distinct().count(), "数列に重複が含まれています。");
    }

    @Test
    void testIsValidInput() {
        assertTrue(HitandBlow.isValidInput("1234"), "正しい入力が無効と判定されました。");
        assertFalse(HitandBlow.isValidInput("1123"), "重複のある入力が有効と判定されました。");
        assertFalse(HitandBlow.isValidInput("12345"), "桁数が多い入力が有効と判定されました。");
        assertFalse(HitandBlow.isValidInput("12"), "桁数が少ない入力が有効と判定されました。");
        assertFalse(HitandBlow.isValidInput("12a4"), "数字以外を含む入力が有効と判定されました。");
    }

    @Test
    void testConvertToIntegerList() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        assertEquals(expected, HitandBlow.convertToIntegerList("1234"), "文字列の変換が正しくありません。");
    }

    @Test
    void testCalculateHits() {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(2);
        answer.add(3);
        answer.add(4);

        ArrayList<Integer> guess = new ArrayList<>();
        guess.add(1);
        guess.add(2);
        guess.add(5);
        guess.add(6);

        assertEquals(2, HitandBlow.calculateHits(answer, guess), "HITの計算が正しくありません。");
    }

    @Test
    void testCalculateBlows() {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(2);
        answer.add(3);
        answer.add(4);

        ArrayList<Integer> guess = new ArrayList<>();
        guess.add(4);
        guess.add(3);
        guess.add(2);
        guess.add(1);

        assertEquals(4, HitandBlow.calculateBlows(answer, guess), "BLOWの計算が正しくありません。");
    }
}
