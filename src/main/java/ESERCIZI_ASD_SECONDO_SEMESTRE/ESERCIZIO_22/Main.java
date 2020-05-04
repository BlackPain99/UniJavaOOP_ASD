package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Scanner secondScanner = new Scanner(scan.nextLine());

        List<Integer> arrayList = new ArrayList<>();

        while (secondScanner.hasNextInt()) {
            arrayList.add(secondScanner.nextInt());
        }

        int[] input = new int[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            input[i] = arrayList.get(i);
        }

        System.out.println( MaxValueOfEspression.maxValue(input) );
    }


}
