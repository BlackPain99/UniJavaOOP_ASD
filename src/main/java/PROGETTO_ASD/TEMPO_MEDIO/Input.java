package PROGETTO_ASD.TEMPO_MEDIO;

//rappresentazione di un input vettoriale
public class Input {

    public static final int cicliErrati = 5;
    private RandomGenerator rdnGen;
    private int[] inputVector;
    private int k;

    //crea un array di dimensione dimension e chiama populate per riempirlo con valori casuali
    public Input(RandomGenerator rdnGen, int dimension) {
        this.rdnGen = rdnGen;
        inputVector = new int[dimension];
        populate();
        this.k = (int) Math.round(rdnGen.get() * inputVector.length);
    }

    //popola l'array con valori casuali
    private void populate() {
        for (int i = 0; i < inputVector.length; i++) {
            inputVector[i] = (int) Math.round(rdnGen.get() * inputVector.length);
        }
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    //restituisce un input in formato int[] duplicato da quello rappresentato dalla classe
    public int[] copy() {

        int[] ret = new int[inputVector.length];
        for (int i = 0; i < inputVector.length ; i++) {
            ret[i] = inputVector[i];
        }

        return ret;
    }


    //restituisce il tempo di tara per questo input
    //tareRip rappresenta il numero di ripetizioni minime per la tara
    public long getTaraTime(int tareRip) {

        long t0 = System.currentTimeMillis();

        for (int i = 0; i < tareRip; i++) {
            copy();
        }

        long t1 = System.currentTimeMillis();

        return (t1 - t0);
    }


    //Restituisce le ripetizioni minime per la tara (tratto dallo pseudocodice del professore Alberto Policriti
    //tMin rappresenta la durata minima che le ripetizioni devono assicurare al sistema
    //(affinchè la si possa misurare con l'orologio di sistema)

    public int getTaraRip(long tMin){

        long t0 = 0;
        long t1 = 0;

        int rip = 1;

        while(t1 - t0 <= tMin){

            //stima di rip con crescita esponenziale
            rip = 2 * rip;
            t0 = System.currentTimeMillis();

            for(int i = 1; i <= rip; i++){
                copy();
            }

            t1 = System.currentTimeMillis();
        }

        //ricerca esatta del numero di ripetizioni per bisezione
        //approssimare a 5 cicli

        int max = rip;
        int min = rip / 2;

        while(max - min >= cicliErrati){

            rip = (max + min) / 2;
            t0 = System.currentTimeMillis();

            for(int i = 1; i <= rip; i++){
                copy();
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


}

