import java.util.Scanner;
class calcRa {
    static Scanner scanner = new Scanner(System.in);
    static int num1, num2;


    public static void main(String[] args) {
        System.out.println("Введите выражение (например, 2 + 3 или II * IV): ");
        String input = scanner.nextLine();
        scanner.close();
        String[] tokens = input.split("\s");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }
        String romanPattern = "^(I|II|III|IV|V|VI|VII|VIII|IX|X)$";
        String arabicPattern = "^[1-9]|10$";
        String a = tokens[0];
        String b = tokens[2];
        String op = tokens[1];
        boolean isRoman = a.matches(romanPattern) && b.matches(romanPattern);
        boolean isArabic = a.matches(arabicPattern) && b.matches(arabicPattern);
        if (!isRoman && !isArabic) throw new IllegalArgumentException("Неверные числа");
        if (isRoman) {
            num1 = romanToArabic(a);
            num2 = romanToArabic(b);
        } else {
            num1 = Integer.parseInt(a); //dyrh
            num2 = Integer.parseInt(b);
        }
        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10");
        }
        int result;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверная операция");
        }
        if (isRoman) {
            if (result < 0) {
                throw new IllegalArgumentException("Римские числа не могут быть отрицательными или нулем");
            }
            System.out.println("Результат: " + arabicToRoman(result));
        } else {
            System.out.println("Результат: " + result);
        }

    }
    private static int romanToArabic(String roman) {
        switch (roman) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new IllegalArgumentException("Неверное римское число: " + roman);
        }
    }
    private static String arabicToRoman ( int arabic){
        if (arabic < 1 || arabic > 3999) {
            throw new IllegalArgumentException("Неверное арабское число: " + arabic);
        }
        StringBuilder roman = new StringBuilder();
        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arabicValues.length; i++) {
            while (arabic >= arabicValues[i]) {
                roman.append(romanNumerals[i]);
                arabic -= arabicValues[i];
            }
        }
        return roman.toString();
    }
}
