package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_22;

public class MaxValueOfEspression {

    static int maxValue(int[] input) {

        int length = input.length;

        //necessari 4 elementi
        if (length < 4) {
            return Integer.MIN_VALUE;
        }

        int[][] matrix = new int[4][length];

        /*//inizializzo matrice
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = Integer.MIN_VALUE;
            }
        }*/


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < length; j++) {

                //prima riga
                //E(i) = input[i]
                if (i == 0) {

                    if (j == 0) {
                        matrix[i][j] = input[0]; // caso base
                    } else {
                        matrix[i][j] = Math.max(matrix[0][j - 1], input[j]);
                    }

                }

                //seconda riga
                //E(i, j) = input[i] - input[j]
                if (i == 1) {

                    if (j == 1) {
                        matrix[i][j] = input[0] - input[1]; //caso base
                    } else if (j > 1) {
                        matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j - 1] - input[j]);
                    }

                }

                //terza riga
                //E(i, j, k) = input[i] - input[j] + input[k]
                if (i == 2) {

                    if (j == 2) {
                        matrix[i][j] = input[0] - input[1] + input[2]; // caso base
                    } else if (j > 2) {
                        matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j - 1] + input[j]);
                    }

                }

                //quarta riga
                //E(i, j, k, h) = input[i] - input[j] + input[k] - input[h]
                if (i == 3) {

                    if (j == 3) {
                        matrix[i][j] = input[0] - input[1] + input[2] - input[3]; // caso base
                    } else if (j > 3) {
                        matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j - 1] - input[j]);
                    }

                }


            }
        }

        return matrix[3][length - 1];
    }


}
