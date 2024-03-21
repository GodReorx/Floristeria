package Exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputControl {
    private static final Scanner INPUT = new Scanner(System.in);
    public static double requestDoubleData(String mensaje) {
        boolean flag = true;
        double opt = 0;
        while (flag) {
            try {
                System.out.println(mensaje);
                opt = INPUT.nextDouble();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Incorrect format");
            }
            INPUT.nextLine();

        }

        return opt;
    }
    public static int requestIntData(String mensaje) {
        boolean flag = true;
        int opt = 0;
        while (flag) {
            try {
                System.out.println(mensaje);
                opt = INPUT.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Only accept numbers");
            }
            INPUT.nextLine();

        }
        return opt;
    }

    public static String askNameOnlyLetters(String mensaje) {
        boolean flag = true;
        String name = "";
        while (flag) {
            try {
                System.out.println(mensaje);
                name = INPUT.nextLine();
                for(int i = 0; i < name.length(); i++) {
                    char checkChar = name.charAt(i);
                    if (!Character.isAlphabetic(checkChar)) {
                        throw new Exception();
                    }
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("ERROR: Only characters");
            }
        }
        return name;
    }
}
