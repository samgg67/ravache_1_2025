import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class ArvoreIterativa {
    NoIterativo raiz;

    public int contarNos() {
        if (raiz == null) return 0;

        int contador = 0;
        Stack<NoIterativo> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            NoIterativo atual = pilha.pop();
            contador++;

            if (atual.direita != null) pilha.push(atual.direita);
            if (atual.esquerda != null) pilha.push(atual.esquerda);
        }
        return contador;
    }

    public void buscarPreOrdem() {
        if (raiz == null) return;

        Stack<NoIterativo> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            NoIterativo atual = pilha.pop();
            System.out.print(atual.valor + " ");

            if (atual.direita != null) pilha.push(atual.direita);
            if (atual.esquerda != null) pilha.push(atual.esquerda);
        }
    }

    public void buscarEmOrdem() {
        Stack<NoIterativo> pilha = new Stack<>();
        NoIterativo atual = raiz;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.esquerda;
            }

            atual = pilha.pop();
            System.out.print(atual.valor + " ");
            atual = atual.direita;
        }
    }

    public void buscarPosOrdem() {
        if (raiz == null) return;

        Stack<NoIterativo> pilha1 = new Stack<>();
        Stack<NoIterativo> pilha2 = new Stack<>();

        pilha1.push(raiz);

        while (!pilha1.isEmpty()) {
            NoIterativo atual = pilha1.pop();
            pilha2.push(atual);

            if (atual.esquerda != null) pilha1.push(atual.esquerda);
            if (atual.direita != null) pilha1.push(atual.direita);
        }

        while (!pilha2.isEmpty()) {
            System.out.print(pilha2.pop().valor + " ");
        }
    }

    public void buscarEmNivel() {
        if (raiz == null) return;

        Queue<NoIterativo> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            NoIterativo atual = fila.poll();
            System.out.print(atual.valor + " ");

            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
    }
}
