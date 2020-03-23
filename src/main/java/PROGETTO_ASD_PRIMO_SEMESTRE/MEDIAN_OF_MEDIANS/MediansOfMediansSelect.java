package PROGETTO_ASD_PRIMO_SEMESTRE.MEDIAN_OF_MEDIANS;

import PROGETTO_ASD_PRIMO_SEMESTRE.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediansOfMediansSelect {

    public static void main(String[] args) {
        List<Integer> listItems = new ArrayList<>();
        int key = Input.InputToList(listItems);

        int[] items = new int[listItems.size()];
        for (int i = 0; i < listItems.size(); i++){
            items[i] = listItems.get(i);
        }

        Integer[] items2 = new Integer[listItems.size()];
        for (int i = 0; i < listItems.size(); i++){
            items2[i] = listItems.get(i);
        }

        int result = mediansOfMediansSelect(items, 0, items.length - 1, key);
        int result2 = (Integer) MediansOfMediansInPlace.mediansOfMediansInPlace(items2, key-1);

        System.out.println(result);
        System.out.println(result2);
    }

    public static int mediansOfMediansSelect(int[] arr, int low, int high, int k){

        
        if(k > 0 && k <= high - low + 1){

            //numeri di elementi nell'array
            int n = high - low + 1;

            //(n + 4)/ 5 altrimenti non conto l'ultimo blocco con meno di 5 elementi (se esiste)
            int[] median = new int[(n + 4)/ 5];

            //mi permette di sapere la posizione a cui sono arrivato
            int i;

            //calcolo la mediana di tutti i blocchi tranne l'ultimo (caso a parte)
            for(i = 0; i < n/5; i++){
                median[i] = getMedian(arr, low + i * 5, 5  );
            }

            //per l'ultimo gruppo con meno di 5 elementi
            if(i*5 < n){
                median[i] = getMedian(arr, low + i * 5, n % 5);
                i++;
            }

            //se c'è un solo blocco la mediana è facilmente calcolabile
            //altrimenti faccio una chiamata ricorsiva ricercando la mediana k = i / 2
            int medOfMed = (i == 1) ? median[i - 1] : mediansOfMediansSelect(median, 0, i - 1, i / 2);

            //richiamo partition con il pivot medOfMed
            //ottengo la posizione del pivot medOfMed nell'array ordinato
            int pos = partitionWithFreePivot(arr, low, high, medOfMed);

            //se la posizione è la stessa di k
            if(pos - low == k - 1){
                return arr[pos];
            } else if(pos - low > k - 1) {
                return mediansOfMediansSelect(arr, low, pos - 1, k);
            } else {
                return mediansOfMediansSelect(arr, pos + 1, high, k - pos + low - 1);
            }
        }

        //se k è più grande del numero di elementi dell'array
        return -1;
    }

    private static int partitionWithFreePivot(int[] arr, int low, int high, int pivot) {

        //cerca il pivot nell'arr e  lo muove alla fine
        int i;
        for(i = low; i < high; i++){
            if(arr[i] == pivot){
                break;
            }
        }
        swap(arr, i, high);

        //partition standard
        i = (low - 1);
        for(int j = low; j <= high; j++){
            if(arr[j] <= pivot){
                i++;
                swap(arr, i, j);
            }
        }

        return i;
    }

    //trova la mediana in arr. Metodo usato solo per array di lunghezza 5.
    private static int getMedian(int[] arr, int i, int n) {

        Arrays.sort(arr, i, i + n);

        // Return middle element
        return arr[(i + (i + n- 1))/ 2];
    }

    private static void swap(int[] arr, int i, int index){
        if(arr[i] != arr[index]) {
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }


}
