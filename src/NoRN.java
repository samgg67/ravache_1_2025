public class NoRN {
    int chave;
    Object value;
    Cor color;
    NoRN esquerdo;
    NoRN direito;
    NoRN parent;

    public NoRN(int chave, Object value, Cor color, NoRN parent) {
        this.chave = chave;
        this.value = value;
        this.color = color;
        this.parent = parent;
        this.esquerdo = null;
        this.direito = null;
    }

    public boolean isRed() {
        return this.color == Cor.Vermelho;
    }

    public boolean isBlack() {
        return this.color == Cor.Preto;
    }

    @Override
    public String toString() {
        return "[" + chave + ":" + color + "]";
    }
}
