package br.com.unifacol.dizimo.model.enums;

public enum Genero {
    M("men's"), F("womanly");

    private final String descricao;

    private Genero(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Genero fromChar(char c) {
        if (c == 'm' || c == 'M') {
            return M;
        } else if (c == 'f' || c == 'F') {
            return F;
        } else {
            throw new IllegalArgumentException("Sexo inválido: " + c);
        }
    }
}
