import java.util.LinkedList;
import java.util.Queue;

class ArvoreRecursiva {
    NoRecursivo raiz;

    public int contarNos(NoRecursivo no) {
        if (no == null) return 0;
        return 1 + contarNos(no.esquerda) + contarNos(no.direita);
    }

    public void buscarPreOrdem(NoRecursivo no) {
        if (no == null) return;
        System.out.print(no.valor + " ");
        buscarPreOrdem(no.esquerda);
        buscarPreOrdem(no.direita);
    }

    public void buscarEmOrdem(NoRecursivo no) {
        if (no == null) return;
        buscarEmOrdem(no.esquerda);
        System.out.print(no.valor + " ");
        buscarEmOrdem(no.direita);
    }

    public void buscarPosOrdem(NoRecursivo no) {
        if (no == null) return;
        buscarPosOrdem(no.esquerda);
        buscarPosOrdem(no.direita);
        System.out.print(no.valor + " ");
    }

    public void buscarEmNivel() {
        if (raiz == null) return;

        Queue<NoRecursivo> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            NoRecursivo atual = fila.poll();
            System.out.print(atual.valor + " ");

            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
    }
}
