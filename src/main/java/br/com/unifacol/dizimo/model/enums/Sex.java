package br.com.unifacol.dizimo.model.enums;

public enum Sex {
    M("men's"), F("womanly");

    private final String descricao;

    private Sex(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Sex fromChar(char c) {
        if (c == 'm' || c == 'M') {
            return M;
        } else if (c == 'f' || c == 'F') {
            return F;
        } else {
            throw new IllegalArgumentException("Sexo inválido: " + c);
        }
    }
}
