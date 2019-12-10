package PROGETTO_ASD;

import java.util.List;
import java.util.Scanner;

public class Input {

    public static void InputToList(List<Integer> items){

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        Scanner secondScanner = new Scanner(userInput);

        while(secondScanner.hasNextInt()){
            int singleNumber = secondScanner.nextInt();
            items.add(singleNumber);
        }
    }


}
