package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_17;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BstAumentato bstAumentato = new BstAumentato();

        iterativeInput(bstAumentato);

    }

    private static void iterativeInput(BstAumentato bst) {
        String s;
        int n;
        Scanner scan = new Scanner(System.in);

        label:
        while (true) {

            s = scan.next();

            switch (s) {
                case "insert":
                    s = scan.next();
                    n = Integer.parseInt(s);
                    s = scan.next();

                    bst.insert(n, s);
                    break;
                case "remove":
                    s = scan.next();
                    n = Integer.parseInt(s);

                    bst.remove(n);
                    break;
                case "find":
                    s = scan.next();
                    n = Integer.parseInt(s);

                    System.out.println(bst.find(n));
                    break;
                case "clear":
                    bst.clear();
                    break;
                case "show":
                    bst.show();
                    System.out.println();
                    break;
                case "height":
                    System.out.println(bst.height());
                    break;
                default:
                    System.out.println();
                    break label;
            }

        }
    }

}
