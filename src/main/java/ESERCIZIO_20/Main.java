package ESERCIZIO_20;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String x = scan.next();
        String y = scan.next();

        System.out.println(LCS.lcsMemo(x, y));

    }
}
