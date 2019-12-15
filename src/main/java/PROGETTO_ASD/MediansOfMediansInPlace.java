package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediansOfMediansInPlace {

    public static void main(String[] args) {
        List<Integer> listItems = new ArrayList<>();
        int key = Input.InputToList(listItems);

        int[] items = new int[listItems.size()];
        for (int i = 0; i < listItems.size(); i++){
            items[i] = listItems.get(i);
        }

        int result = mediansOfMediansSelectInPlace(items, 0, items.length - 1, key);

        System.out.println(result);
    }

    public static int mediansOfMediansSelectInPlace(int[] arr, int low, int high, int k){

        if(k > 0 && k <= high - low + 1){

            //numeri di elementi nell'array
            int n = high - low + 1;

            int i;
            //calcolo la mediana di tutti i blocchi tranne l'ultimo (caso a parte)
            for(i = 0; i < n/5; i++){
                arr[i] = findMedian(arr, low + i * 5, 5 );
            }

            //per il gruppo con meno di 5 elementi (calcolo la mediana dell'ultimo blocco)
            if(i * 5 < n){
                arr[i] = findMedian(arr, low + i * 5, n % 5);
                i++;
            }

            //se c'è un solo blocco la mediana è facilmente calcolabile.
            //altrimenti faccio una chiamata ricorsiva ricercando la mediana k = i / 2
            int medOfMed = (i == 1)? arr[i - 1] : mediansOfMediansSelectInPlace(arr, 0, i - 1, i / 2);

            int r = partitionWithFreePivot(arr, low, high, medOfMed);

            if(r - low == k - 1){
                return arr[r];
            } else if(r - low > k - 1) {
                return mediansOfMediansSelectInPlace(arr, low, r - 1, k);
            } else {
                return mediansOfMediansSelectInPlace(arr, r + 1, high, k - (r + 1) + low);
            }
        }

        return -1;
    }

    private static int findMedian(int[] arr, int i, int n) {
        Arrays.sort(arr, i, i + n);
        return arr[n/2];
    }

    private static int partitionWithFreePivot(int[] arr, int low, int high, int pivot) {

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == pivot){
                swap(arr, i, high);
                break;
            }
        }

        int i = (low - 1);
        for(int j = low; j <= high; j++){
            if(arr[j] <= pivot){
                i++;
                swap(arr, i, j);
            }
        }

        return i;
    }

    private static void swap(int[] arr, int i, int index){
        if(arr[i] != arr[index]) {
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }
}
