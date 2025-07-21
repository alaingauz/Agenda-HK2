package controller;

import model.Contacto;
import services.AgendaService;

import java.util.Collection;
import java.util.Scanner;

public class GestorAgenda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AgendaService agendaService = new AgendaService(5); // Creamos el servicio con tamaño 5 para probar
        int opcion = 0;

        while (opcion != 9) {
            System.out.println("\n--- MENÚ DE AGENDA TELEFÓNICA ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Ver si la agenda está llena");
            System.out.println("6. Ver espacios libres");
            System.out.println("7. Comprobar si un contacto existe");
            System.out.println("8. Modificar un contacto");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Introduce el nombre: ");
                        String firstnameAdd = sc.nextLine();
                        System.out.print("Introduce el apellido: ");
                        String lastnameAdd = sc.nextLine();
                        System.out.print("Introduce el teléfono: ");
                        String telefonoAdd = sc.nextLine();

                        Contacto nuevoContacto = new Contacto(firstnameAdd, lastnameAdd, telefonoAdd);
                        if (agendaService.añadirContacto(nuevoContacto)) {
                            System.out.println("✅ Contacto añadido correctamente.");
                        } else {
                            System.out.println("❌ No se pudo añadir. La agenda puede estar llena o el contacto ya existe.");
                        }
                        break;

                    case 2:
                        Collection<Contacto> lista = agendaService.listarContactos();
                        if (lista.isEmpty()) {
                            System.out.println("La agenda está vacía.");
                        } else {
                            System.out.println("--- Lista de Contactos ---");
                            for (Contacto c : lista) {
                                System.out.println("➡️ " + c.toString());
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Introduce el nombre a buscar: ");
                        String firstnameSearch = sc.nextLine();
                        System.out.print("Introduce el apellido a buscar: ");
                        String lastnameSearch = sc.nextLine();
                        String fullNameSearch = firstnameSearch + " " + lastnameSearch;

                        Contacto encontrado = agendaService.buscarContacto(fullNameSearch);
                        if (encontrado != null) {
                            System.out.println("📞 El teléfono de " + encontrado.getFullName() + " es: " + encontrado.getTelefono());
                        } else {
                            System.out.println("🤷 El contacto no fue encontrado.");
                        }
                        break;

                    case 4:
                        System.out.print("Introduce el nombre del contacto a eliminar: ");
                        String firstnameDelete = sc.nextLine();
                        System.out.print("Introduce el apellido del contacto a eliminar: ");
                        String lastnameDelete = sc.nextLine();
                        String fullNameDelete = firstnameDelete + " " + lastnameDelete;

                        if (agendaService.eliminarContacto(fullNameDelete)) {
                            System.out.println("✅ El contacto fue eliminado.");
                        } else {
                            System.out.println("❌ El contacto no se encontró y no pudo ser eliminado.");
                        }
                        break;

                    case 5:
                        if (agendaService.agendaLlena()) {
                            System.out.println("La agenda está llena.");
                        } else {
                            System.out.println("La agenda aún tiene espacio.");
                        }
                        break;

                    case 6:
                        System.out.println("ℹ️ Quedan " + agendaService.getEspaciosLibres() + " espacios libres.");
                        break;

                    case 7:
                        System.out.print("Introduce el nombre a comprobar: ");
                        String firstnameExist = sc.nextLine();
                        System.out.print("Introduce el apellido a comprobar: ");
                        String lastnameExist = sc.nextLine();
                        String fullNameExist = firstnameExist + " " + lastnameExist;

                        if (agendaService.buscarContacto(fullNameExist) != null) {
                            System.out.println("✔️ Sí, el contacto '" + fullNameExist + "' existe en la agenda.");
                        } else {
                            System.out.println("❌ No, el contacto '" + fullNameExist + "' no existe.");
                        }
                        break;
                    case 8:
                        System.out.print("Introduce el nombre del contacto a modificar: ");
                        String nombreModificar = sc.nextLine();
                        System.out.print("Introduce el apellido del contacto a modificar: ");
                        String apellidoModificar = sc.nextLine();
                        String fullNameMod = nombreModificar + " " + apellidoModificar;
                        System.out.print("Introduce el nuevo teléfono: ");
                        String nuevoTelefono = sc.nextLine();
                        if (agendaService.modificarTelefono(fullNameMod, nuevoTelefono)) {
                            System.out.println("Contacto actualizado correctamente.");
                        } else {
                            System.out.println("No se encontró el contacto para modificar.");
                        }
                        break;


                    case 9:
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            }
        }
        sc.close();
    }
}