package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_18;



import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AVLTree avl = new AVLTree();

        iterativeInput(avl);
    }


    private static void iterativeInput(AVLTree avl) {
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

                    avl.insert(n, s);
                    break;
                case "remove":
                    s = scan.next();
                    n = Integer.parseInt(s);

                    avl.remove(n);
                    break;
                case "find":
                    s = scan.next();
                    n = Integer.parseInt(s);

                    System.out.println(avl.find(n));
                    break;
                case "clear":
                    avl.clear();
                    break;
                case "show":
                    avl.show();
                    System.out.println();
                    break;
                case "height":
                    System.out.println(avl.height());
                    break;
                default:
                    System.out.println();
                    break label;
            }

        }
    }

}
