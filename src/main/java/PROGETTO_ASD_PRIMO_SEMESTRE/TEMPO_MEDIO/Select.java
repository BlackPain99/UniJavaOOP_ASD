package PROGETTO_ASD_PRIMO_SEMESTRE.TEMPO_MEDIO;

public abstract class Select {

    public static final int cicliErrati = 5;

    public abstract String getName();

    /**
     * esegue l'algoritmo vero e proprio (esecuzione unitaria dell'algoritmo)
     * calcolo del k-esimo elemento più piccolo in un vettore non ordinato di interi
     * @param input vettore passato all'algoritmo
     * @param k k-esimo elemento più piccolo da cercare nell'array
     */
    public abstract int execute(int[] input, int k);



    /**
     * esegue rip volte l'operazione copy su input e execute su Select
     * @param input rappresentazione di un input vettoriale
     * @param rip indica il numero di volte che bisogna eseguire l'algoritmo
     * @return tempo medio per l'esecuzione delle due operazioni
     */
    public double executeTest(Input input, int rip){

        double t0 = System.currentTimeMillis();

        for (int i = 0; i < rip ; i++) {
            execute(input.copy(), input.getK());
        }

        double t1 = System.currentTimeMillis();

        return (t1 - t0) / rip;
    }

    /**
     * calcola il numero di ripetizioni lorde necessarie ad eseguire l'operazione copy su Input
     * e execute su Select affinchè siano dell'ordine di grandezza che ci permetta la misurazione.
     * @param tMin durata minima che le ripetizioni devono assicurare al sistema
     * @param input rappresentazione di un input vettoriale
     * @return numero di ripetizioni lorde necessarie
     */
    public int getRipLordo(long tMin, Input input){

        long t0 = 0;
        long t1 = 0;
        int rip = 1;
        int[] vect = input.copy();

        while (t1 - t0 <= tMin){

            rip = rip * 2;

            t0 = System.currentTimeMillis();

            for (int i = 1; i <= rip ; i++) {
                execute(input.copy(), input.getK());
            }

            t1 = System.currentTimeMillis();

        }

        int max = rip;
        int min = rip / 2;

        while (max - min >= cicliErrati){

            rip = (max + min) / 2;
            t0 = System.currentTimeMillis();

            for (int i = 1; i <= rip; i++) {
                execute(input.copy(), input.getK());
            }

            t1 = System.currentTimeMillis();

            if(t1 - t0 <= tMin){
                min = rip;
            } else {
                max = rip;
            }

        }

        return max;
    }

    /**
     * scambia elemento in posizione i con l'elemento in posizione j in vett
     * @param vett vettore in cui avviene lo scambio
     * @param i indice del primo elemento
     * @param j indice del secondo elemento
     */
    protected static void swap(int[] vett, int i, int j){
        int temp = vett[i];
        vett[i] = vett[j];
        vett[j] = temp;
    }

}


