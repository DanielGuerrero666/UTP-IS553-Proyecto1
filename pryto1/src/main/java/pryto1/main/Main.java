package pryto1.main;

import java.util.Scanner;

import pryto1.classes.Contact;
import pryto1.classes.Phonebook;

public class Main {
    static Phonebook misContactos = new Phonebook();
    public static void main(String[] args) {
        misContactos.loadData("default");
        System.out.println("========================");
        System.out.println("Verificando contactos...");
        misContactos.verifyContactNumbers();
        mainMenu();
    }

    public static void mainMenu(){
        System.out.println("\n============== Menu Principal ==============");
        System.out.println("[1] - Agregar nuevo contacto");
        System.out.println("[2] - Buscar contacto");
        System.out.println("[3] - Ver contactos");
        System.out.println("[4] - Editar contacto");
        System.out.println("[5] - Eliminar contacto");
        System.out.println("[6] - Guardar");
        System.out.println("[7] - Cargar");
        System.out.println("[8] - Salir");
        System.out.println("============================================");

        Scanner scan = new Scanner(System.in);
        System.out.print("[#] : ");
        int option = scan.nextInt();
        switch(option){
            case 1:            
                System.out.print("\033[H\033[2J");
                System.out.flush();
                addNewContact();
                break;
            case 2:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                searchInPhonebook();
                break;
            case 3:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                showPhonebook();
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                try {
                    System.out.println("Hasta pronto...");
                    Thread.sleep(1*1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush(); 
                System.exit(0);
                break;
            default: 
                System.out.println("Opcion invalida");
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
        }
        scan.close();
    }

    public static void addNewContact(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese los datos del nuevo contacto:");
        System.out.print("Nombre: ");
        String name = scan.nextLine();
        System.out.print("Número: ");
        String number = scan.nextLine();
        System.out.println("¿Desea ingresar mas números? S/N");
        String option = scan.nextLine();
        if(option.equalsIgnoreCase("S") == true){
            do {
                number += ",";
                System.out.print("Número: ");
                number += scan.nextLine();
                System.out.println("¿Desea ingresar mas números? S/N");
                option = scan.nextLine();
            } while (option.equalsIgnoreCase("S")==true);
        }
        System.out.print("E-mail (opcional): ");
        String mail = scan.nextLine();
        System.out.print("Dirección (opcional): ");
        String direction = scan.nextLine();
        System.out.print("Sobrenombre (opcional): ");
        String nick = scan.nextLine();
        System.out.println("¿Esta seguro de querer guardar estos datos? S/N");
        option = scan.nextLine();
        if(option.equalsIgnoreCase("S")==true){
            misContactos.addContact(new Contact(name, number, mail, direction, nick));
            System.out.println("Contacto Guardado.");
            misContactos.saveData("default");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mainMenu();
        }else{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            addNewContact();
        }
        scan.close();
    }

    public static void searchInPhonebook(){
        Scanner scan = new Scanner(System.in);
        System.out.println("============== Filtros de Busqueda ==============");
        System.out.println("[1] - Por Nombre");
        System.out.println("[2] - Por Número");
        System.out.println("[3] - Por E-mail");
        System.out.println("[4] - Por Dirección");
        System.out.println("[5] - Por Sobrenombre");

        System.out.print("[#] : ");
        int option = scan.nextInt();
        scan.nextLine();
        switch(option){
            case 1: 
                System.out.print("Ingrese el nombre a buscar: ");
                String searchName = scan.nextLine();
                misContactos.searchContactByName(searchName);
                scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case 2: 
                System.out.print("Ingrese el número a buscar: ");
                String searchNumber = scan.nextLine();
                misContactos.searchContactByNumber(searchNumber);
                scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case 3: System.out.print("Ingrese el correo a buscar: ");
                String searchMail = scan.nextLine();
                misContactos.searchContactByMail(searchMail);
                scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case 4: System.out.print("Ingrese la dirección a buscar: ");
                String searchDirection = scan.nextLine();
                misContactos.searchContactByDirection(searchDirection);
                scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();                
                break;
            case 5: System.out.print("Ingrese el sobrenombre a buscar: ");
                String searchNick = scan.nextLine();
                misContactos.searchContactByNick(searchNick);
                scan.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();                
                break;
            default: System.out.println("Opcion invalida");
                System.out.print("\033[H\033[2J");
                System.out.flush();
                searchInPhonebook();
        }
        scan.close();
    }

    public static void showPhonebook(){
        System.out.println("============== Contactos en la Agenda ==============");
        misContactos.showContacts();
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        mainMenu();
        scan.close();
    }
}