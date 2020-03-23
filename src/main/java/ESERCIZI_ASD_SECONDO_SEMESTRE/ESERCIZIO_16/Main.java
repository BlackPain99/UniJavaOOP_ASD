package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_16;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bst bst = new Bst();

        iterativeInput(bst);

    }

    private static void iterativeInput(Bst bst) {

        while(true){
            Scanner scanner = new Scanner(System.in);

            String rigaDiComando = scanner.nextLine();

            Scanner secondScanner = new Scanner(rigaDiComando);

            if(rigaDiComando.length() == 0){
                break;
            }

            List<String> inputList = new ArrayList<>();

            while(secondScanner.hasNext()){
                String key = secondScanner.next();
                inputList.add(key);
            }

            String comando = inputList.get(0);

            if(!comando.equals("insert") && !comando.equals("remove") && !comando.equals("find") && !comando.equals("clear") && !comando.equals("show")){
                break;
            }

            interpreteComando(inputList, bst);
        }

    }

    private static void interpreteComando(List<String> inputList, Bst bst) {

        String stringKey;
        int key;

        if (inputList.get(0).equals("insert")){
            stringKey = inputList.get(1);
            String value = inputList.get(2);

            key = Integer.parseInt(stringKey);

            bst.insert(key, value);
        }

        if(inputList.get(0).equals("remove")){
            stringKey = inputList.get(1);

            key = Integer.parseInt(stringKey);

            bst.remove(key);
        }

        if(inputList.get(0).equals("find")){
            stringKey = inputList.get(1);

            key = Integer.parseInt(stringKey);

            String value = bst.find(key);
            System.out.println(value);
        }

        if(inputList.get(0).equals("clear")){
            bst.clear();
        }

        if(inputList.get(0).equals("show")){
            bst.show();
            System.out.println();
        }

    }
}
