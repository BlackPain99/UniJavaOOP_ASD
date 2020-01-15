package PROGETTO_ASD.TEMPO_MEDIO;

public class MetodiUtiliCalcoloTempoMedio {

    //I tempi di esecuzione devono essere misurati con un errore relativo massimo pari a 0.01 (1%)
    //calcolare preliminarmente la risoluzione dell'orologio monotono
    //stima del tempo medio di esecuzione e della sua deviazione standard
    //produrre in output una sequenza di record nel formato "N K T1 D1 T2 D2 T3 D3"
    //N è la dimensione del vettore generato,
    //K è un'istanza del parametro k,
    //Ti (per i=1,2,3) è una stima del tempo medio del i-esimo algoritmo,
    //Di una stima della relativa deviazione standard.

    public static void main(String[] args) {

        for(int i = 0; i < 1000; i++){
            System.out.println(getGranularità());
        }

    }

    public static long getGranularità(){

        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();

        while(end == start){
            end = System.currentTimeMillis();
        }

        return (end - start);
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
