package services;

import model.Contacto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AgendaService {

    private final Map<String, Contacto> contactos;
    private final int tamanoMaximo;

    public AgendaService(int tamanoMaximo) {
        this.contactos = new HashMap<>();
        this.tamanoMaximo = tamanoMaximo;
    }

    public AgendaService() {
        this(10);
    }

    public boolean añadirContacto(Contacto c) {
        // CAMBIO: Usamos getFullName() como clave única
        if (agendaLlena() || contactos.containsKey(c.getFullName())) {
            return false;
        }
        contactos.put(c.getFullName(), c);
        return true;
    }

    // Buscar contacto
    public Contacto buscarContacto(String fullName) {
        // CAMBIO: El parámetro ahora es el nombre completo
        return contactos.get(fullName);
    }
    // Eliminar contacto
    public boolean eliminarContacto(String fullName) {
        // CAMBIO: El parámetro ahora es el nombre completo
        return contactos.remove(fullName) != null;
    }
    //Lista de contactos
    public Collection<Contacto> listarContactos() {
        return contactos.values();
    }

    //Verifica si la agenda está llena
    public boolean agendaLlena() {
        return contactos.size() >= tamanoMaximo;
    }
    // Devuelve el número de espacios libres
    public int getEspaciosLibres() {
        return tamanoMaximo - contactos.size();
    }

    // Verifica si un contacto existe
    public boolean existeContacto(String fullName) {
        return contactos.containsKey(fullName);
    }

    // Modificar teléfono de un contacto
    public boolean modificarTelefono(String fullName, String nuevoTelefono) {
        Contacto c = contactos.get(fullName);
        if (c != null) {
            c.setTelefono(nuevoTelefono);
            return true;
        }
        return false;
    }

}