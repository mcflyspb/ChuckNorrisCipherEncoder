package chucknorris;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        for (;;) {
            System.out.println("Please input operation (encode/decode/exit):");
            String inputText = input.nextLine().trim();
            if (Objects.equals(inputText, "encode")) {
                encode(input);
            } else if (Objects.equals(inputText, "decode")) {
                decode(input);
            } else if (Objects.equals(inputText, "exit")) {
                break;
            } else {
                System.out.println("There is no '" + inputText + "' operation\n");
            }
        }
        System.out.println("Bye!");
    }

    private static void encode(Scanner input) {
        System.out.println("Input string:");
        String StringSymbol = input.nextLine();
        String symbol = "";
        String encodeMessage = "";
        char[] charArray = StringSymbol.toCharArray();
        char[] binarySymbolArray;


        for (char x : charArray) {
            String currentSymbol = Integer.toBinaryString(x).length() == 6 ? "0" + Integer.toBinaryString(x) : Integer.toBinaryString(x);
            //System.out.println(currentSymbol);
            symbol = symbol + currentSymbol;
        }


        binarySymbolArray = symbol.toCharArray();

        System.out.println("Encoded string:");


        char preSymbol = binarySymbolArray[0];
        int preSymbolLenght = binarySymbolArray.length;
        int countOne = 0;
        int countZero = 0;

        if (preSymbol == '1') {
            countOne = 1;
        } else {
            countZero = 1;
        }


        for (int i = 1; i < preSymbolLenght; i++) {

            //System.out.println("i: " + i + " binarySymbol[i]: " + binarySymbolArray[i] + " preSymbol: " + preSymbol
            //+ " countOne: " + countOne + " countZero: " + countZero);

            if (binarySymbolArray[i] == '1' && binarySymbolArray[i] == preSymbol ) {
                countOne++;
            } else  if (binarySymbolArray[i] == '0' && binarySymbolArray[i] == preSymbol) {
                countZero++;
            } else if (binarySymbolArray[i] == '0' && binarySymbolArray[i] != preSymbol) {
                //countOne++;

                encodeMessage = encodeMessage + printDigits (1,countOne);
                countZero++;
                countOne = 0;
            } else {
                //countZero++;

                encodeMessage = encodeMessage + printDigits (0,countZero);
                countOne++;
                countZero = 0;
            }
            preSymbol = binarySymbolArray[i];
        }

        if (countOne > 0) {
            encodeMessage = encodeMessage + printDigits (1,countOne);
            //printDigits (1,countOne);
        }
        if (countZero > 0) {
            encodeMessage = encodeMessage + printDigits (0,countZero);
            //printDigits (0,countZero);
        }

        System.out.println(encodeMessage.trim());

    }

    private static void decode(Scanner input) {
        System.out.println("Input encoded string:");
        String text = input.nextLine();
        String[] textBlock = text.split(" ");
        String binaryText = "";



        for (int i = 0; i < textBlock.length / 2; i++) {
            binaryText = binaryText + printChar(textBlock[i * 2], textBlock[i * 2 + 1]);
            //System.out.println(textBlock[i * 2] + " - " + textBlock[i * 2 + 1]);
        }

        //System.out.println(binaryText);
        //checkDecodeMessage (text, textBlock, binaryText);

        if (checkDecodeMessage (text, textBlock, binaryText)) {
            char[] charText = binaryText.toCharArray();
            String output = "";

            System.out.println("Decoded string:");

            for (int j = 0; j < (binaryText.length() + 1) / 7; j++) {

                byte b7 = (byte) (64 * Integer.parseInt(String.valueOf(charText[j * 7])));
                byte b6 = (byte) (32 * Integer.parseInt(String.valueOf(charText[j * 7 + 1])));
                byte b5 = (byte) (16 * Integer.parseInt(String.valueOf(charText[j * 7 + 2])));
                byte b4 = (byte) (8 * Integer.parseInt(String.valueOf(charText[j * 7 + 3])));
                byte b3 = (byte) (4 * Integer.parseInt(String.valueOf(charText[j * 7 + 4])));
                byte b2 = (byte) (2 * Integer.parseInt(String.valueOf(charText[j * 7 + 5])));
                byte b1 = (byte) Integer.parseInt(String.valueOf(charText[j * 7 + 6]));
                char currectChar = (char) (b1 + b2 + b3 + b4 + b5 + b6 + b7);

                output = output + currectChar;
                //System.out.print(currectChar);
            }
            System.out.printf(output.trim());
            System.out.println("\n");
        } else {
            System.out.println("Encoded string is not valid.\n");
        }
    }

    private static boolean checkDecodeMessage(String text, String[] textBlock, String binaryText) {
        boolean result = false;
        String[] charText = text.replace(" ","").split("");
        for (String x : charText) {
            if (!Objects.equals(x, "0")) {
                return false;
            }
        }

        for (int x = 0; x < textBlock.length / 2; x++) {
            //System.out.println("--" + x + "--");
            if (Objects.equals(textBlock[2 * x],"0") || Objects.equals(textBlock[2 * x],"00")) {
                result = true;
            } else {
                return false;
            }
        }


        if (textBlock.length % 2 != 0) {
            return false;
        }

        if (binaryText.length() % 7 != 0) {
           return false;
        }

        return result;
    }

    private static String printChar(String s, String s1) {
        String ret = "";
        int s1Lenght = s1.length();
       // System.out.println("X - " + s1Lenght);
        if (Objects.equals(s, "0")) {
            //System.out.println("XX-0");
            while (s1Lenght > 0) {
                ret = ret + "1";
                s1Lenght--;
            }
        } else {
            //System.out.println("XX-1");
            while (s1Lenght > 0) {
                ret = ret + "0";
                s1Lenght--;
            }
        }
        return ret;
    }

    private static String printDigits(int i, int count) {
        String encodedMessage = "";
        //System.out.println("printDigits: " + i + " --- " + count);

        if (i == 1) {
            encodedMessage = encodedMessage + "0 ";
            //System.out.print("0 ");
        } else {
            encodedMessage = encodedMessage + "00 ";
            //System.out.print("00 ");
        }
        while (count > 0) {
            encodedMessage = encodedMessage + "0";
            //System.out.print("0");
            count--;
        }
        encodedMessage = encodedMessage + " ";
        //System.out.print(" ");
        return encodedMessage;
    }
}
