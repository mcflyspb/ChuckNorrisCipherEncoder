import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = " " + input.nextLine() + " ";
        String subString = input.next();
        System.out.println(string.split(subString).length - 1);
    }
}