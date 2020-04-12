package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_19;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

         RBT rbt = new RBT();

         iterativeInput(rbt);

    }

    private static void iterativeInput(RBT rbt) {
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

                    rbt.insert(n, s);
                    break;
                case "find":
                    s = scan.next();
                    n = Integer.parseInt(s);

                    System.out.println(rbt.find(n));
                    break;
                case "clear":
                    rbt.clear();
                    break;
                case "show":
                    rbt.show();
                    System.out.println();
                    break;
                case "height":
                    System.out.println(rbt.height());
                    break;
                default:
                    System.out.println();
                    break label;
            }

        }
    }


}
