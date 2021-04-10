package pryto1.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import pryto1.classes.Contact;
import pryto1.classes.OriginalException;
import pryto1.classes.Phonebook;

public class Main {
    static Phonebook misContactos = new Phonebook();
    static String fileName;
    public static void main(String[] args) {
        defineFileName("default");
        misContactos.loadData(fileName);
        System.out.println("========================");
        System.out.println("Verificando contactos...");
        misContactos.verifyContactNumbers();
        mainMenu();
    }

    public static void defineFileName(String setFileName){
        fileName = setFileName;
    }

    public static void mainMenu(){
        System.out.println("\n============== Menu Principal ==============");
        System.out.println("[1] - Agregar nuevo contacto");
        System.out.println("[2] - Buscar contacto");
        System.out.println("[3] - Ver contactos");
        System.out.println("[4] - Editar contacto");
        System.out.println("[5] - Eliminar contacto");
        System.out.println("[6] - Exportar");
        System.out.println("[7] - Importar");
        System.out.println("[8] - Salir");
        System.out.println("============================================");

        Scanner scan = new Scanner(System.in);
        System.out.print("[#] : ");
        int option = scan.nextInt();
        switch(option){
            case 1:            
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
                System.out.print("\033[H\033[2J");
                System.out.flush();
                editPhonebook();
                break;            
            case 5:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                deleteContactInPhonebook();
                break;        
            case 6:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                exportData();
                break;        
            case 7:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                importData();
                break;      
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
            misContactos.saveData(fileName);
            misContactos.loadData(fileName);
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
        Contact instanceOfContact;
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
                try {
                    instanceOfContact = misContactos.searchContactByName(searchName);
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    scan.nextLine();
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    scan.nextLine();
                    e.printStackTrace();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case 2: 
                System.out.print("Ingrese el número a buscar: ");
                String searchNumber = scan.nextLine();
                try {
                    instanceOfContact = misContactos.searchContactByNumber(searchNumber);
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    scan.nextLine();
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    scan.nextLine();
                    e.printStackTrace();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case 3: System.out.print("Ingrese el correo a buscar: ");
                String searchMail = scan.nextLine();
                try {
                    instanceOfContact = misContactos.searchContactByMail(searchMail);
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    scan.nextLine();
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    scan.nextLine();
                    e.printStackTrace();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case 4: System.out.print("Ingrese la dirección a buscar: ");
                String searchDirection = scan.nextLine();
                try {
                    instanceOfContact = misContactos.searchContactByDirection(searchDirection);
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    scan.nextLine();
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    scan.nextLine();
                    e.printStackTrace();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();                
                break;
            case 5: System.out.print("Ingrese el sobrenombre a buscar: ");
                String searchNick = scan.nextLine();
                try {
                    instanceOfContact = misContactos.searchContactByNick(searchNick);
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    scan.nextLine();
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    scan.nextLine();
                    e.printStackTrace();
                }
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
        Scanner scan = new Scanner(System.in);
        System.out.println("============== Contactos en la Agenda ==============");
        Contact[] arrayOfInfo = misContactos.returnInfo();
        int i=1;
        for(Contact instanceOfContact : arrayOfInfo){
            System.out.println("==============================================");
            System.out.println("Contacto #"+i);
            System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMail(),
            instanceOfContact.getDirection(),instanceOfContact.getNick()));
            i++;
        }
        scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        mainMenu();
        scan.close();
    }

    public static void editPhonebook(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la posicion del contacto a editar: ");
        int positionInList = scan.nextInt();
        scan.nextLine();
        misContactos.identifyEspecificContact(positionInList);
        System.out.println("¿Es este el contacto que desea editar? S/N");
        String confirmation = scan.nextLine();
        if(confirmation.equalsIgnoreCase("S")==true){
            System.out.println("Seleccione el campo del contacto que desea editar");
            System.out.println("[1] - Editar nombre");
            System.out.println("[2] - Agregar número");
            System.out.println("[3] - Editar número existente");
            System.out.println("[4] - Editar correo");
            System.out.println("[5] - Editar dirección");
            System.out.println("[6] - Editar sobrenombre");

            System.out.print("[#] : ");
            int option = scan.nextInt();
            scan.nextLine();

            switch(option){
                case 1:
                    System.out.println("Ingrese el nuevo nombre del contacto: ");
                    String name = scan.nextLine();
                    misContactos.editEspecificContactInfo(1, positionInList, name);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    misContactos.identifyEspecificContact(positionInList);
                    scan.nextLine();
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo numero de contacto :");
                    String newNumber = scan.nextLine();
                    misContactos.editEspecificContactInfo(2, positionInList, newNumber);
                    misContactos.verifyEditContactNumbers(fileName);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    misContactos.identifyEspecificContact(positionInList);
                    scan.nextLine();
                    break;
                case 3:
                    System.out.print("Ingrese el numero que desea editar: ");
                    String oldToChangeNumber = scan.nextLine();
                    System.out.print("Ingrese el nuevo numero: ");
                    String newToChangeNumber = scan.nextLine();
                    String codednumber = oldToChangeNumber+"|"+newToChangeNumber;
                    misContactos.editEspecificContactInfo(3, positionInList, codednumber);
                    misContactos.verifyEditContactNumbers(fileName);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    misContactos.identifyEspecificContact(positionInList);
                    scan.nextLine();
                    break;
                case 4:
                    System.out.println("Ingrese el nuevo correo del contacto: ");
                    String mail = scan.nextLine();
                    misContactos.editEspecificContactInfo(4, positionInList, mail);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    misContactos.identifyEspecificContact(positionInList);
                    scan.nextLine();
                    break;
                case 5:
                    System.out.println("Ingrese la nueva dirección del contacto: ");
                    String direction = scan.nextLine();
                    misContactos.editEspecificContactInfo(5, positionInList, direction);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    misContactos.identifyEspecificContact(positionInList);
                    scan.nextLine();
                    break;
                case 6:
                    System.out.println("Ingrese el nuevo sobrenombre del contacto: ");
                    String nick = scan.nextLine();
                    misContactos.editEspecificContactInfo(6, positionInList, nick);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    misContactos.identifyEspecificContact(positionInList);
                    scan.nextLine();
                    break;
                default:
                    System.out.println("Opcion Invalida");
                    break;
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mainMenu();
        }else{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            editPhonebook();
        }
        scan.close();
    }

    public static void deleteContactInPhonebook(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la posicion del contacto a eliminar: ");
        int positionInList = scan.nextInt();
        scan.nextLine();
        misContactos.identifyEspecificContact(positionInList);
        System.out.println("¿Seguro que desea eliminar este contacto? S/N");
        String confirmation = scan.nextLine();
        if(confirmation.equalsIgnoreCase("S")==true){
            misContactos.deleteContact(positionInList);
            System.out.println("Contacto Eliminado");
            misContactos.saveData(fileName);
            misContactos.loadData(fileName);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mainMenu();
        }else{
            System.out.println("Proceso cancelado");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mainMenu();
        }
        scan.close();
    }

    public static void verifyStructureOfFile(String vFileName){
        try {
            Scanner scan = new Scanner(new File("pryto1/archives/"+fileName+".txt"));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                int counter=0;
                while (line.contains(";")) {
                    line = line.substring(line.indexOf(";") + ";".length(), line.length());
                    counter++;
                }

                if(counter != 4){
                        try {
                            System.out.println("El archivo no cumple con la estructura para este software");
                            Thread.sleep(2*1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        defineFileName("default");
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        mainMenu();
                    }  
            }
        } catch (IOException e){
            try {
                System.out.println("Archivo no encontrado");
                Thread.sleep(2*1000);
            } catch (Exception e2) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void importData(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nombre que del archivo que desea importar");
        String fN = scan.nextLine();
        System.out.println("¿Seguro que desea cargar estos datos? S/N");
        String confirmation = scan.nextLine();
        if(confirmation.equalsIgnoreCase("S")==true){
            verifyStructureOfFile(fN);
            System.out.println("Archivo verificado");
            defineFileName(fN);
            misContactos.clean();
            misContactos.loadData(fN);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mainMenu();
        }else{
            try {
                System.out.println("Proceso cancelado");  
                Thread.sleep(2*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mainMenu();
        }
        scan.close();
    }

    public static void exportData(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nombre que desea para exportar el archivo");
        String fN = scan.nextLine();
        misContactos.saveData(fN);
        misContactos.clean();
        try {
            System.out.println("Archivo Exportado con exito");
            Thread.sleep(2*1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scan.close();
    }

    public static String contactInfoToString(String name, String numbers, String mail,
        String direction, String nick){
        return "Nombre: "+name+"\nNumero(s): "+numbers+"\nE-Mail: "+mail+"\nDirección: "+direction+"\nSobrenombre: "+nick;
    }
}