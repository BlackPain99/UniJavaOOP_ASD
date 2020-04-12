package ESERCIZIO_20;

public class LCS {

    private static final String UNKNOWN = null;

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


}
