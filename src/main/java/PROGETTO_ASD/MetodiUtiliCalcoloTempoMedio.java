package PROGETTO_ASD;

public class MetodiUtiliCalcoloTempoMedio {

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            System.out.println(granularità());
        }
    }

    public static long granularità(){
        long start = System.nanoTime();
        long end = System.nanoTime();
        while(end == start){
            end = System.nanoTime();
        }
        return (end - start);
    }
}
