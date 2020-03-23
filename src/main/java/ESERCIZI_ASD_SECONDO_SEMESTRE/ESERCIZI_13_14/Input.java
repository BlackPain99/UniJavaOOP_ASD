package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZI_13_14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {

    public static void inputToList(List<String> tree){

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        Scanner secondScanner = new Scanner(userInput);

        while(secondScanner.hasNext()){
            String key = secondScanner.next();
            tree.add(key);
        }

    }


}
