package services;

import model.Contacto;

import java.util.*;

public class AgendaService {

    private final Map<String, Contacto> contactos;
    private final int tamanoMaximo;

    // Constructor con tamaño personalizado
    public AgendaService(int tamanoMaximo) {
        this.contactos = new HashMap<>();
        this.tamanoMaximo = tamanoMaximo;
    }

    // Constructor por defecto (10 contactos)
    public AgendaService() {
        this(10);
    }

    // Añadir contacto si hay espacio y no es duplicado
    public boolean añadirContacto(Contacto c) {
        if (agendaLlena() || contactos.containsKey(c.getFullName())) {
            return false;
        }
        contactos.put(c.getFullName(), c);
        return true;
    }

    // Buscar contacto por nombre completo (clave)
    public Contacto buscarContacto(String fullName) {
        return contactos.get(fullName);
    }

    // Eliminar contacto por nombre completo
    public boolean eliminarContacto(String fullName) {
        return contactos.remove(fullName) != null;
    }

    // Listar contactos ordenados alfabéticamente usando Comparator
    public List<Contacto> listarContactos() {
        if (contactos.isEmpty()) {
            return new ArrayList<>(); // Lista vacía en lugar de null
        }

        List<Contacto> lista = new ArrayList<>(contactos.values());

        // Usar Comparator.comparing para más claridad
        lista.sort(Comparator.comparing((Contacto c) -> c.getFirstname().toLowerCase())
                .thenComparing(c -> c.getLastname().toLowerCase()));

        return lista;
    }

    // Verificar si la agenda está llena
    public boolean agendaLlena() {
        return contactos.size() >= tamanoMaximo;
    }

    // Espacios libres disponibles
    public int getEspaciosLibres() {
        return tamanoMaximo - contactos.size();
    }

    // Verificar si un contacto existe
    public boolean existeContacto(String fullName) {
        return contactos.containsKey(fullName);
    }

    // Modificar teléfono
    public boolean modificarTelefono(String fullName, String nuevoTelefono) {
        Contacto c = contactos.get(fullName);
        if (c != null) {
            c.setTelefono(nuevoTelefono);
            return true;
        }
        return false;
    }

    // Mostrar contactos ordenados por nombre y apellido
    public void mostrarContactosOrdenados() {
        List<Contacto> contactosOrdenados = listarContactos();

        if (contactosOrdenados.isEmpty()) {
            System.out.println(" La agenda está vacía.");
            return;
        }

        System.out.println(" Contactos ordenados:");
        for (Contacto c : contactosOrdenados) {
            System.out.println(c.getFirstname() + " " + c.getLastname() + " - " + c.getTelefono());
        }
    }

    // Buscar contacto por nombre y apellido
    public Contacto buscarPorNombreApellido(String firstname, String lastname) {
        // Validar parámetros
        if (firstname == null || lastname == null ||
                firstname.trim().isEmpty() || lastname.trim().isEmpty()) {
            System.out.println(" Error: El nombre y apellido no pueden estar vacíos.");
            return null;
        }

        // Buscar contacto
        for (Contacto c : contactos.values()) {
            if (c.getFirstname().equalsIgnoreCase(firstname.trim()) &&
                    c.getLastname().equalsIgnoreCase(lastname.trim())) {
                System.out.println(" Contacto encontrado: " +
                        c.getFirstname() + " " + c.getLastname() + " - " + c.getTelefono());
                return c;
            }
        }

        // Mensaje  si no se encuentra
        System.out.println(" No se encontró ningún contacto con el nombre: " +
                firstname.trim() + " " + lastname.trim());
        return null;
    }

    //  buscar contactos que contengan un texto
    public List<Contacto> buscarContactosPorTexto(String texto) {
        // Validar parámetro
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println(" Error: El texto de búsqueda no puede estar vacío.");
            return new ArrayList<>();
        }

        List<Contacto> resultados = new ArrayList<>();
        String textoBusqueda = texto.trim().toLowerCase();

        for (Contacto c : contactos.values()) {
            if (c.getFirstname().toLowerCase().contains(textoBusqueda) ||
                    c.getLastname().toLowerCase().contains(textoBusqueda)) {
                resultados.add(c);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron contactos que contengan: " + texto.trim());
        } else {
            System.out.println(" Se encontraron " + resultados.size() + " contacto(s):");
            // Ordenar los resultados
            resultados.sort(Comparator.comparing((Contacto c) -> c.getFirstname().toLowerCase())
                    .thenComparing(c -> c.getLastname().toLowerCase()));

            for (Contacto c : resultados) {
                System.out.println("  " + c.getFirstname() + " " + c.getLastname() + " - " + c.getTelefono());
            }
        }

        return resultados;
    }
}