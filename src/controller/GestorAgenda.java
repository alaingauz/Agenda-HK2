package controller;

import model.Contacto;
import services.AgendaService;

import java.util.Collection;
import java.util.Scanner;

public class GestorAgenda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AgendaService agendaService = new AgendaService(5);
        int opcion = 0;

        while (opcion != 8) {
            // ... (El menú se imprime igual)
            System.out.println("\n--- MENÚ DE AGENDA TELEFÓNICA ---");
            System.out.println("1. Añadir contacto");
            // 3. Mostrar opciones al usuario
            System.out.println("\n--- MENÚ DE AGENDA TELEFÓNICA ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Ver si la agenda está llena");
            System.out.println("6. Ver espacios libres");
            System.out.println("7. Comprobar si un contacto existe");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        // CAMBIO: Pedimos nombre y apellido por separado
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

                    case 3: // Buscar Contacto
                        // CAMBIO: Pedimos ambos para buscar
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

                    case 4: // Eliminar Contacto
                        // CAMBIO: Pedimos ambos para eliminar
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

                    // ... Los otros casos (listar, espacios libres, etc.) no necesitan grandes cambios.
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            }
        }
        sc.close();
    }
}