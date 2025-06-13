public class NoRN {
    int key;
    Object value;
    Cor color;
    NoRN left;
    NoRN right;
    NoRN parent;

    public NoRN(int key, Object value, Cor color, NoRN parent) {
        this.key = key;
        this.value = value;
        this.color = color;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    public boolean isRed() {
        return this.color == Cor.Vermelho;
    }

    public boolean isBlack() {
        return this.color == Cor.Preto;
    }

    @Override
    public String toString() {
        return "[" + key + ":" + color + "]";
    }
}
