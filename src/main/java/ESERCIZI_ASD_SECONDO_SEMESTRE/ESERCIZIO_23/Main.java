package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_23;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<String> input = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        String[] testo = scan.nextLine().split(" ");

        int width = scan.nextInt();

        JustifiedText.justify(testo, width);

    }


}
