package PROGETTO_ASD_SECONDO_SEMESTRE;

class AVLNode {

    int key;
    String value;
    AVLNode left, right, parent;
    int height;

    public AVLNode(int key, String value) {
        this.key = key;
        this.value = value;
        left = null;
        right = null;
        parent = null;
        height = 1;
    }

}
