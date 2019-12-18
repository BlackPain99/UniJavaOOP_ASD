package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.List;

public class MediansOfMediansInPlace {

    public static void main(String[] args) {
        List<Integer> listItems = new ArrayList<>();
        int key = Input.InputToList(listItems);

        int[] items = new int[listItems.size()];
        for (int i = 0; i < listItems.size(); i++){
            items[i] = listItems.get(i);
        }

        int result = medianOfMediansSelect(items, 0, items.length - 1, key - 1);

        System.out.println(result);
    }


    /**
     * trova la posizione della mediana delle mediane (miglior pivot)
     * @param arr array in cui cercare il pivot
     * @param left posizone iniziale per la ricerca
     * @param right posizione finale per la ricerca
     * @return la posizione della mediana delle mediane (miglior pivot)
     */
    private static int findPivot(int[] arr, int left, int right){
        int tempRight, medianSingleBlock, mediansOfMedians;

        //se ci sono meno di 5 elementi, calcolo subito la  posizione della mediana
        if(right - left < 5){
            return findPosMedian(arr, left, right);
        }

        //altrimenti sposto le mediane dei sottogruppi di 5 elementi nelle prime n/5 posizioni
        for(int i = left; i <= right; i = i + 5){

            //calcolo la posizione mediana dell'i-esimo gruppo di 5 elementi
            tempRight = i + 4;
            if(tempRight > right){
                tempRight = right;
            }

            medianSingleBlock = findPosMedian(arr, i, tempRight);

            swap(arr, medianSingleBlock, left + (i - left)/5);
        }

        //calcolo la posizione della mediana delle n/5 mediane dei rispettivi gruppi
        mediansOfMedians = (right - left)/(5 * 2) + left + 1;

        return medianOfMediansSelect(arr, left, left + (right - left)/5, mediansOfMedians);
    }

    /**
     * trova l'n-esimo elemento più piccolo dell'array
     * @param arr array in cui effettuare la ricerca
     * @param left posizone iniziale per la ricerca
     * @param right posizione finale per la ricerca
     * @param n n-esimo elemento
     * @return l'n-esimo elemento più piccolo dell'array
     */
    private static int medianOfMediansSelect(int[] arr, int left, int right, int n) {
        int pivotIndex;

        while(left != right){

            pivotIndex = findPivot(arr, left, right);
            pivotIndex = partition(arr, left, right, pivotIndex, n);

            if( n == pivotIndex){
                return arr[n];
            } else if( n < pivotIndex){
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }

        return arr[left];
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, int n) {

        int pivotValue = arr[pivotIndex];

        //muovo il pivot alla fine dell'array
        swap(arr, pivotIndex, right);

        int storeIndex = left;

        //muovo tutti gli elementi più piccoli del pivot alla sua sinistra
        for (int i = left; i < right ; i++) {

            if(arr[i] < pivotValue){
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        int storeIndexEq = storeIndex;
        //muovo tutti gli elementi uguali al pivot subito dopo gli elementi minori del pivot
        for (int i = storeIndex; i < right; i++){

            if(arr[i] == pivotValue){
                swap(arr, storeIndexEq, i);
                storeIndexEq++;
            }
        }

        //sposto il pivot nella sua posizione finale
        swap(arr, right, storeIndexEq);

        //ritorna la locazione del pivot in base alla desiderata locazione n
        if(n < storeIndex){
            //n è nel gruppo degli elementi piu piccoli del pivot
            return storeIndex;
        } else if (n <= storeIndexEq){
            //n è nel gruppo degli elementi uguali al pivot
            return n;
        } else {
            //n è nel gruppo degli elementi più grandi del pivot
            return storeIndexEq;
        }
    }


    /**
     * ritorna la posizione della mediana di un gruppo di al max 5 elementi (con insertion sort)
     * @param arr array in cui cercare la mediana
     * @param left posizione di partenza della ricerca
     * @param right posizione di arrivo della ricerca
     * @return
     */
    private static int findPosMedian(int[] arr, int left, int right) {

        int i = left + 1;
        int j;

        while(i <= right){
            j = i;

            while(j > left && arr[j - 1] > arr[j]){
                swap(arr, j - 1, j);
                j--;
            }

            i++;
        }

        return (left + right)/2;
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

    /**
     * scambia l'elemento in arr in posizione i con quello in posizione index
     * @param arr array in cui avviene lo scambio
     * @param i indice del primo elemento
     * @param index indice del secondo elemento
     */
    private static void swap(int[] arr, int i, int index){
        if(arr[i] != arr[index]) {
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }




}
