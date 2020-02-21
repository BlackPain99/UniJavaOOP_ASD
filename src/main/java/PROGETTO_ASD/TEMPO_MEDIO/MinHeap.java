package PROGETTO_ASD.TEMPO_MEDIO;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private int heapSize;
    private List<Integer> IntList;


    public int getHeapSize() {
        return heapSize;
    }

    public List<Integer> getIntList() {
        return IntList;
    }

    public int getIntListSize() {
        return IntList.size();
    }

    public int getMin(){
        return IntList.get(0);
    }


    public int left(int i){
        return 2*i+1;
    }

    public int right(int i){
        return 2*i+2;
    }

    public int parent(int i){
        return (i-1)/2;
    }

    /**
     * costruttore minHeap vuota (inserire elementi tramite metodo insert )
     */
    public MinHeap(){
        heapSize = 0;
        IntList = new ArrayList<Integer>();
    }

    /**
     * costruttore minHeap attraverso elementi contenuti in un ArrayList
     * @param items elementi su cui costruire la minHeap
     */
    public MinHeap(List<Integer> items) {
        this.heapSize = items.size();
        IntList = new ArrayList<>(items);
        buildMinHeap();
    }

    /**
     * costruisce la minHeap spostando gli elementi di IntList
     */
    private void buildMinHeap(){
        int startIndex = (heapSize / 2) - 1;

        for (int i = startIndex; i >= 0; i--){
            heapify(i);
        }
    }

    /**
     * se left(i) e right(i) sono radici di minHeap, allora heapify trasforma "i" in radice di minHeap.
     * @param i indice da trasformare in radice di minHeap
     */
    public void heapify(int i){

        int l = left(i);
        int r = right(i);
        int min = 0;

        //trovo il minimo elemento tra quello in posizione i, left(i), right(i)
        if(l < heapSize && IntList.get(l) < IntList.get(i)) {
            min = l;
        } else {
            min = i;
        }

        if(r < heapSize && IntList.get(r) < IntList.get(min)){
            min = r;
        }

        if(min != i){
            //scambio elemento in posizione i con elemento in posizione min
            swap(i, min);

            //correggo errore creato in posizione min
            heapify(min);
        }
    }

    /**
     * scambia elemento in posizione x con elemento in posizione y in IntList
     * @param x posizione primo elemento
     * @param y posizione secondo elemento
     */
    private void swap(int x, int y){
        int temp = IntList.get(x);
        IntList.set(x, IntList.get(y));
        IntList.set(y, temp);
    }

    public void insert(int key){
        heapSize++;

        //inserisco elemento key alla fine della minheap
        int i = heapSize - 1;
        IntList.set(i, key);

        //esco quando il genitore è minore del figlio
        while(i > 0 && IntList.get(i) < IntList.get(parent(i))){
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int extract(){
        if (heapSize == 0){
            throw new IllegalStateException("minHeap is empty");
        } else {

            //scambio il primo elemento con l'ultimo
            int lastElement = heapSize - 1;
            swap(0, lastElement);
            heapSize--;

            //correggo
            heapify(0);
        }
        return IntList.get(heapSize);
    }

    public void printMinHeap(){
        for (int i = 0; i < heapSize; i++){
            System.out.print(IntList.get(i) + " ");
        }
        System.out.println();
    }

    
}

class Node implements Comparable<Node> {

    private int key; //posizione
    private int val; //valore


    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public int getKey() {
        return key;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String toString() {
        return "" + val;
    }

    public int parent(int i){
        return (i-1)/2;
    }

    public int left(int i){
        return 2*i+1;
    }

    public int right(int i){
        return 2*i+2;
    }

    public Node getParent(List<Node> heap) {
        if (key == 0) {
            return null;
        }
        int parentKey = (key + 1) / 2 - 1;
        return heap.get(parentKey);
    }

    public Node getLeft(List<Node> heap) {
        int leftKey = 2 * (key + 1) - 1;
        if (leftKey >= heap.size()) {
            return null;
        }
        return heap.get(leftKey);
    }

    public Node getRight(List<Node> heap) {
        int rightKey = 2 * (key + 1);
        if (rightKey >= heap.size()) {
            return null;
        }
        return heap.get(rightKey);
    }


    @Override
    public int compareTo(Node o) {

        if (this.getVal() < o.getVal()) {
            return -1;
        } else if (this.getVal() == o.getVal()) {
            return 0;
        } else {
            return 1;
        }
    }
}
