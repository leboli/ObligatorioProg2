import TADs.BinaryTree.SearchBinaryTree;
import TADexceptions.ListaVaciaExcepcion;

public class Main {

    public static void main(String[] args) throws ListaVaciaExcepcion {
        // ---------------------------------- ATRIBUTOS -----------------------------------------
        // --------------------------------- CONSTRUCTOR ----------------------------------------
        // ------------------------------ SETTERS & GETTERS -------------------------------------
        // ----------------------------------- METODOS ------------------------------------------

        SearchBinaryTree<Integer> myEmptySBT = new SearchBinaryTree<>();

        myEmptySBT.add(2);
        myEmptySBT.add(1);
        myEmptySBT.add(3);
        myEmptySBT.add(5);
        myEmptySBT.add(4);
        myEmptySBT.add(-1);

        System.out.println(myEmptySBT.inOrder());


    }

}
