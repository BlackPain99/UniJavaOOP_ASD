package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_20;

public class LCS {

    private static final String UNKNOWN = null;


    /**
     * calcola LCS
     * metodo classico senza memoization o programmazione dinamica
     * inefficente (calcola più volte gli stessi valori)
     * @param u prima stringa
     * @param v seconda stringa
     * @return LCS
     */
    static String lcs(String u, String v) {

        if (u.equals("") || v.equals("")) {

            return "";

        } else if (u.charAt(0) == v.charAt(0)) {

            return u.charAt(0) + lcs(u.substring(1), v.substring(1));

        } else {

            String l1 = lcs(u.substring(1), v);
            String l2 = lcs(u, v.substring(1));

            if (l1.length() > l2.length()) {
                return l1;
            } else {
                return l2;
            }

        }
    }


    /**
     * calcola LCS tramite la memoization, salvando i risultati ottenuti in una tabella
     * si appoggia al metodo lcsMemoRec, curandosi solo dell'inizializzazione
     * notevole spreco di memoria causa ricorsione e per salvare tutte le stringhe ottenute in memoria
     * @param u prima stringa
     * @param v seconda stringa
     * @return LCS
     */
    static String lcsMemo(String u, String v) {

        int i = u.length();
        int j = v.length();

        String[][] h = new String[i + 1][j + 1];

        for (int x = 0; x <= i; x++) {
            for (int y = 0; y <= j; y++) {
                h[x][y] = UNKNOWN;
            }
        }

        return lcsMemoRec(u, v, h);
    }


    private static String lcsMemoRec(String u, String v, String[][] h) {
        int i = u.length();
        int j = v.length();

        if (h[i][j] == UNKNOWN) {

            if (u.equals("") || v.equals("")) {
                h[i][j] = "";
            } else if (u.charAt(0) == v.charAt(0)) {
                h[i][j] = u.charAt(0) + lcsMemoRec(u.substring(1), v.substring(1), h);
            } else {
                String l1 = lcsMemoRec(u.substring(1), v, h);
                String l2 = lcsMemoRec(u, v.substring(1), h);

                if (l1.length() > l2.length()) {
                    h[i][j] = l1;
                } else {
                    h[i][j] = l2;
                }

            }
        }

        return h[i][j];
    }


    /**
     * calcola la tabella contenente LLCS
     * @param u prima stringa
     * @param v seconda stringa
     * @return tabella costruita (int[][])
     */
    static int[][] llcsDP(String u, String v) {

        int m = u.length();
        int n = v.length();

        int[][] h = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            h[i][0] = 0;
        }

        for (int j = 1; j <= n; j++) {
            h[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (u.charAt(i - 1) == v.charAt(j - 1)) {
                    h[i][j] = h[i - 1][j - 1] + 1;
                } else {
                    h[i][j] = Math.max(h[i - 1][j], h[i][j - 1]);
                }

            }
        }

        return h;
    }




    /**
     * risolve il problema della LCS con programmazione dinamica senza ricorsione
     * si avvale di llcsDP per calcolare la tabella con LLCS
     * tramite lcsDPRec ricostruisce la stringa
     * @param u prima stringa
     * @param v seconda stringa
     * @return LCS
     */
    static String lcsDP(String u, String v){

        //calcolo la tabella contenente le lunghezze delle LCS
        int[][] h= llcsDP(u, v);

        //ripercorro la tabella h dall'ultima cella, seguendo le frecce, per ricostruire la stringa
        return lcsDPRec(h, u, v, u.length(), v.length());
    }


    /**
     * ripercorre la tabella h di LLCS dall'ultima cella, seguendo le frecce, per ricostruire la stringa
     * approccio iterativo per risparmiare memoria
     * @param h tabella di LLCS
     * @param u prima stringa
     * @param v seconda stringa
     * @param i lunghezza di u
     * @param j lunghezza di v
     * @return LCS
     */
    static String lcsDPRec(int[][] h, String u, String v, int i, int j){
        String s = "";

        while (h[i][j] != 0){

            if(u.charAt(i-1) == v.charAt(j-1)){
                s = u.charAt(i-1) + s;
                i = i - 1;
                j = j - 1;
            } else if(h[i][j - 1] > h[i - 1][j]){
                j = j - 1;
            } else {
                i = i - 1;
            }

        }

        return s;
    }


    /**
     * risolve il problema della LCS con programmazione dinamica ma utilizzando il meotodo ricorsivo backtrack
     * si avvale di llcsDP per calcolare la tabella con LLCS
     * tramite backtrack ricostruisce la stringa
     * @param u prima stringa
     * @param v seconda stringa
     * @return LCS
     */
    static String lcsDPWithRecursion(String u, String v){

        //calcolo la tabella contenente le lunghezze delle LCS
        int[][] h= llcsDP(u, v);

        //ripercorro la tabella h dall'ultima cella, seguendo le frecce, per ricostruire la stringa
        return backtrack(h, u, v, u.length(), v.length());

    }
    /**
     * ripercorre la tabella h di LLCS dall'ultima cella, seguendo le frecce, per ricostruire la stringa
     * approccio ricorsivo
     * @param h tabella di LLCS
     * @param u prima stringa
     * @param v seconda stringa
     * @param i lunghezza di u
     * @param j lunghezza di v
     * @return LCS
     */
    static String backtrack(int[][] h, String u, String v, int i, int j) {

        //se almeno una delle due stringhe è vuota, allora ritorna la stringa vuota
        if (i == 0 || j == 0) {
            return "";
        } else if (u.charAt(i-1) == v.charAt(j-1)) {
            return backtrack(h, u, v, i - 1, j - 1) + u.charAt(i-1);
        } else if (h[i][j - 1] > h[i - 1][j]) {
            return backtrack(h, u, v, i, j - 1);
        } else {
            return backtrack(h, u, v, i - 1, j);
        }

    }



}
