package main.java.jp.ac.uryukyu.ie.e245740;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HitandBlow {
    private static final int NUMBER_LENGTH = 4; // 数字の桁数

    public static void main(String[] args) {
        // ランダムな数列を生成
        ArrayList<Integer> answer = generateRandomNumbers();
        System.out.println("HIT & BLOW ゲームを開始します！");
        System.out.println(NUMBER_LENGTH + "桁の異なる数字を当ててください。");

        Scanner scanner = new Scanner(System.in);
        boolean isCorrect = false;
        int attempts = 0;

        // ゲームループ
        while (!isCorrect) {
            attempts++;
            System.out.print("予想を入力してください（" + NUMBER_LENGTH + "桁の数字）: ");
            String input = scanner.nextLine();

            // 入力のバリデーション
            if (!isValidInput(input)) {
                System.out.println("入力が正しくありません。異なる数字の" + NUMBER_LENGTH + "桁を入力してください。");
                continue;
            }

            ArrayList<Integer> guess = convertToIntegerList(input);
            int hits = calculateHits(answer, guess);
            int blows = calculateBlows(answer, guess);

            System.out.println("HITS: " + hits + " / BLOWS: " + blows);

            if (hits == NUMBER_LENGTH) {
                isCorrect = true;
                System.out.println("おめでとうございます！" + attempts + "回の試行で正解しました！");
            }
        }
        scanner.close();
    }

    // ランダムな数列を生成
    private static ArrayList<Integer> generateRandomNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return new ArrayList<>(numbers.subList(0, NUMBER_LENGTH));
    }

    // 入力のバリデーション
    private static boolean isValidInput(String input) {
        if (input.length() != NUMBER_LENGTH) {
            return false;
        }

        ArrayList<Character> digits = new ArrayList<>();
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) || digits.contains(c)) {
                return false;
            }
            digits.add(c);
        }
        return true;
    }

    // 文字列を整数リストに変換
    private static ArrayList<Integer> convertToIntegerList(String input) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (char c : input.toCharArray()) {
            numbers.add(Character.getNumericValue(c));
        }
        return numbers;
    }

    // ヒットの計算
    private static int calculateHits(ArrayList<Integer> answer, ArrayList<Integer> guess) {
        int hits = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            if (answer.get(i).equals(guess.get(i))) {
                hits++;
            }
        }
        return hits;
    }

    // ブローの計算
    private static int calculateBlows(ArrayList<Integer> answer, ArrayList<Integer> guess) {
        int blows = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            if (answer.contains(guess.get(i)) && !answer.get(i).equals(guess.get(i))) {
                blows++;
            }
        }
        return blows;
    }
}