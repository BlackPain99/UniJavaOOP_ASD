package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_19;

public class RBTNode {

    int key;
    String value;
    String color;
    RBTNode left, right, parent;
    int height;

    public RBTNode(int key, String value, String color) {
        this.key = key;
        this.value = value;
        this.color = color;
        left = null;
        right = null;
        parent = null;
        height = 1;
    }

}
