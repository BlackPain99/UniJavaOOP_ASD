package PROGETTO_ASD.TEMPO_MEDIO;

//rappresentazione di un input vettoriale
public class Input {

    public static final int cicliErrati = 5;
    private RandomGenerator rdnGen;
    private int[] inputVector;

    public Input(RandomGenerator rdnGen, int dimension) {
        //crea un array di dimensione dimension
        this.rdnGen = rdnGen;
        inputVector = new int[dimension];
        populate();
    }

    private void populate() {
        //popola l'array con valori casuali
        for (int i = 0; i < inputVector.length; i++) {
            inputVector[i] = (int) Math.round(rdnGen.get() * inputVector.length);
        }
    }

    public int[] copy() {
        //restituisce un input in formato int[] duplicato da quello rappresentato dalla classe

        int[] ret = new int[inputVector.length];
        for (int i = 0; i < inputVector.length ; i++) {
            ret[i] = inputVector[i];
        }

        return ret;
    }


    public long getTaraTime(int tareRip) {
        //restituisce il tempo di tara per questo input

        long t0 = System.currentTimeMillis();

        for (int i = 0; i < tareRip; i++) {
            copy();
        }

        long t1 = System.currentTimeMillis();

        return (t1 - t0);
    }


    public int getTaraRip(long tMin){
        //Restituisce le ripetizioni minime per la tara

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

