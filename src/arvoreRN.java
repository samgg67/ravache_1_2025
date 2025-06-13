public class arvoreRN {
    private NoRB raiz;

    public NoRB buscar(int key) {
        return buscarRec(raiz, key);
    }

    private NoRB buscarRec(NoRB no, int key) {
        if (no == null || no.key == key)
            return no;
        if (key < no.key)
            return buscarRec(no.left, key);
        else
            return buscarRec(no.right, key);
    }

    public void inserir(int key, Object value) {
        NoRB novo = new NoRB(key, value, Cor.Vermelho, null);
        raiz = inserir(raiz, novo);
        corrigirInsercao(novo);
    }

    private NoRB inserir(NoRB raiz, NoRB novo) {
        if (raiz == null)
            return novo;

        if (novo.key < raiz.key) {
            raiz.left = inserir(raiz.left, novo);
            raiz.left.parent = raiz;
        } else if (novo.key > raiz.key) {
            raiz.right = inserir(raiz.right, novo);
            raiz.right.parent = raiz;
        }

        return raiz;
    }

    private void corrigirInsercao(NoRB no) {
        while (no != raiz && no.parent.isRed()) {
            NoRB pai = no.parent;
            NoRB avo = pai.parent;

            if (pai == avo.left) {
                NoRB tio = avo.right;

                if (tio != null && tio.isRed()) {
                    pai.color = Cor.Preto;
                    tio.color = Cor.Preto;
                    avo.color = Cor.Vermelho;
                    no = avo;
                } else {
                    if (no == pai.right) {
                        no = pai;
                        rotacaoEsquerda(no);
                    }
                    pai.color = Cor.Preto;
                    avo.color = Cor.Vermelho;
                    rotacaoDireita(avo);
                }
            } else {
                NoRB tio = avo.left;

                if (tio != null && tio.isRed()) {
                    pai.color = Cor.Preto;
                    tio.color = Cor.Preto;
                    avo.color = Cor.Vermelho;
                    no = avo;
                } else {
                    if (no == pai.left) {
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

    private void rotacaoEsquerda(NoRB no) {
        NoRB y = no.right;
        no.right = y.left;
        if (y.left != null) y.left.parent = no;
        y.parent = no.parent;

        if (no.parent == null) {
            raiz = y;
        } else if (no == no.parent.left) {
            no.parent.left = y;
        } else {
            no.parent.right = y;
        }

        y.left = no;
        no.parent = y;
    }

    private void rotacaoDireita(NoRB no) {
        NoRB y = no.left;
        no.left = y.right;
        if (y.right != null) y.right.parent = no;
        y.parent = no.parent;

        if (no.parent == null) {
            raiz = y;
        } else if (no == no.parent.right) {
            no.parent.right = y;
        } else {
            no.parent.left = y;
        }

        y.right = no;
        no.parent = y;
    }

    // Remoção pública
    public void remover(int key) {
        NoRB no = buscar(key);
        if (no != null)
            removerNo(no);
    }

    private void removerNo(NoRB z) {
        NoRB y = z;
        Cor corOriginal = y.color;
        NoRB x;

        if (z.left == null) {
            x = z.right;
            transplantar(z, z.right);
        } else if (z.right == null) {
            x = z.left;
            transplantar(z, z.left);
        } else {
            y = minimo(z.right);
            corOriginal = y.color;
            x = y.right;

            if (y.parent == z) {
                if (x != null) x.parent = y;
            } else {
                transplantar(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplantar(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (corOriginal == Cor.Preto)
            corrigirRemocao(x);
    }

    private void corrigirRemocao(NoRB x) {
        while (x != raiz && (x == null || x.isBlack())) {
            if (x == x.parent.left) {
                NoRB w = x.parent.right;
                if (w != null && w.isRed()) {
                    w.color = Cor.Preto;
                    x.parent.color = Cor.Vermelho;
                    rotacaoEsquerda(x.parent);
                    w = x.parent.right;
                }

                if ((w.left == null || w.left.isBlack()) &&
                        (w.right == null || w.right.isBlack())) {
                    w.color = Cor.Vermelho;
                    x = x.parent;
                } else {
                    if (w.right == null || w.right.isBlack()) {
                        if (w.left != null) w.left.color = Cor.Preto;
                        w.color = Cor.Vermelho;
                        rotacaoDireita(w);
                        w = x.parent.right;
                    }

                    w.color = x.parent.color;
                    x.parent.color = Cor.Preto;
                    if (w.right != null) w.right.color = Cor.Preto;
                    rotacaoEsquerda(x.parent);
                    x = raiz;
                }
            } else {
                NoRB w = x.parent.left;
                if (w != null && w.isRed()) {
                    w.color = Cor.Preto;
                    x.parent.color = Cor.Vermelho;
                    rotacaoDireita(x.parent);
                    w = x.parent.left;
                }

                if ((w.right == null || w.right.isBlack()) &&
                        (w.left == null || w.left.isBlack())) {
                    w.color = Cor.Vermelho;
                    x = x.parent;
                } else {
                    if (w.left == null || w.left.isBlack()) {
                        if (w.right != null) w.right.color = Cor.Preto;
                        w.color = Cor.Vermelho;
                        rotacaoEsquerda(w);
                        w = x.parent.left;
                    }

                    w.color = x.parent.color;
                    x.parent.color = Cor.Preto;
                    if (w.left != null) w.left.color = Cor.Preto;
                    rotacaoDireita(x.parent);
                    x = raiz;
                }
            }
        }
        if (x != null) x.color = Cor.Preto;
    }

    private void transplantar(NoRB u, NoRB v) {
        if (u.parent == null) {
            raiz = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }

        if (v != null) {
            v.parent = u.parent;
        }
    }

    private NoRB minimo(NoRB no) {
        while (no.left != null) {
            no = no.left;
        }
        return no;
    }

    public void percursoEmOrdem() {
        percursoEmOrdem(raiz);
        System.out.println();
    }

    private void percursoEmOrdem(NoRB no) {
        if (no != null) {
            percursoEmOrdem(no.left);
            System.out.print(no + " ");
            percursoEmOrdem(no.right);
        }
    }
}
