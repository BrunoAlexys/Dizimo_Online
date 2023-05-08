package br.com.unifacol.dizimo.model.enums;

public enum Estado {
    AC("Acre"), AL("Alagoas"), AP("Amapá"), AM("Amazonas"), BA("Bahia"), CE("Ceará"), DF("Distrito Federal"),
    ES("Espírito Santo"), GO("Goiás"), MA("Maranhão"), MT("Mato Grosso"), MS("Mato Grosso do Sul"), MG("Minas Gerais"),
    PA("Pará"), PB("Paraíba"), PR("Paraná"), PE("Pernambuco"), PI("Piauí"), RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"), RS("Rio Grande do Sul"), RO("Rondônia"), RR("Roraima"), SC("Santa Catarina"),
    SP("São Paulo"), SE("Sergipe"), TO("Tocantins");

    private final String fullName;

    private Estado(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public static Estado acronym(String acronym) {
        for (Estado estado : values()) {
            if (estado.name().equalsIgnoreCase(acronym)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Sigla do estado inválida: " + acronym);
    }
}
