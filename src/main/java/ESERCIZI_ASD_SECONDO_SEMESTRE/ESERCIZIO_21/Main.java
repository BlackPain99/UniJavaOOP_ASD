package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_21;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String u = scan.next();
        String v = scan.next();

        System.out.println(Levenshtein.editDistance(u, v));

        System.out.println(Levenshtein.editDistanceDP(u, v));
    }


}
