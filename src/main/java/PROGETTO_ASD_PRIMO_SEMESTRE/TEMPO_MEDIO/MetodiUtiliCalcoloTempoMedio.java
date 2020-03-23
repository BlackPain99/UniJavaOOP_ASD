package PROGETTO_ASD_PRIMO_SEMESTRE.TEMPO_MEDIO;

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



}
