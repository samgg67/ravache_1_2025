public class MainAVL {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        arvore.inserir(30);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(10);
        arvore.inserir(25);

        System.out.print("Árvore em ordem: ");
        arvore.percorrerEmOrdem();

        System.out.println("Remover o valor 20");
        arvore.remover(20);

        System.out.print("Árvore após remoção: ");
        arvore.percorrerEmOrdem();
    }
}
