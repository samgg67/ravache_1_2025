public class MainRecursivo {
    public static void main(String[] args) {
        ArvoreRecursiva arvore = new ArvoreRecursiva();

        arvore.raiz = new NoRecursivo("A");
        arvore.raiz.esquerda = new NoRecursivo("B");
        arvore.raiz.direita = new NoRecursivo("C");
        arvore.raiz.esquerda.esquerda = new NoRecursivo("D");
        arvore.raiz.esquerda.direita = new NoRecursivo("E");
        arvore.raiz.direita.direita = new NoRecursivo("F");

        System.out.println("Total de nós na árvore: " + arvore.contarNos(arvore.raiz));

        System.out.print("Pré-ordem: ");
        arvore.buscarPreOrdem(arvore.raiz);
        System.out.println();

        System.out.print("Em ordem: ");
        arvore.buscarEmOrdem(arvore.raiz);
        System.out.println();

        System.out.print("Pós-ordem: ");
        arvore.buscarPosOrdem(arvore.raiz);
        System.out.println();

        System.out.print("Em nível: ");
        arvore.buscarEmNivel();
        System.out.println();
    }

}
