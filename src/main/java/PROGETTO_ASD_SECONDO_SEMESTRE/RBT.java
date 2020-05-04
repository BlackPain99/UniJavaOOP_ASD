package PROGETTO_ASD_SECONDO_SEMESTRE;

public class RBT {

    RBTNode root;

    public RBT() {
        this.root = null;
    }

    /**
     * inserisce un nuovo nodo nell'albero RBT con chiave k e valore v.
     * (si assuma che l'albero non contenga già un nodo con chiave k)
     * Costo: Θ(logn)
     * @param k chiave di tipo intero
     * @param v valore di tipo stringa
     */
    void insert(int k, String v) {

        RBTNode z = new RBTNode(k, v, "red");
        RBTNode y = null;
        RBTNode x = root;

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
        }

        RBTFixUp(z);
    }

    /**
     * riequilibra l'albero dopo un inserimento (risolve il problema rosso figlio di rosso)
     * nel caso peggiore il problema viene spostato verso l'alto
     * @param x nodo da cui applicare la procedura per ribilanciare l'albero
     */
    private void RBTFixUp(RBTNode x) {
        if (x == root) {
            x.color = "black";
            return;
        }

        if (x.parent.color.equals("black")) {
            return;
        }


        RBTNode zio = trovaZio(x);
        if (color(zio).equals("black")) { //se lo zio di x è black

            //se il padre di x è figlio sx del nonno
            if(x.parent.parent.left == x.parent){

                if(x.parent.left == x){ //caso fortunato (lo zio è opposto a x)

                    rightRotate(x.parent.parent); //ruoto a dx con perno il nonno di x
                    x.parent.right.color = "red"; //coloro di rosso l'ex-nonno di x
                    x.parent.color = "black"; //coloro di nero il genitore di x

                } else { //caso quasi fortunato (lo zio è dalla stessa parte di x)

                    leftRotate(x.parent); //ruoto a sx con perno il genitore di x
                    rightRotate(x.parent); //ruoto a dx sul nuovo genitore di x (x diventa il nodo radice del sottoalbero)
                    x.right.color = "red"; //coloro di rosso il nuovo figlio dx di x
                    x.color = "black"; //coloro di nero x;

                }

            } else { //se il padre di x è figlio dx del nonno

                if(x.parent.right == x){ //caso fortunato (lo zio è opposto a x)

                    leftRotate(x.parent.parent); //ruoto a sx con perno il nonno di x
                    x.parent.left.color = "red"; //coloro di rosso l'ex-nonno di x
                    x.parent.color = "black"; //coloro di nero il genitore di x

                } else { //caso quasi fortunato (lo zio è dalla stessa parte di x)

                    rightRotate(x.parent); //ruoto a dx con perno il genitore di x
                    leftRotate(x.parent); //ruoto a sx sul nuovo genitore di x (x diventa il nodo radice del sottoalbero)
                    x.left.color = "red"; //coloro di rosso il nuovo figlio sx di x
                    x.color = "black"; //coloro di nero x;
                }
            }

        } else { // se lo zio di x è red
            x.parent.color = "black"; //coloro di nero il genitore
            zio.color = "black"; //coloro di nero lo zio
            x.parent.parent.color = "red"; //coloro di rosso il nonno

            //il nonno sarà il nuovo nodo problematico
            RBTFixUp(x.parent.parent);
        }

    }

    private RBTNode trovaZio(RBTNode x) {
        RBTNode parent = x.parent;
        RBTNode grandparent = parent.parent;

        if (grandparent.left == parent) {
            return grandparent.right;
        } else {
            return grandparent.left;
        }

    }

    private String color(RBTNode x) {
        if (x == null) {
            return "black";
        } else {
            return x.color;
        }
    }

    /**
     * trova nell'albero il nodo con chiave numerica k.
     * (si assuma che tale nodo esista sempre)
     * si appoggia al metodo findTree(RBTNode, int)
     *
     * @param k chiave di tipo intero
     * @return il valore associato a tale nodo
     */
    String find(int k) {
        return findTree(root, k);
    }

    private String findTree(RBTNode x, int k) {

        if (x == null) {
            throw new NullPointerException("impossibile cercare un nodo, l'albero è vuoto");
        }

        if (x.key == k) {
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
     * rimuove tutti i nodi dall'albero, che diventerà vuoto.
     */
    void clear() {
        root = null;
    }

    /**
     * visualizza l'albero corrente (rappresentazione in forma polacca (notazione prefissa)).
     * Il contenuto di ogni nodo viene rappresentato nel formato "chiave:valore:colore".
     * Un (sotto-)albero vuoto deve essere rappresentato dalla stringa "NULL".
     */
    void show() {
        showTree(root);
    }

    private void showTree(RBTNode x) {

        if (x == null) {
            System.out.print("NULL ");
        } else {
            System.out.print(x.key + ":" + x.value + ":" + x.color + " ");
            showTree(x.left);
            showTree(x.right);
        }

    }


    /**
     * calcola altezza dell'albero
     *
     * @return altezza della radice dell'albero
     */
    int height() {
        if (root == null)
            return 0;

        return root.height;
    }

    /**
     * calcola altezza dell'albero radicato in x
     *
     * @param x nodo di cui si vuole conoscere l'altezza
     * @return altezza dell'albero radicato in x
     */
    int height(RBTNode x) {

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
    RBTNode rightRotate(RBTNode x) {
        RBTNode parent = x.parent;
        RBTNode y = x.left;

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
    RBTNode leftRotate(RBTNode y) {
        RBTNode parent = y.parent;
        RBTNode x = y.right;

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
     * aggiorna le altezze dei nodi a partire dal nodo passato in input fino alla radice
     * ATTENZIONE: SI POTREBBE MIGLIORARE VERIFCANDO CHE AD OGNI PASSO ABBIA L'ALTEZZA GIUSTA... SE È COSI SI ESCE DAL LOOP SENZA ANDARE FINO ALLA RADICE
     *
     * @param y nodo da cui partire per aggiornare le altezze
     */
    private void updateHeights(RBTNode y) {

        while (y != null) {

            if (y.height == Math.max(height(y.left), height(y.right)) + 1) {
                break;
            }

            y.height = Math.max(height(y.left), height(y.right)) + 1;

            y = y.parent;
        }

    }


}
