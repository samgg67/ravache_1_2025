public class MainIterativo {
    public static void main(String[] args) {
        ArvoreIterativa arvore = new ArvoreIterativa();

        arvore.raiz = new NoIterativo("A");
        arvore.raiz.esquerda = new NoIterativo("B");
        arvore.raiz.direita = new NoIterativo("C");
        arvore.raiz.esquerda.esquerda = new NoIterativo("D");
        arvore.raiz.esquerda.direita = new NoIterativo("E");
        arvore.raiz.direita.direita = new NoIterativo("F");

        System.out.println("Total de nós na árvore (recursivo ou padrão): " + arvore.contarNos());


        System.out.println("Total de nós usando fila: " + arvore.nosFila());
        System.out.println("Total de nós usando pilha: " + arvore.nosPilha());

        System.out.print("Pré-ordem: ");
        arvore.buscarPreOrdem();
        System.out.println();

        System.out.print("Em ordem: ");
        arvore.buscarEmOrdem();
        System.out.println();

        System.out.print("Pós-ordem: ");
        arvore.buscarPosOrdem();
        System.out.println();

        System.out.print("Em nível: ");
        arvore.buscarEmNivel();
        System.out.println();
    }
}
