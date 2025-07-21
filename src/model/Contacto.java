package model;

import java.util.Objects;

public class Contacto {
    // CAMBIO: Se reemplaza 'nombre' por 'firstname' y 'lastname'
    private String firstname;
    private String lastname;
    private String telefono;

    public Contacto(String firstname, String lastname, String telefono) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telefono = telefono;
    }

    // --- Getters y Setters actualizados ---
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // NUEVO: Método de ayuda para obtener el nombre completo
    public String getFullName() {
        return firstname + " " + lastname;
    }

    // CAMBIO: toString actualizado
    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + firstname + '\'' +
                ", apellido='" + lastname + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    // CAMBIO: La igualdad ahora depende del nombre y apellido
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return Objects.equals(firstname, contacto.firstname) &&
                Objects.equals(lastname, contacto.lastname);
    }

    // CAMBIO: El hashCode ahora depende del nombre y apellido
    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}