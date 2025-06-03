public class ArvoreAVL {
    private No raiz;

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }
        if (valor < no.valor) {
            no.esquerdo = inserirRecursivo(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = inserirRecursivo(no.direito, valor);
        } else {
            return no;
        }

        atualizarAltura(no);
        return balancear(no);
    }

    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private No removerRecursivo(No no, int valor) {
        if (no == null) return null;

        if (valor < no.valor) {
            no.esquerdo = removerRecursivo(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = removerRecursivo(no.direito, valor);
        } else {
            if (no.esquerdo == null) return no.direito;
            if (no.direito == null) return no.esquerdo;

            No menor = encontrarMenor(no.direito);
            no.valor = menor.valor;
            no.direito = removerRecursivo(no.direito, menor.valor);
        }

        atualizarAltura(no);
        return balancear(no);
    }

    private No encontrarMenor(No no) {
        while (no.esquerdo != null) {
            no = no.esquerdo;
        }
        return no;
    }

    private int altura(No no) {
        return (no == null) ? 0 : no.altura;
    }

    private void atualizarAltura(No no) {
        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
    }

    private int Balanceamento(No no) {
        return (no == null) ? 0 : altura(no.esquerdo) - altura(no.direito);
    }

    private No balancear(No no) {
        int fb = Balanceamento(no);

        if (fb > 1) {
            if (Balanceamento(no.esquerdo) < 0) {
                no.esquerdo = rotacaoEsquerda(no.esquerdo);
            }
            return rotacaoDireita(no);
        }

        if (fb < -1) {
            if (Balanceamento(no.direito) > 0) {
                no.direito = rotacaoDireita(no.direito);
            }
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerdo;
        No T2 = x.direito;

        x.direito = y;
        y.esquerdo = T2;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direito;
        No T2 = y.esquerdo;

        y.esquerdo = x;
        x.direito = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    public void percorrerEmOrdem() {
        percorrerEmOrdem(raiz);
        System.out.println();
    }

    private void percorrerEmOrdem(No no) {
        if (no != null) {
            percorrerEmOrdem(no.esquerdo);
            System.out.print(no.valor + " ");
            percorrerEmOrdem(no.direito);
        }
    }
}
