package PROGETTO_ASD.TEMPO_MEDIO;

//classe per generazione numeri casuali (meglio di random)
public class RandomGenerator {

    private double seed;

    //costruttore
    //genera un'istanza di RandomGenerator a partire dal seme iniziale s
    public RandomGenerator(double s){
        seed = s;
    }

    //restituisce un numero compreso tra 0 e 1
    public double get(){

        //costanti
        final int a = 16087;
        final int m = 2147483647;
        final int q = 127773;
        final int r = 2836;

        double lo, hi, test;

        hi = Math.ceil(seed / q);
        lo = seed - q * hi;
        test = a * lo - r * hi;

        if(test < 0.0){
            seed = test + m;
        } else {
            seed = test;
        }

        return seed / m;
    }

    //restituisce il valore corrente del seme
    public double getSeed(){
        return seed;
    }

    //imposta il valore del seme a s
    public void setSeed(double s){
        seed = s;
    }

    //esempio: stampa 10 numeri casuali compresi tra 1 e 100
    //Math.round ritorna il long più vicino al numero
    public static void main(String[] args) {

        long n, i;

        RandomGenerator rndGen = new RandomGenerator(1234);

        System.out.println("Il valore iniziale del seme è: " + rndGen.getSeed());

        for (i = 1; i <= 10 ; i++) {
            n = Math.round(rndGen.get() * 100);
            System.out.println(n);
        }
    }

}
