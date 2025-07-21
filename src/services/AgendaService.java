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

    public Contacto buscarContacto(String fullName) {
        // CAMBIO: El parámetro ahora es el nombre completo
        return contactos.get(fullName);
    }

    public boolean eliminarContacto(String fullName) {
        // CAMBIO: El parámetro ahora es el nombre completo
        return contactos.remove(fullName) != null;
    }

    public Collection<Contacto> listarContactos() {
        return contactos.values();
    }

    public boolean agendaLlena() {
        return contactos.size() >= tamanoMaximo;
    }

    public int getEspaciosLibres() {
        return tamanoMaximo - contactos.size();
    }
}