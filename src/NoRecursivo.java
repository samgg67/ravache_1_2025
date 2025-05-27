class NoRecursivo {
    String valor;
    NoRecursivo esquerda, direita;

    NoRecursivo(String valor) {
        this.valor = valor;
        esquerda = direita = null;
    }
}