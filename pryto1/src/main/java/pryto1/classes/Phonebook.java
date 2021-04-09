package pryto1.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Phonebook{
    private List<Contact> contactList = new ArrayList<>();
    private static File archive;
    private static String contactInfoFormat;
    private static String contactName;
    private static String contactNumbers;
    private static String contactMail;
    private static String contactDirection;
    private static String contactNick;

    public void addContact(Contact contact){
        contactList.add(contact);
    }

    public void searchContactByName(String name){
        int searchedCounter=0;
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getName().compareTo(name) == 0 ){
                instanceOfContact.showData();
            }
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            System.out.println("No se halló ningun contacto bajo este nombre");
        }
    }

    public void searchContactByNumber(String number){
        int searchedCounter=0;
        for(Contact instanceOfContact : contactList){
            String numbersChain = instanceOfContact.getNumbers();
            int aux=0;
            int index;
            do {
                index = numbersChain.indexOf(",", aux);
                if(index == -1){
                    index = numbersChain.length();
                }
                String actualNumber = numbersChain.substring(aux, index);
                if(actualNumber.compareTo(number) == 0){
                    instanceOfContact.showData();
                }else{
                    aux = index+1;
                }
                aux = index+1;
            } while (index != numbersChain.length());
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            System.out.println("No se halló ningun contacto bajo este número");
        }
    }

    public void searchContactByMail(String mail){
        int searchedCounter=0;
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getMail().compareTo(mail) == 0 ){
                instanceOfContact.showData();
            }
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            System.out.println("No se halló ningun contacto bajo este nombre de correo");
        }
    }

    public void searchContactByDirection(String direction){
        int searchedCounter=0;
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getDirection().compareTo(direction) == 0 ){
                instanceOfContact.showData();
            }
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            System.out.println("No se halló ningun contacto bajo esta dirección");
        }        
    }

    public void searchContactByNick(String nick){
        int searchedCounter=0;
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getNick().compareTo(nick) == 0 ){
                instanceOfContact.showData();
            }
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            System.out.println("No se halló ningun contacto bajo este sobrenombre");
        }
    }

    public void showContacts(){
        int i=1;
        for(Contact instanceOfContact : contactList){
            System.out.println("==============================");
            System.out.println("Contacto #"+i);
            instanceOfContact.showData();
            i++;
        }
    }

    public void identifyEspecificContact(int positionInList){
        positionInList--;
       contactList.get(positionInList).showData();
    }

    public void editEspecificContactInfo(int option, int positionInList, String newInfo){
        positionInList--;
        contactList.get(positionInList).editData(option, newInfo);
    }

    public void deleteContact(int positionInList){
        positionInList--;
        contactList.remove(positionInList);
    }

    public void verifyContactNumbers(){
        if(contactList.isEmpty()==false){
            for(Contact instanceOfContact : contactList){
                String numbersChain = instanceOfContact.getNumbers();
                int aux=0;
                int index;
                do {
                    index = numbersChain.indexOf(",", aux);
                    if(index == -1){
                        index = numbersChain.length();
                    }
                    String actualNumber = numbersChain.substring(aux, index);
                    int counter = 0;
                    for(Contact innerInstanceOfContact : contactList){
                        if(innerInstanceOfContact.getNumbers().indexOf(actualNumber) != -1){
                            counter++;
                            if(counter>1){
                                System.out.println("Error: Número repetido hallado, Imposible acceder a la informacion de contacto...");
                                System.exit(0);
                            }
                        }
                    }
                    aux = index+1;
                } while (index != numbersChain.length());
            }
            System.out.println("Agenda verificada, no se detectaron problemas.");
        }else{
            System.out.println("La agenda esta vacia. Nada que verificar.");
        }
    }

    public void verifyEditContactNumbers(){
        if(contactList.isEmpty()==false){
            for(Contact instanceOfContact : contactList){
                String numbersChain = instanceOfContact.getNumbers();
                int aux=0;
                int index;
                do {
                    index = numbersChain.indexOf(",", aux);
                    if(index == -1){
                        index = numbersChain.length();
                    }
                    String actualNumber = numbersChain.substring(aux, index);
                    int counter = 0;
                    for(Contact innerInstanceOfContact : contactList){
                        if(innerInstanceOfContact.getNumbers().indexOf(actualNumber) != -1){
                            counter++;
                            if(counter>1){
                                System.out.println("Error: No es posible almacenar números repetidos en la agenda");
                                contactList.clear();
                                loadData("default");
                            }
                        }
                    }
                    aux = index+1;
                } while (index != numbersChain.length());
            }
            System.out.println("Número verificado, no se detectaron problemas.");
        }else{
            System.out.println("La agenda esta vacia. Nada que verificar.");
        }
    }

    public void loadData(String fileName){
        try {
            Scanner scan = new Scanner(new File("pryto1/archives/"+fileName+".txt"));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                int index = line.indexOf(";");
                contactName = line.substring(0, index);
                int aux = index+1;
                index = line.indexOf(";", aux);
                contactNumbers = line.substring(aux, index);
                aux = index+1;
                index = line.indexOf(";", aux);
                contactMail = line.substring(aux, index);
                aux = index+1;
                index = line.indexOf(";", aux);
                contactDirection = line.substring(aux, index);
                aux = index+1;
                index = line.length();
                contactNick = line.substring(aux, index);
                Contact instanceOfContact = new Contact(contactName, contactNumbers,
                     contactMail, contactDirection, contactNick);
                addContact(instanceOfContact);
            }
            scan.close();
        } catch (IOException ex) {
            System.out.println("Error: Archivo no econtrado, Creando lista de contactos por defecto...");
            archive = new File("pryto1/archives/default.txt");
                try {
                    archive.createNewFile();
                    System.out.println("Creada lista de contactos por defecto");
                    loadData("default");
                } catch (IOException ex2) {
                    System.out.println("Error: Lista de contactos por defecto no puede ser creada");
                    ex2.printStackTrace();
            }
        }
    }    

    public void saveData(String fileName){
        try {
            FileWriter writer = new FileWriter("pryto1/archives/"+fileName+".txt");
            for(Contact instanceOfContact : contactList){
                contactInfoFormat = instanceOfContact.getName()+";"+instanceOfContact.getNumbers()+";"+
                    instanceOfContact.getMail()+";"+instanceOfContact.getDirection()+";"+
                        instanceOfContact.getNick();                   
                
                writer.write(contactInfoFormat+"\n");
            }
            writer.close();
            contactList.clear();
        } catch (IOException e) {
            File newFile = new File("pryto1/archives/"+fileName+".txt");
            try {
                newFile.createNewFile();
                saveData(fileName);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void clean(){
        contactList.clear();
    }
}