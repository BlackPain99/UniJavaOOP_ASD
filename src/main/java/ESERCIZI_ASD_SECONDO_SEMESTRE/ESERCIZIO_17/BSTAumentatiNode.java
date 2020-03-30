package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_17;

/**
 * Il contenuto di ogni nodo deve essere rappresentato nel formato "chiave:valore".
 * In piu ogni nodo deve "conoscere" la propria altezza.
 * L'altezza, quindi, prenderà tempo costante.
 */
class BSTAumentatiNode {

    private int key;
    private String value;
    private BSTAumentatiNode left, right, parent;
    private int height;


    BSTAumentatiNode(int key, String value){
        this.key = key;
        this.value = value;
        left = null;
        right = null;
        parent = null;
        this.height = 1;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public BSTAumentatiNode getLeft() {
        return left;
    }

    public BSTAumentatiNode getRight() {
        return right;
    }

    public BSTAumentatiNode getParent() {
        return parent;
    }

    public int getHeight() {
        return height;
    }

    public void setLeft(BSTAumentatiNode left) {
        this.left = left;
    }

    public void setRight(BSTAumentatiNode right) {
        this.right = right;
    }

    public void setParent(BSTAumentatiNode parent) {
        this.parent = parent;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
