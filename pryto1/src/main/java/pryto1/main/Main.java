/* Clase Main, JavaSE-1.8, 16/04/2021, Juan Daniel Noriega Guerrero
 *  Clase princiapl encargada de la interaccion programa-usuario
 */
package pryto1.main;

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
        try {
            misContactos.verifyContactNumbers();
            System.out.println("Agenda verificada, no se detectaron problemas.");
            mainMenu();
        } catch (OriginalException e) {
            System.out.println("Error: "+e.getMessage());
            System.exit(0);
        }
    }

    /*  Metodo "defineFileName" recibe un nombre para definir como archivo de trabajo.
    */
    public static void defineFileName(String setFileName){
        fileName = setFileName;
    }

    /*  Metodo "mainMenu" metodo que trabaja como menu principal, posee todas las posibles opciones
    *   de trabajo con el programa para el usuario.
    */
    public static void mainMenu(){
        Scanner scan = new Scanner(System.in);
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

        System.out.print("[#] : ");
        String option = scan.nextLine();
        switch(option){
            case "1":            
                addNewContact();
                break;
            case "2":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                searchInPhonebook();
                break;
            case "3":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                showPhonebook();
                break;
            case "4":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                editPhonebook();
                break;            
            case "5":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                deleteContactInPhonebook();
                break;        
            case "6":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                exportData();
                break;        
            case "7":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                importData();
                break;      
            case "8":
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
        }
        scan.close();
    }

    /*  Metodo "addNewContact" se encarga de leer todos los datos ingreasdos por el usuario y crear
    *   una instancia de Contact con esa informacion que sera guardada en la lista de contactos
    *   tras esto, actualiza la agenda.
    */
    public static void addNewContact(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese los datos del nuevo contacto:");
        System.out.print("Nombre (Obligatorio): ");
        String name = scan.nextLine();
        while(name.isBlank()){
            System.out.print("Nombre (Obligatorio): ");
            name = scan.nextLine();
        }
        System.out.print("Número (Obligatorio): ");
        String number = scan.nextLine();
        while(number.isBlank()){
            System.out.print("Número (Obligatorio): ");
            number = scan.nextLine();
        }
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
        System.out.print("Lugar de Primer Encuentro (Obligatorio): ");
        String meetPlace = scan.nextLine();
        while(meetPlace.isBlank()){
            System.out.print("Lugar de Primer Encuentro (Obligatorio): ");
            meetPlace = scan.nextLine();
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
            misContactos.addContact(new Contact(name, number, meetPlace, mail, direction, nick));
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

    /*  Metodo "searchInPhonebook" Se encarga de ofrecer al usuario una serie de busquedas por los 
    *   distintos atributos de la clase Contact, tras esto lee informacion por consola que envia a
    *   sus metodos realcionados en las clases internas del programa.
    */
    public static void searchInPhonebook(){
        Scanner scan = new Scanner(System.in);
        Contact instanceOfContact2 = new Contact();
        Contact[] matchesArray;
        System.out.println("============== Filtros de Busqueda ==============");
        System.out.println("[1] - Por Nombre");
        System.out.println("[2] - Por Número");
        System.out.println("[3] - Por lugar de primer encuentro");
        System.out.println("[4] - Por E-mail");
        System.out.println("[5] - Por Dirección");
        System.out.println("[6] - Por Sobrenombre");

        System.out.print("[#] : ");
        String option = scan.nextLine();
        switch(option){
            case "1": 
                System.out.print("Ingrese el nombre a buscar: ");
                String searchName = scan.nextLine();
                try {
                    matchesArray = misContactos.searchContactByName(searchName);
                    int i=1;
                    for(Contact instanceOfContact : matchesArray){
                        System.out.println("====================================================");
                        System.out.println("Contacto #"+i);
                        System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                            instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                        i++;
                    }
                    System.out.println("====================================================");
                    scan.nextLine();
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    scan.nextLine();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case "2": 
                System.out.print("Ingrese el número a buscar: ");
                String searchNumber = scan.nextLine();
                try {
                    instanceOfContact2 = misContactos.searchContactByNumber(searchNumber);
                    System.out.println("====================================================");
                    System.out.println(contactInfoToString(instanceOfContact2.getName(),instanceOfContact2.getNumbers(),instanceOfContact2.getMeetPlace(),
                        instanceOfContact2.getMail(),instanceOfContact2.getDirection(),instanceOfContact2.getNick()));
                    System.out.println("====================================================");
                    scan.nextLine();
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    scan.nextLine();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case "3":
                System.out.print("Ingrese el lugar a buscar: ");
                String searchMeetPlace = scan.nextLine();
                try {
                    matchesArray = misContactos.searchContactByMeetPlace(searchMeetPlace);
                    int i=1;
                    for(Contact instanceOfContact : matchesArray){
                        System.out.println("====================================================");
                        System.out.println("Contacto #"+i);
                        System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                            instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                        i++;
                    }
                    System.out.println("====================================================");
                    scan.nextLine();
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    scan.nextLine();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                mainMenu();
                break;
            case "4": System.out.print("Ingrese el correo a buscar: ");
                String searchMail = scan.nextLine();
                try {
                    matchesArray = misContactos.searchContactByMail(searchMail);
                    int i=1;
                    for(Contact instanceOfContact : matchesArray){
                        System.out.println("====================================================");
                        System.out.println("Contacto #"+i);
                        System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                            instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                        i++;
                    }
                    System.out.println("====================================================");
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
            case "5": System.out.print("Ingrese la dirección a buscar: ");
                String searchDirection = scan.nextLine();
                try {
                    matchesArray = misContactos.searchContactByDirection(searchDirection);
                    int i=1;
                    for(Contact instanceOfContact : matchesArray){
                        System.out.println("====================================================");
                        System.out.println("Contacto #"+i);
                        System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                            instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                        i++;
                    }
                    System.out.println("====================================================");
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
            case "6": System.out.print("Ingrese el sobrenombre a buscar: ");
                String searchNick = scan.nextLine();
                try {
                    matchesArray = misContactos.searchContactByNick(searchNick);
                    int i=1;
                    for(Contact instanceOfContact : matchesArray){
                        System.out.println("====================================================");
                        System.out.println("Contacto #"+i);
                        System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                            instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                        i++;
                    }
                    System.out.println("====================================================");
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

    /*  Metodo "showPhonebook" este metodo se encarga de mostrar al usuario por consola,
    *    su lista de contactos actual
    */
    public static void showPhonebook(){
        Scanner scan = new Scanner(System.in);
        System.out.println("============== Contactos en la Agenda ==============");
        Contact[] arrayOfInfo = misContactos.returnInfo();
        int i=1;
        for(Contact instanceOfContact : arrayOfInfo){
            System.out.println("Contacto #"+i);
            System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
            System.out.println("====================================================");
            i++;
        }
        scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        mainMenu();
        scan.close();
    }

    /*  Metodo "editPhonebook" este metodo se encarga de enseñar al usuario una serie de opciones
    *   con las cuales indicar el contacto y los atributos de este a editar.
    */
    public static void editPhonebook(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la posicion del contacto a editar: ");
        int positionInList = scan.nextInt();
        scan.nextLine();
        Contact instanceOfContact = new Contact();
        try {
            instanceOfContact = misContactos.identifyEspecificContact(positionInList);
            System.out.println("====================================================");
            System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
            System.out.println("====================================================");
        } catch (OriginalException e2) {
            System.out.println("Error: "+e2.getMessage());
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            mainMenu();
        }
        System.out.println("¿Es este el contacto que desea editar? S/N");
        String confirmation = scan.nextLine();
        if(confirmation.equalsIgnoreCase("S")==true){
            System.out.println("Seleccione el campo del contacto que desea editar");
            System.out.println("[1] - Editar nombre");
            System.out.println("[2] - Agregar número");
            System.out.println("[3] - Editar número existente");
            System.out.println("[4] - Editar lugar de primer encuentro");
            System.out.println("[5] - Editar correo");
            System.out.println("[6] - Editar dirección");
            System.out.println("[7] - Editar sobrenombre");

            System.out.print("[#] : ");
            String option = scan.nextLine();
            switch(option){
                case "1":
                    System.out.println("Ingrese el nuevo nombre del contacto: ");
                    String name = scan.nextLine();
                    misContactos.editEspecificContactInfo(1, positionInList, name);
                    misContactos.saveData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    misContactos.loadData(fileName);
                    System.out.println("Informacion actualizada");
                    System.out.println("====================================================");
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    System.out.println("====================================================");
                    scan.nextLine();
                    break;
                case "2":
                    System.out.print("Ingrese el nuevo numero de contacto :");
                    String newNumber = scan.nextLine();
                    misContactos.editEspecificContactInfo(2, positionInList, newNumber);
                try {
                    misContactos.verifyEditContactNumbers();
                    System.out.println("Número verificado, no se detectaron problemas.");
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    e.printStackTrace();
                }
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    System.out.println("====================================================");
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    System.out.println("====================================================");
                    scan.nextLine();
                    break;
                case "3":
                    System.out.print("Ingrese el numero que desea editar: ");
                    String oldToChangeNumber = scan.nextLine();
                    System.out.print("Ingrese el nuevo numero: ");
                    String newToChangeNumber = scan.nextLine();
                    String codednumber = oldToChangeNumber+"|"+newToChangeNumber;
                    misContactos.editEspecificContactInfo(3, positionInList, codednumber);
                try {
                    misContactos.verifyEditContactNumbers();
                    System.out.println("Número verificado, no se detectaron problemas.");
                } catch (OriginalException e) {
                    System.out.println("Error: "+e.getMessage());
                    e.printStackTrace();
                }
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    System.out.println("====================================================");
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    System.out.println("====================================================");
                    scan.nextLine();
                    break;
                case "4":
                    System.out.println("Ingrese el nuevo lugar de primer encuentro del contacto: ");
                    String meetPlace = scan.nextLine();
                    misContactos.editEspecificContactInfo(4, positionInList, meetPlace);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    System.out.println("====================================================");
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    System.out.println("====================================================");
                    scan.nextLine();
                    break;
                case "5":
                    System.out.println("Ingrese el nuevo correo del contacto: ");
                    String mail = scan.nextLine();
                    misContactos.editEspecificContactInfo(5, positionInList, mail);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    System.out.println("====================================================");
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    System.out.println("====================================================");
                    scan.nextLine();
                    break;
                case "6":
                    System.out.println("Ingrese la nueva dirección del contacto: ");
                    String direction = scan.nextLine();
                    misContactos.editEspecificContactInfo(6, positionInList, direction);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    System.out.println("====================================================");
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    System.out.println("====================================================");
                    scan.nextLine();
                    break;
                case "7":
                    System.out.println("Ingrese el nuevo sobrenombre del contacto: ");
                    String nick = scan.nextLine();
                    misContactos.editEspecificContactInfo(7, positionInList, nick);
                    misContactos.saveData(fileName);
                    misContactos.loadData(fileName);
                    System.out.println("Edicion de contacto exitosa.");
                    System.out.println("Informacion actualizada");
                    System.out.println("====================================================");
                    System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
                        instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
                    System.out.println("====================================================");
                    scan.nextLine();
                    break;
                default:
                    System.out.println("Opcion Invalida");
                    scan.nextLine();
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

    /*  Metodo "deleteContactInPhonebook" se encarga de indicar y enseñar al usuario el contacto elegido
    *   antes de solicitar una confirmacion para la invocacion de los metodos de borrado de contactos.
    */
    public static void deleteContactInPhonebook(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la posicion del contacto a eliminar: ");
        int positionInList = scan.nextInt();
        scan.nextLine();
        Contact instanceOfContact = new Contact();
        try {
            instanceOfContact = misContactos.identifyEspecificContact(positionInList);
        } catch (OriginalException e) {
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println("====================================================");
        System.out.println(contactInfoToString(instanceOfContact.getName(),instanceOfContact.getNumbers(),instanceOfContact.getMeetPlace(),
            instanceOfContact.getMail(),instanceOfContact.getDirection(),instanceOfContact.getNick()));
        System.out.println("====================================================");
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

    /*  Metodo "importData" se encarga de importar de un archivo, cuyo nombre es pedido
    *   por consola, para cargar dicho archivo como archivo de trabajo durante la ejecucion del programa
    */
    public static void importData(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nombre que del archivo que desea importar");
        String fN = scan.nextLine();
        System.out.println("¿Seguro que desea cargar estos datos? S/N");
        String confirmation = scan.nextLine();
        if(confirmation.equalsIgnoreCase("S")==true){
            Integer counter = misContactos.verifyStructureOfFile(fN);
            if(counter != 5){
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
            System.out.println("Archivo verificado, importando datos");
            defineFileName(fN);
            misContactos.clean();
            misContactos.loadData(fN);
            try {
                misContactos.verifyContactNumbers();
            } catch (OriginalException e1) {
                System.out.println(e1.getMessage());
                System.exit(0);
            }
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

    /*  Metodo "exportData" se ecnarga de crear un archivo para el usuario en donde se exportara
    *   la informacion de la listad e contactos actual, dicha exportacion se hallara en la carpeta
    *   archives.
    */
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

    /*  Metodo "contactInfoToString" que facilita la impresion de informacion ordenada en los demas 
    *   metodos de la clase Main.
    */
    public static String contactInfoToString(String name, String numbers, String meetPlace,
        String mail, String direction, String nick){
        return "Nombre: "+name+"\nNumero(s): "+numbers+"\nLugar de primer encuentro: "+meetPlace+"\nE-Mail: "+mail+"\nDirección: "+direction+"\nSobrenombre: "+nick;
    }
}