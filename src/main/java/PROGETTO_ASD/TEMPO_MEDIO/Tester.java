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

    //numero di ripetizioni della tara necessarie per calcolare il tempo di tara per una determinata dimensione di input
    public static int[] inputTaraRip;

    //tempo effettivo di tara per una determinata dimensione di input
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
        inputDimension = new int[9];
        inputDimension[0] = 100;
        inputDimension[1] = 200;
        inputDimension[2] = 500;
        inputDimension[3] = 1000;
        inputDimension[4] = 2000;
        inputDimension[5] = 5000;
        inputDimension[6] = 10000;
        inputDimension[7] = 100000;
        inputDimension[8] = 500000;
        //inputDimension[9] = 1000000;

        //imposto gli algoritmi Select
        select = new Select[4];
        select[0] = new QuickSelect();
        select[1] = new MediansOfMediansSelect();
        select[2] = new MediansOfMediansInPlace();
        select[3] = new HeapSelect();

        /**fine impostazioni utente **/

        //calcolo granularità del sistema
        granularità = getGranularità();
        System.out.println("Granularità = " + granularità);

        //calcolo di tMin
        long tMincoeffGranularità = (long) ((float) 1 / (float) errRelMax / (float) 100);
        tMin = granularità * tMincoeffGranularità;
        System.out.println("Errore ammesso = " + errRelMax + "%");
        System.out.println("tMin = " + tMin + " (granularità * " + tMincoeffGranularità + ")");

        System.out.println("Costante di delta = " + deltaConst);

        rndGen = new RandomGenerator(seed);
        System.out.println("Seme = " + seed);

        System.out.println("Ripetizione degli algoritmi su input diversi = " + totInput);

        //riporto le dimensioni
        System.out.print("Dimensioni =");
        for (int i = 0; i < inputDimension.length; i++) {
            System.out.print(" " + inputDimension[i]);
        }
        System.out.println("");

        //riporto gli algoritmi
        System.out.print("Algoritmi =");
        for (int i = 0; i < select.length; i++) {
            System.out.print(" " + select[i].getName());
        }
        System.out.println();
        System.out.println();
        
        /**Tara**/
        //Calcolo la tara degli input
        inputTaraRip = new int[inputDimension.length];
        inputTaraTime = new long[inputDimension.length];

        for (int i = 0; i < inputDimension.length; i++) {
            System.out.print("Calcolo della tara per la dimensione " + inputDimension[i] + "...");

            Input input = new Input(rndGen, inputDimension[i]);

            inputTaraRip[i] = input.getTaraRip(tMin);
            System.out.print(" rip = " + inputTaraRip[i]);

            inputTaraTime[i] = input.getTaraTime(inputTaraRip[i]);
            System.out.println(" time = " + inputTaraTime[i]);
        }

        System.out.println("");
        System.out.println("* Tabella tara *");
        System.out.println("Nell'ordine: indice, dimensione, ripetizioni della tara, tempo di tara");

        for (int i = 0; i < inputDimension.length; i++) {
            System.out.println(i + "\t\t" + inputDimension[i] + "\t\t" + inputTaraRip[i] + "\t\t" + inputTaraTime[i]);
        }

        /**Algoritmi**/
        System.out.println("");
        System.out.println("* Tabella esecuzione algoritmi *");
        //System.out.println("Nell'ordine: indexAlg, indexDim, Algoritmo, Dimensione, 1°ripLordo, tLordo, tLordo^2, tMedio, Delta, DevStd, Time");
        System.out.println("Nell'ordine: Algoritmo, Dimensione, tMedio, DevStd");
        for (int alg = 0; alg < select.length; alg++) {

            /**Dimensioni**/
            for (int dim = 0; dim < inputDimension.length; dim++) {
                //System.out.print(alg);
                //System.out.print("\t\t" + dim);
                System.out.print(select[alg].getName());
                System.out.print("\t\t" + inputDimension[dim]);

                //istanzio e azzero tLordo
                double tLordo = 0;
                double tLordoQuadrato = 0;

                long totInputTime = System.currentTimeMillis();

                /**Input diversi eseguiti ripLordo volte**/
                for (int nInput = 0; nInput < totInput; nInput++) {

                    //Genero input
                    Input input = new Input(rndGen, inputDimension[dim]);

                    //Calcolo ripLordo
                    int ripLordo = select[alg].getRipLordo(tMin, input);

                    /*
                    if (nInput == 0){
                        System.out.print("\t\t" + ripLordo);
                    }
                    */

                    //Aggiungo il tempo lordo impiegato a tLordo
                    double tAlg = select[alg].executeTest(input, ripLordo);
                    tLordo = tLordo + tAlg;
                    tLordoQuadrato = tLordoQuadrato + (tAlg * tAlg);
                }

                tLordo = tLordo / totInput;
                tLordoQuadrato = tLordoQuadrato / totInput;
                //System.out.print("\t\t" + tLordo);
                //System.out.print("\t\t" + tLordoQuadrato);

                //tMedio
                double tMedio = (tLordo - ((double) inputTaraTime[dim] / (double) inputTaraRip[dim]));
                System.out.print("\t\t" + tMedio);

                //Delta & Deviazione Standard
                double devStd = (Math.sqrt(tLordoQuadrato - (tMedio * tMedio)));
                double delta = (1 / Math.sqrt(totInput)) * deltaConst * devStd;
                //System.out.print("\t\t" + delta);
                System.out.print("\t\t" + devStd);

                //System.currentTimeMillis();
                //System.out.print("\t\t" + (System.currentTimeMillis() - totInputTime));
                System.out.println("");
            }
        }

        //Fine
        System.out.println("");
        System.out.println("< Terminato (" + (System.currentTimeMillis() - startTime) + "ms)");
    }

    public static void Test(int seed){
        System.out.println("");
        System.out.println("Test started with seed " + seed);

        rndGen = new RandomGenerator(seed);

        Input input = new Input(rndGen, 1000);
        int k = input.getK();
        System.out.println(k + " -esimo elemento più piccolo");
        int[] vect = input.copy();

        /*
        System.out.println("before: ");
        showVect(vect);
        */

        Select alg = new MediansOfMediansInPlace();
        System.out.println("executing " + alg.getName() + "...");

        long t0 = System.currentTimeMillis();
        int result = alg.execute(vect, k);
        long t1 = System.currentTimeMillis();
        System.out.println("Result: " + result);
        System.out.println("done in " + (t1-t0) + "ms");

        /*
        System.out.println("after: ");
        showVect(vect);
        */
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
