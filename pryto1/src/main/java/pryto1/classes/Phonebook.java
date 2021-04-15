package pryto1.classes;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
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

    public Contact searchContactByName(String name) throws OriginalException{
        int searchedCounter=0;
        Contact foundedContact = new Contact();
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getName().compareTo(name) == 0 ){
                foundedContact = instanceOfContact;
                break;
            }
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo este nombre");
        }
        return foundedContact;
    }

    public Contact searchContactByNumber(String number) throws OriginalException{
        int searchedCounter=0;
        Contact foundedContact = new Contact();
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
                    foundedContact = instanceOfContact;
                    break;
                }else{
                    aux = index+1;
                }
                aux = index+1;
            } while (index != numbersChain.length());
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo este número");
        }
        return foundedContact;
    }

    public Contact searchContactByMail(String mail) throws OriginalException{
        int searchedCounter=0;
        Contact foundedContact = new Contact();
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getMail().compareTo(mail) == 0 ){
                foundedContact = instanceOfContact;
                break;
            }
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo este correo electronico");
        }
        return foundedContact;
    }

    public Contact searchContactByDirection(String direction) throws OriginalException{
        int searchedCounter=0;
        Contact foundedContact = new Contact();
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getDirection().compareTo(direction) == 0 ){
                foundedContact = instanceOfContact;
                break;
            }
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo esta dirección");
        }        
        return foundedContact;
    }

    public Contact searchContactByNick(String nick) throws OriginalException{
        int searchedCounter=0;
        Contact foundedContact = new Contact();
        for(Contact instanceOfContact : contactList){
            if(instanceOfContact.getNick().compareTo(nick) == 0 ){
                foundedContact = instanceOfContact;
                break;
            }
            searchedCounter++;
        }
        if(searchedCounter == contactList.size()){
            throw new OriginalException("No se halló ningun contacto bajo este sobrenombre");
        }
        return foundedContact;
    }

    public Contact[] returnInfo(){
        Contact[] contactArray = new Contact[contactList.size()]; 
        int i=0;
        for(Contact instanceOfContact : contactList){
            contactArray[i] = instanceOfContact;
            i++;
        }
        return contactArray;
    }

    public Contact identifyEspecificContact(int positionInList) throws OriginalException{
        positionInList--;
        if(positionInList>=contactList.size()){
            throw new OriginalException("Posicion vacia, no es posibel acceder a la informacion");
        }
       return contactList.get(positionInList);
    }

    public void editEspecificContactInfo(int option, int positionInList, String newInfo){
        positionInList--;
        contactList.get(positionInList).editData(option, newInfo);
    }

    public void deleteContact(int positionInList){
        positionInList--;
        contactList.remove(positionInList);
    }

    public void verifyContactNumbers() throws OriginalException{
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
                                throw new  OriginalException("Error: Número repetido hallado, Imposible acceder a la informacion de contacto...");
                            }
                        }
                    }
                    aux = index+1;
                } while (index != numbersChain.length());
            }
        }
    }

    public void verifyEditContactNumbers(String fileName) throws OriginalException{
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
                                throw new OriginalException("Error: No es posible almacenar números repetidos en la agenda");
                            }
                        }
                    }
                    aux = index+1;
                } while (index != numbersChain.length());
            }
        }
    }

    public Integer verifyStructureOfFile(String vFileName){
        int counter=0;
        try {
            Scanner scan = new Scanner(new File("pryto1/archives/"+vFileName+".txt"));
            String line = scan.nextLine();
            counter=0;
            while (line.contains(";")) {
                line = line.substring(line.indexOf(";") + ";".length(), line.length());
                counter++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return counter;
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
            archive = new File("pryto1/archives/default.txt");
                try {
                    archive.createNewFile();
                    loadData("default");
                } catch (IOException ex2) {
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