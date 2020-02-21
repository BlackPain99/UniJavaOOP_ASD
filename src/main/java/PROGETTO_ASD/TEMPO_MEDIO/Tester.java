package PROGETTO_ASD.TEMPO_MEDIO;

public class Tester {

    //seme per generazione di vettori con numeri casuali
    public static final int seed = 12345;

    //numero di volte che l'algoritmo viene eseguito su un dato input
    public static final int totInput = 500;

    //errore relativo massimo ammesso in %
    public static final int errRelMax = 1;

    //costante di delta
    //z(1 - alfa/2) funzione di distribuzione normale
    public static final double deltaConst = 1.96;

    //dimensione degli input
    public static int[] inputDimension;

    //numero di ripetizioni della tara per una determinata dimensione di input
    public static int[] inputTaraRip;

    //tempo della tara per una determinata dimensione di input
    public static long[] inputTaraTime;

    //granularità del sistema
    public static long granularità;

    //tempo minimo da misurare (dipende dalla scelta dell'errore di misurazione)
    public static long tMin;

    //array degli algoritmi su cui verrà eseguita la misurazione
    public static Select select[];

    public static RandomGenerator rndGen;


    public static void main(String[] args) {
        main();
    }

    public static void main(){

        long startTime = System.currentTimeMillis();

        /**impostazioni utente**/

        //imposto le dimensioni dell'input
        inputDimension = new int[10];
        inputDimension[0] = 100;
        inputDimension[1] = 200;
        inputDimension[2] = 500;
        inputDimension[3] = 1000;
        inputDimension[4] = 2000;
        inputDimension[5] = 5000;
        inputDimension[6] = 10000;
        inputDimension[7] = 100000;
        inputDimension[8] = 500000;
        inputDimension[9] = 1000000;

        //imposto gli algoritmi Select
        select = new Select[4];
        select[0] = new QuickSelect();
        select[1] = new HeapSelect();
        select[2] = new MediansOfMediansSelect();
        select[3] = new MediansOfMediansInPlace();

        /**fine impostazioni utente **/

        //calcolo granularità del sistema
        granularità = getGranularità();
        System.out.println("% Granularità = " + granularità);

        //calcolo di tMin
        long tMincoeffGranularità = (long) ((float) 1 / (float) errRelMax / (float) 100);
        tMin = granularità * tMincoeffGranularità;
        System.out.println("% Errore ammesso = " + errRelMax + "%");
        System.out.println("% tMin = " + tMin + " (granularità * " + tMincoeffGranularità + "");

        System.out.println("% Costante di delta = " + deltaConst);

        rndGen = new RandomGenerator(seed);
        System.out.println("% Seme = " + seed);

        System.out.println("% Ripetizione degli algoritmi su input diversi = " + totInput);

        //riporto le dimensioni
        System.out.print("% Dimensioni =");
        for (int i = 0; i < inputDimension.length; i++) {
            System.out.print(" " + inputDimension[i]);
        }
        System.out.println("");

        //riporto gli algoritmi
        System.out.print("% Algoritmi =");
        for (int i = 0; i < select.length; i++) {
            System.out.print(" " + select[i].getName());
        }
        System.out.println("");











    }


    //calcola la granularità del sistema
    public static long getGranularità() {

        long t0 = System.currentTimeMillis();
        long t1 = System.currentTimeMillis();

        while(t0 == t1){
            t1 = System.currentTimeMillis();
        }

        return (t1 - t0);
    }

    private static void showVect(int[] vect){

        System.out.print("VECT: [" + vect.length + "]");

        for (int i = 0; i < vect.length; i++) {
            System.out.print(" " + vect[i]);
        }

        System.out.println("");
    }


}
