package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_21;

public class Levenshtein {

    static int editDistance(String u, String v) {

        int edit;

        if (u.equals(v)) {
            return 0;
        } else if (u.equals("")) {
            return v.length();
        } else if (v.equals("")) {
            return u.length();
        } else {

            if (u.charAt(0) == v.charAt(0)) {
                edit = 0;
            } else {
                edit = 1;
            }

            return Math.min(Math.min(editDistance(u.substring(1), v.substring(1)) + edit, editDistance(u, v.substring(1)) + 1), editDistance(u.substring(1), v) + 1);
        }
    }

    static int editDistanceDP(String u, String v){

        int edit;

        int[][] d = new int[u.length() + 1][v.length()+ 1];

        for (int i = 0; i <= u.length(); i++){
            d[i][0] = i;
        }

        for (int j = 0; j <= v.length(); j++){
            d[0][j] = j;
        }

        for (int i = 1; i <= u.length(); i++) {
            for (int j = 1; j <= v.length(); j++) {

                if(u.charAt(i-1) == v.charAt(j-1)){
                    edit = 0;
                } else {
                    edit = 1;
                }

                d[i][j] = Math.min(Math.min(d[i-1][j-1] + edit, d[i][j-1] + 1), d[i-1][j] + 1);
            }
        }

        return d[u.length()][v.length()];
    }



}
