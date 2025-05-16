public class Arvore {
    No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }

        if (valor < atual.valor) {
            atual.esquerdo = inserirRecursivo(atual.esquerdo, valor);
        } else if (valor > atual.valor) {
            atual.direito = inserirRecursivo(atual.direito, valor);
        }

        return atual;
    }

    public void emOrdem() {
        emOrdemRecursivo(raiz);
    }

    private void emOrdemRecursivo(No no) {
        if (no != null) {
            emOrdemRecursivo(no.esquerdo);
            System.out.print(no.valor + " ");
            emOrdemRecursivo(no.direito);
        }
    }

    public void preOrdem() {
        preOrdemRecursivo(raiz);
    }

    private void preOrdemRecursivo(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdemRecursivo(no.esquerdo);
            preOrdemRecursivo(no.direito);
        }
    }

    public void posOrdem() {
        posOrdemRecursivo(raiz);
    }

    private void posOrdemRecursivo(No no) {
        if (no != null) {
            posOrdemRecursivo(no.esquerdo);
            posOrdemRecursivo(no.direito);
            System.out.print(no.valor + " ");
        }
    }
}
