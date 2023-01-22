import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        int textLenght = text.length();
        System.out.println();
        char[] textChar = text.toCharArray();

        if (textLenght % 2 == 1) {
            for (int i = 0; i < textLenght; i++) {
                if (i != textLenght / 2) {
                    System.out.print(textChar[i]);
                }
            }
        } else {
            for (int i = 0; i < textLenght; i++) {
                if (i < (textLenght / 2) - 1) {
                    //System.out.println(i);
                    System.out.print(textChar[i]);
                } else if (i > textLenght / 2) {
                    //System.out.println(i);
                    System.out.print(textChar[i]);
                }
            }

        }

        // put your code here
    }
}