public class arvoreRN {
    private NoRN raiz;

    public NoRN buscar(int chave) {
        return buscarRec(raiz, chave);
    }

    private NoRN buscarRec(NoRN no, int chave) {
        if (no == null || no.chave == chave)
            return no;
        if (chave < no.chave)
            return buscarRec(no.esquerdo, chave);
        else
            return buscarRec(no.direito, chave);
    }

    public void inserir(int chave, Object value) {
        NoRN novo = new NoRN(chave, value, Cor.Vermelho, null);
        raiz = inserir(raiz, novo);
        corrigirInsercao(novo);
    }

    private NoRN inserir(NoRN raiz, NoRN novo) {
        if (raiz == null)
            return novo;

        if (novo.chave < raiz.chave) {
            raiz.esquerdo = inserir(raiz.esquerdo, novo);
            raiz.esquerdo.parent = raiz;
        } else if (novo.chave > raiz.chave) {
            raiz.direito = inserir(raiz.direito, novo);
            raiz.direito.parent = raiz;
        }

        return raiz;
    }

    private void corrigirInsercao(NoRN no) {
        while (no != raiz && no.parent.isRed()) {
            NoRN pai = no.parent;
            NoRN avo = pai.parent;

            if (pai == avo.esquerdo) {
                NoRN tio = avo.direito;

                if (tio != null && tio.isRed()) {
                    pai.color = Cor.Preto;
                    tio.color = Cor.Preto;
                    avo.color = Cor.Vermelho;
                    no = avo;
                } else {
                    if (no == pai.direito) {
                        no = pai;
                        rotacaoEsquerda(no);
                    }
                    pai.color = Cor.Preto;
                    avo.color = Cor.Vermelho;
                    rotacaoDireita(avo);
                }
            } else {
                NoRN tio = avo.esquerdo;

                if (tio != null && tio.isRed()) {
                    pai.color = Cor.Preto;
                    tio.color = Cor.Preto;
                    avo.color = Cor.Vermelho;
                    no = avo;
                } else {
                    if (no == pai.esquerdo) {
                        no = pai;
                        rotacaoDireita(no);
                    }
                    pai.color = Cor.Preto;
                    avo.color = Cor.Vermelho;
                    rotacaoEsquerda(avo);
                }
            }
        }
        raiz.color = Cor.Preto;
    }

    private void rotacaoEsquerda(NoRN no) {
        NoRN y = no.direito;
        no.direito = y.esquerdo;
        if (y.esquerdo != null) y.esquerdo.parent = no;
        y.parent = no.parent;

        if (no.parent == null) {
            raiz = y;
        } else if (no == no.parent.esquerdo) {
            no.parent.esquerdo = y;
        } else {
            no.parent.direito = y;
        }

        y.esquerdo = no;
        no.parent = y;
    }

    private void rotacaoDireita(NoRN no) {
        NoRN y = no.esquerdo;
        no.esquerdo = y.direito;
        if (y.direito != null) y.direito.parent = no;
        y.parent = no.parent;

        if (no.parent == null) {
            raiz = y;
        } else if (no == no.parent.direito) {
            no.parent.direito = y;
        } else {
            no.parent.esquerdo = y;
        }

        y.direito = no;
        no.parent = y;
    }

    public void remover(int chave) {
        NoRN no = buscar(chave);
        if (no != null)
            removerNo(no);
    }

    private void removerNo(NoRN z) {
        NoRN y = z;
        Cor corOriginal = y.color;
        NoRN x;

        if (z.esquerdo == null) {
            x = z.direito;
            transplantar(z, z.direito);
        } else if (z.direito == null) {
            x = z.esquerdo;
            transplantar(z, z.esquerdo);
        } else {
            y = minimo(z.direito);
            corOriginal = y.color;
            x = y.direito;

            if (y.parent == z) {
                if (x != null) x.parent = y;
            } else {
                transplantar(y, y.direito);
                y.direito = z.direito;
                y.direito.parent = y;
            }

            transplantar(z, y);
            y.esquerdo = z.esquerdo;
            y.esquerdo.parent = y;
            y.color = z.color;
        }

        if (corOriginal == Cor.Preto)
            corrigirRemocao(x);
    }

    private void corrigirRemocao(NoRN x) {
        while (x != raiz && (x == null || x.isBlack())) {
            if (x == x.parent.esquerdo) {
                NoRN w = x.parent.direito;
                if (w != null && w.isRed()) {
                    w.color = Cor.Preto;
                    x.parent.color = Cor.Vermelho;
                    rotacaoEsquerda(x.parent);
                    w = x.parent.direito;
                }

                if ((w.esquerdo == null || w.esquerdo.isBlack()) &&
                        (w.direito == null || w.direito.isBlack())) {
                    w.color = Cor.Vermelho;
                    x = x.parent;
                } else {
                    if (w.direito == null || w.direito.isBlack()) {
                        if (w.esquerdo != null) w.esquerdo.color = Cor.Preto;
                        w.color = Cor.Vermelho;
                        rotacaoDireita(w);
                        w = x.parent.direito;
                    }

                    w.color = x.parent.color;
                    x.parent.color = Cor.Preto;
                    if (w.direito != null) w.direito.color = Cor.Preto;
                    rotacaoEsquerda(x.parent);
                    x = raiz;
                }
            } else {
                NoRN w = x.parent.esquerdo;
                if (w != null && w.isRed()) {
                    w.color = Cor.Preto;
                    x.parent.color = Cor.Vermelho;
                    rotacaoDireita(x.parent);
                    w = x.parent.esquerdo;
                }

                if ((w.direito == null || w.direito.isBlack()) &&
                        (w.esquerdo == null || w.esquerdo.isBlack())) {
                    w.color = Cor.Vermelho;
                    x = x.parent;
                } else {
                    if (w.esquerdo == null || w.esquerdo.isBlack()) {
                        if (w.direito != null) w.direito.color = Cor.Preto;
                        w.color = Cor.Vermelho;
                        rotacaoEsquerda(w);
                        w = x.parent.esquerdo;
                    }

                    w.color = x.parent.color;
                    x.parent.color = Cor.Preto;
                    if (w.esquerdo != null) w.esquerdo.color = Cor.Preto;
                    rotacaoDireita(x.parent);
                    x = raiz;
                }
            }
        }
        if (x != null) x.color = Cor.Preto;
    }

    private void transplantar(NoRN u, NoRN v) {
        if (u.parent == null) {
            raiz = v;
        } else if (u == u.parent.esquerdo) {
            u.parent.esquerdo = v;
        } else {
            u.parent.direito = v;
        }

        if (v != null) {
            v.parent = u.parent;
        }
    }

    private NoRN minimo(NoRN no) {
        while (no.esquerdo != null) {
            no = no.esquerdo;
        }
        return no;
    }

    public void percursoEmOrdem() {
        percursoEmOrdem(raiz);
        System.out.println();
    }

    private void percursoEmOrdem(NoRN no) {
        if (no != null) {
            percursoEmOrdem(no.esquerdo);
            System.out.print(no + " ");
            percursoEmOrdem(no.direito);
        }
    }
}
