package PROGETTO_ASD;

import java.util.ArrayList;
import java.util.List;

public class HeapMin {

    private List<Node> heap;
    private int heapsize;

    public List<Node> getHeap() {
        return heap;
    }

    public void setHeap(List<Node> heap) {
        this.heap = heap;
    }

    public void setHeapsize(int heapsize) {
        this.heapsize = heapsize;
    }

    public int getHeapsize() {
        return heapsize;
    }

    /**
     * inizializza una heap con valori presi da una lista di interi
     * @param items contiene gli elementi da inserire
     */
    public HeapMin(List<Integer> items){
        heap = new ArrayList<>();
        for (int i = 0; i < items.size(); i++){
            heap.add(new Node(i, items.get(i)));
        }
        heapsize = heap.size();
        buildHeap();
    }

    public HeapMin(){
        heap = new ArrayList<Node>();
        heapsize = 0;
    }

    /**
     * se left(i) e right(i) sono radici di minHeap, allora heapify trasforma "i" in radice di minHeap
     * @param i indice da trasformare in radice di minHeap
     */
    public void heapify(int i){

        Node currNode = heap.get(i);
        Node leftNode = currNode.getLeft(heap);
        Node rigthNode = currNode.getRight(heap);

        Node smallest = currNode;

        if((leftNode != null) && (leftNode.getKey() < heapsize) && (leftNode.getVal() < currNode.getVal())){
            smallest = leftNode;
        }

        if((rigthNode != null) && (rigthNode.getKey() < heapsize) && (rigthNode.getVal() < smallest.getVal())){
            smallest = rigthNode;
        }

        if(smallest != currNode){
            int tempVal = currNode.getVal();
            currNode.setVal(smallest.getVal());
            smallest.setVal(tempVal);

            heapify(smallest.getKey());
        }
    }

    /**
     * se il parent del nodo con posizione key ha un valore più grande, scambio i valori dei nodi
     * @param key la posizione del nodo da controllare
     * @return ritorna la posizione del parent se c'è stato uno scambio,
     *          0 se ha raggiunto la radice,
     *          -1 se non ci sono stati scambi
     */
    public int swapWithParent(int key){

        Node node = heap.get(key);
        Node parent = node.getParent(heap);

        if(parent == null){
            return 0; //raggiuta la radice
        }

        if(node.getVal() < parent.getVal()){
            int tempVal = node.getVal();
            node.setVal(parent.getVal());
            parent.setVal(tempVal);
            return parent.getKey();
        }

        return -1; //la posizione del figlio è giusta
    }

    /**
     * inserisce un nuovo nodo nella heap
     * @param val valore del nodo da inserire
     */
    public void insert(int val){
        heapsize++;
        Node newNode = new Node(heapsize - 1, val);
        heap.add(newNode);

        int key = newNode.getKey();
        do{
            key = swapWithParent(key);
        } while(key > 0);
    }

    /**
     * costruisce una minHeap dalla lista di elementi iniziale
     * chiomando ripetutamente heapify, partendo dal primo nodo non foglia fino alla radice
     */
    private void buildHeap(){
        for (int i = (heapsize - 1)/2; i >= 0 ; i--) {
            heapify(i);
        }
        //heapsize = heap.size();

    }

    /**
     * ordina la minHeap rimuovendo ripetutamente l`elemento minimo,
     * piazza l`ultimo elemento nella radice e chiama heapify sulla radice.
     * La heap, infine, viene trasformata in un array i cui elementi sono ordinati in modo crescente
     * @return array con elementi ordinati in modo crescente
     */
    public int[] heapSort(){
        buildHeap();
        for (int i = heap.size() - 1; i > 0; i--) {

            Node firstNode = heap.get(0);
            Node iNode = heap.get(i);

            int tempVal = firstNode.getVal();
            firstNode.setVal(iNode.getVal());
            iNode.setVal(tempVal);

            heapsize--;
            heapify(0);
        }

        int[] sortedArray = new int[heap.size()];
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = heap.get(sortedArray.length - i - 1).getVal();
        }
        return sortedArray;
    }

    public String toString(){
        StringBuffer res = new StringBuffer();

        for (int i = 0; i < heapsize ; i++) {
            Node node = heap.get(i);
            res.append(i + " [Node " + node.toString());

            if(node.getParent(heap) != null && node.getParent(heap).getKey() < heapsize){
                res.append(" Parent " + node.getParent(heap).getVal());
            }

            if(node.getLeft(heap) != null && node.getLeft(heap).getKey() < heapsize){
                res.append(" Left " + node.getLeft(heap).getVal());
            }

            if(node.getRight(heap) != null && node.getRight(heap).getKey() < heapsize){
                res.append(" Right " + node.getRight(heap).getVal());
            }

            res.append("]\n");
        }

        return res.toString();
    }

    public int extract(){

        if (heapsize <= 0){
            throw new IllegalStateException("minHeap is empty");
        } else {

            Node firstNode = heap.get(0);
            Node lastNode = heap.get(heapsize - 1);

            //scambio il primo elemento con l'ultimo
            int tempVal = firstNode.getVal();
            firstNode.setVal(lastNode.getVal());
            lastNode.setVal(tempVal);
            heapsize--;

            //correggo
            heapify(0);
        }

        return heap.get(heapsize).getVal();
    }

    public Node getMinNode(){
        if(heapsize <= 0){
            throw new IllegalStateException("minHeap is empty");
        } else {
            return heap.get(0);
        }

    }

    public void insert2(Node n){

        heapsize++;
        heap.add(n);

        int key = heapsize - 1;
        do{
            key = swapWithParent2(key);
        } while (key > 0);

    }

    public int swapWithParent2(int key){

        Node node = heap.get(key);
        Node parent = node.getParent(heap);

        if(parent == null){
            return 0; //raggiuta la radice
        }

        if(node.getVal() < parent.getVal()){
            heap.set(key, parent);
            heap.set((key + 1) / 2 - 1, node);
            return heap.indexOf(parent);
        }

        return -1; //la posizione del figlio è giusta
    }

    public Node extract2(){

        if (heapsize <= 0){
            throw new IllegalStateException("minHeap is empty");
        } else {

            Node firstNode = heap.get(0);
            Node lastNode = heap.get(heapsize - 1);

            //scambio il primo elemento con l'ultimo
            heap.set(0, lastNode);
            heap.set(heapsize - 1, firstNode);
            heapsize--;

            //correggo
            heapify2(0);
        }

        return heap.get(heapsize);
    }

    public void heapify2(int i){

        Node currNode = heap.get(i);
        Node leftNode = currNode.getLeft(heap);
        Node rigthNode = currNode.getRight(heap);

        Node smallest = currNode;

        if((leftNode != null) && (leftNode.getKey() < heapsize) && (leftNode.getVal() < currNode.getVal())){
            smallest = leftNode;
        }

        if((rigthNode != null) && (rigthNode.getKey() < heapsize) && (rigthNode.getVal() < smallest.getVal())){
            smallest = rigthNode;
        }

        if(smallest != currNode){
            int key = heap.indexOf(smallest);
            heap.set(i, smallest);
            heap.set(key,currNode);


            heapify2(key);
        }
    }






}
