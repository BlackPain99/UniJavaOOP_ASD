package PROGETTO_ASD_SECONDO_SEMESTRE;

class AVLTree {

    AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    /**
     * inserisce un nuovo nodo nell'albero AVL con chiave k e valore v.
     * (si assuma che l'albero non contenga già un nodo con chiave k)
     *
     * @param k chiave di tipo intero
     * @param v valore di tipo stringa
     */
    void insert(int k, String v) {

        AVLNode z = new AVLNode(k, v);
        AVLNode y = null;
        AVLNode x = root;

        while (x != null) {
            if (x.key > z.key) {
                y = x;
                x = x.left;
            } else {
                y = x;
                x = x.right;
            }
        }

        //inserimento in un BST vuoto
        if (y == null) {
            root = z;
        } else {

            z.parent = y;

            if (y.key > z.key) {
                y.left = z;
            } else {
                y.right = z;
            }

            //aggiornare le altezze dei nodi a partire dal padre del nodo inserito, y, fino alla radice
            updateHeights(y);

            balanceTheTreeAfterInsertion(y);
        }
    }

    private void balanceTheTreeAfterInsertion(AVLNode y) {
        AVLNode z = firstUnbalancedNode(y);

        if (z == null) {
            return;
        } else {
            int hDiff = getHDiff(z);

            if (hDiff > 1) {

                if (height(z.left.left) < height(z.left.right)) {
                    leftRotate(z.left);
                }
                rightRotate(z);

            } else {

                if (height(z.right.right) < height(z.right.left)) {
                    rightRotate(z.right);
                }
                leftRotate(z);

            }

        }
    }

    private AVLNode firstUnbalancedNode(AVLNode y) {

        if (y == null) {
            return null;
        } else {
            int hDiff = getHDiff(y);

            if (hDiff > 1 || hDiff < -1) {
                return y;
            } else {
                return firstUnbalancedNode(y.parent);
            }
        }
    }

    /**
     * aggiorna le altezze dei nodi a partire dal nodo passato in input fino alla radice
     *
     * @param y nodo da cui partire per aggiornare le altezze
     */
    private void updateHeights(AVLNode y) {

        while (y != null) {

            y.height = maxHeight(y.left, y.right) + 1;

            y = y.parent;
        }

    }

    /**
     * determina altezza più lunga tra due nodi in input
     *
     * @param left  primo nodo (figlio sx)
     * @param right secondo nodo (figlio dx)
     * @return l'altezza più lunga
     */
    private int maxHeight(AVLNode left, AVLNode right) {
        int h1, h2;

        if (left == null)
            h1 = 0;
        else
            h1 = left.height;

        if (right == null)
            h2 = 0;
        else
            h2 = right.height;

        return Math.max(h1, h2);
    }




    /**
     * trova nell'albero il nodo con chiave numerica k.
     * (si assuma che tale nodo esista sempre)
     * si appoggia al metodo findTree(BSTNode, int)
     *
     * @param k chiave di tipo intero
     * @return il valore associato a tale nodo
     */
    String find(int k) {
        return findTree(root, k);
    }

    private String findTree(AVLNode x, int k) {

        if (x == null || x.key == k) {
            assert x != null;
            return x.value;
        } else {

            if (k < x.key) {
                return findTree(x.left, k);
            } else {
                return findTree(x.right, k);
            }

        }

    }

    /**
     * trova nell'albero il nodo con chiave numerica k.
     * (si assuma che tale nodo esista sempre)
     * si appoggia al metodo findTreeNode(BSTNode, int)
     *
     * @param k chiave di tipo intero
     * @return nodo con chiave k
     */
    AVLNode findNode(int k) {
        return findTreeNode(root, k);
    }


    private AVLNode findTreeNode(AVLNode x, int k) {

        if (x == null || x.key == k) {
            return x;
        } else {

            if (k < x.key) {
                return findTreeNode(x.left, k);
            } else {
                return findTreeNode(x.right, k);
            }

        }

    }

    /**
     * rimuove tutti i nodi dall'albero, che diventerà vuoto.
     */
    void clear() {
        root = null;
    }

    /**
     * visualizza l'albero corrente (rappresentazione in forma polacca (notazione prefissa)).
     * Il contenuto di ogni nodo viene rappresentato nel formato "chiave:valore:altezza".
     * Un (sotto-)albero vuoto deve essere rappresentato dalla stringa "NULL".
     */
    void show() {
        showTree(root);
    }

    private void showTree(AVLNode x) {

        if (x == null) {
            System.out.print("NULL ");
        } else {
            System.out.print(x.key + ":" + x.value + ":" + x.height + " ");
            showTree(x.left);
            showTree(x.right);
        }

    }

    /**
     * dato un nodo x con chiave k, trova il nodo che contiene la più piccola chiave più grande di k
     *
     * @param x nodo di cui si vuole conoscere il successore
     * @return il nodo che contiene la più piccola chiave più grande di k
     */
    AVLNode successor(AVLNode x) {

        if (x.right != null) {
            return searchMin(x.right);
        } else {
            AVLNode y = x.parent;

            while (y != null && x != y.left) {
                x = y;
                y = x.parent;
            }

            return y;
        }
    }

    /**
     * trova il nodo con chiave minima
     *
     * @param x nodo di partenza da cui iniziare a cercare la chiave minima
     * @return il nodo con chiave minima
     */
    AVLNode searchMin(AVLNode x) {
        if (x == null || x.left == null) {
            return x;
        } else {
            return searchMin(x.left);
        }

    }

    /**
     * altezza dell'albero
     *
     * @return altezza della radice dell'albero
     */
    int height() {
        if (root == null)
            return 0;

        return root.height;
    }

    int height(AVLNode x) {

        if (x == null) {
            return 0;
        } else {
            return x.height;
        }

    }


    /**
     * metodo per ruotare a dx un sottoalbero con radice y
     *
     * @param y radice del sottoalbero da ruotare
     * @return nuova radice
     */
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        //effettuare la rotazione
        if (y.parent != null) {
            if (y.parent.left == y) {
                x.parent = y.parent;
                x.parent.left = x;
            } else {
                x.parent = y.parent;
                x.parent.right = x;
            }

        } else {
            x.parent = null;
        }
        x.right = y;
        y.parent = x;
        y.left = T2;

        //aggiornare le altezze
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        //ritorno la nuova radice
        return x;
    }

    /**
     * metodo per ruotare a sx un sottoalbero con radice x
     * @param x radice del sottoalbero da ruotare
     * @return nuova radice
     */
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        //effettuare la rotazione
        if(x.parent != null){
            if(x.parent.left == x){
                y.parent = x.parent;
                y.parent.left = y;
            } else {
                y.parent = x.parent;
                y.parent.right = y;
            }

        } else {
            y.parent = null;
        }

        y.left = x;
        x.right = T2;
        x.parent = y;

        //aggiornare le altezze
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        //ritorno la nuova radice
        return y;
    }

    /**
     * calcola di quanto differiscono le altezze dei figli di x
     *
     * @param x nodo di cui si vuole controllare HDiff
     * @return HDiff per x
     */
    int getHDiff(AVLNode x) {
        if (x == null) {
            return 0;
        } else {
            return height(x.left) - height(x.right);
        }

    }
}
