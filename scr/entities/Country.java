package entities;

import java.util.Objects;

public class Country {

    // Atributos
    private String nombrePais;

    // Constructor
    public Country(String name) {
        this.nombrePais = name;
    }

    // Setter & Getter
    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country pais = (Country) o;
        return Objects.equals(nombrePais, pais.nombrePais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombrePais);
    }
}
