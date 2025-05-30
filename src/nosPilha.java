import java.util.Stack;

public class nosPilha {

    public static int nosPilha(No raiz) {
        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);
        int contador = 0;

        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            contador++;

            if (atual.direito != null) pilha.push(atual.direito);
            if (atual.esquerdo != null) pilha.push(atual.esquerdo);
        }

        return contador;
    }
}
