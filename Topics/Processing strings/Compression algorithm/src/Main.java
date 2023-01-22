import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        char[] textChar = text.toCharArray();
        char previousChar = textChar[0];
        int charCount = 1;

        for (int i = 1; i < textChar.length; i++) {
            if (textChar[i] == previousChar) {
                charCount++;
            } else {
                printChar(previousChar, charCount);
                charCount = 1;
            }
            previousChar = textChar[i];
        }
        if (charCount > 0) {
            printChar(previousChar, charCount);
        }
    }

    private static void printChar(char previousChar, int charCount) {
        System.out.print(String.valueOf(previousChar) + charCount);
    }
}