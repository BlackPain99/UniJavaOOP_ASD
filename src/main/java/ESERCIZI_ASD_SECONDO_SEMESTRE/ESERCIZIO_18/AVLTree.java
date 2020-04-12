package ESERCIZI_ASD_SECONDO_SEMESTRE.ESERCIZIO_18;

public class AVLTree {

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
     * ATTENZIONE: SI POTREBBE MIGLIORARE VERIFCANDO CHE AD OGNI PASSO ABBIA L'ALTEZZA GIUSTA... SE È COSI SI ESCE DAL LOOP SENZA ANDARE FINO ALLA RADICE
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
     * rimuove dall'albero il nodo che contiene la chiave numerica k.
     * (si assuma che tale nodo esista sempre)
     *
     * @param k chiave di tipo intero
     */
    void remove(int k) {
        AVLNode z = findNode(k);

        //nodo non trovato
        if (z == null) {
            return;
        }

        removeTree(z);
    }


    private void removeTree(AVLNode z) {

        //x è il nodo effettivamente da eliminare (può essere il nodo stesso o il suo successore)
        AVLNode x, v;

        if (z.left == null || z.right == null) {
            x = z;
        } else {
            x = successor(z);
        }

        //determino il figlio di x che lo andrà a sostituire
        if (x.left != null) {
            v = x.left;
        } else {
            v = x.right;
        }

        //aggiorno il parent di v al parent di x (ovviamente se v non è null)
        if (v != null) {
            v.parent = x.parent;
        }

        AVLNode parent = x.parent;

        //se x è la radice, allora v sarà la nuova radice (il parent è già assegnato a null dall'if precedente)
        if (x.parent == null) {
            root = v;
        } else {

            //adesso mi occupo di aggiornare i puntatori del padre di x
            if (x == x.parent.left) {
                x.parent.left = v;
            } else {
                x.parent.right = v;
            }

        }

        if (x != z) {
            z.key = x.key;
            z.value = x.value;
        }

        updateHeights(parent);

        balanceTheTreeAfterDelete(parent);


    }

    private void balanceTheTreeAfterDelete(AVLNode y) {
        AVLNode z = firstUnbalancedNode(y);

        if (z == null) {
            return;
        } else {
            int hDiff = getHDiff(z);

            int originalHeight = height(z);

            //left-left case or left-right case
            if (hDiff > 1) {

                if (height(z.left.left) < height(z.left.right)) {
                    leftRotate(z.left);
                }
                rightRotate(z);

            } else { //right-right case or right-left case

                if (height(z.right.right) < height(z.right.left)) {
                    rightRotate(z.right);
                }
                leftRotate(z);

            }

            if (originalHeight != height(z.parent)) {
                balanceTheTreeAfterDelete(z.parent.parent);
            }

        }
    }


    /**
     * trova nell'albero il nodo con chiave numerica k.
     * (si assuma che tale nodo esista sempre)
     * si appoggia al metodo findTree(AVLNode, int)
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
     * controlla la correttezza dei puntatori
     *
     * @return true se è un albero binario valido, false altrimenti
     */
    boolean isValid() {
        return isTree(root);
    }

    boolean isTree(AVLNode x) {

        if (x == null) {
            return true;
        }

        if (x == root && x.parent != null) {
            return false;
        }

        if (x.left != null && x.left.parent != x) {
            return false;
        }

        if (x.right != null && x.right.parent != x) {
            return false;
        }

        return isTree(x.left) && isTree(x.right);
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
     * metodo per ruotare a dx un sottoalbero con radice x
     *
     * @param x radice del sottoalbero da ruotare
     * @return nuova radice
     */
    AVLNode rightRotate(AVLNode x) {
        AVLNode parent = x.parent;
        AVLNode y = x.left;

        x.left = y.right;

        if (y.right != null) {
            y.right.parent = x;
        }

        if (parent == null) {
            root = y;
        } else if (parent.right == x) {
            parent.right = y;
        } else if (parent.left == x) {
            parent.left = y;
        }

        y.right = x;
        x.parent = y;
        y.parent = parent;

        //aggiornare le altezze
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        updateHeights(parent);

        //ritorno la nuova radice
        return y;
    }

    /**
     * metodo per ruotare a sx un sottoalbero con radice y
     *
     * @param y radice del sottoalbero da ruotare
     * @return nuova radice
     */
    AVLNode leftRotate(AVLNode y) {
        AVLNode parent = y.parent;
        AVLNode x = y.right;

        y.right = x.left;

        if (x.left != null) {
            x.left.parent = y;
        }

        if (parent == null) {
            root = x;
        } else if (parent.right == y) {
            parent.right = x;
        } else if (parent.left == y) {
            parent.left = x;
        }

        x.left = y;
        y.parent = x;
        x.parent = parent;

        //aggiornare le altezze
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        updateHeights(parent);
        //ritorno la nuova radice
        return x;
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
