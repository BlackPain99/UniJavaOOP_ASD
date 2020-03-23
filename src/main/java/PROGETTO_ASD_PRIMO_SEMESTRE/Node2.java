package PROGETTO_ASD_PRIMO_SEMESTRE;

class Node2 implements Comparable<Node2>{

    private int key; //posizione
    private int val; //valore

    public Node2(int key, int val) {
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

    public String toString(){
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

    @Override
    public int compareTo(Node2 o) {

        if(this.getVal() < o.getVal()){
            return -1;
        } else if(this.getVal() == o.getVal()){
            return 0;
        } else {
            return 1;
        }
    }


}
