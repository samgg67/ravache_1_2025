import java.util.Queue;
import java.util.LinkedList;

public class nosFila {


    public int nosFila(No raiz) {
        if (raiz == null) return 0;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        int contador = 0;
        while (!fila.isEmpty()) {
            No atual = fila.poll();
            contador++;

            if (atual.esquerdo != null) fila.add(atual.esquerdo);
            if (atual.direito != null) fila.add(atual.direito);
        }
        return contador;
    }
}