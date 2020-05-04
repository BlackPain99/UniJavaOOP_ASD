package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_23;

import java.util.ArrayList;
import java.util.List;

public class JustifiedText {

    static void justify(String[] input, int width){
        IntPair[] memo = new IntPair[input.length + 1];

        //caso base
        memo[input.length] = new IntPair(0, 0);

        for (int i = input.length - 1; i >= 0 ; i--) {
            int score = Integer.MAX_VALUE;
            int nextIndexLine = i + 1;

            for (int j = i + 1 ; j <= input.length ; j++) {
                int costo = costFunction(input, i, j, width);

                if(costo < 0 || costo == Integer.MAX_VALUE)
                    break;

                int currScore = costo + memo[j].x;

                if(currScore < 0 || currScore == Integer.MAX_VALUE)
                    break;

                //prendo il minimo
                if(score > currScore){
                    score = currScore;
                    nextIndexLine = j;
                }

            }

            memo[i] = new IntPair(score, nextIndexLine);
        }

        int i = 0;

        while(i < input.length){
            String line = getLine(input, i, memo[i].y);
            System.out.println(line);;
            i = memo[i].y;
        }
        
    }


    private static String getLine(String[] input, int start, int end) {
        StringBuilder sb = new StringBuilder();

        for (int i = start; i < end - 1; i++) {
            sb.append(input[i] + " ");
        }

        sb.append(input[end - 1]);

        return sb.toString();
    }


    private static int costFunction(String[] words, int start, int end, int width){

        int length = 0;

        for (int i = start; i < end ; i++) {

            length = length + words[i].length();

            if(length > width)
                return Integer.MAX_VALUE;

            //devo aggiungere anche uno spazio per parola
            length++;
        }

        //elimino lo spazio all'ultima parola
        length--;
        int temp = width - length;
        return temp * temp * temp;
    }


}
