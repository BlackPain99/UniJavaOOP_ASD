package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class heapSelection {

    public static void main(String[] args) {

        List<Integer> items = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        Scanner secondScanner = new Scanner(userInput);

        while(secondScanner.hasNextInt()){
            int singleNumber = secondScanner.nextInt();
            items.add(singleNumber);
        }

        String s = items.toString();
        System.out.println(s);
        System.out.println(items.size());

    }
}
