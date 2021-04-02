package pryto1.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Phonebook{
    private List<Contact> contactList = new ArrayList<>();
    private static File archive;
    private static String contactName;
    private static String contactNumbers;
    private static String contactMail;
    private static String contactDirection;
    private static String contactNick;

    public void addContact(Contact contact){
        contactList.add(contact);
    }

    public void searchContact(Integer index){

    }

    public void showContacts(){
        System.out.println("=============== LISTA DE CONTACTOS ===============");
        for(Contact instanceOfContact : contactList){
            instanceOfContact.showData();
        }
    }

    public void deleteContact(){

    }

    public void verifyContact(){

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
                Contact contactInstance = new Contact(contactName, contactNumbers,
                     contactMail, contactDirection, contactNick);
                addContact(contactInstance);
            }
            scan.close();
        } catch (IOException ex) {
            System.out.println("Error: File not Found...");
        }
    }    

    public void defaultLoadData(){
        archive = new File("pryto1/archives/default.txt");
        if(!archive.exists()){
            try {
                archive.createNewFile();
                System.out.println("Default Contact List Created...");
                loadData("default");
            } catch (IOException ex) {
                System.out.println("Error: Default Contact List Can't be Created...");
            }
        }else{
            loadData("default");
        }
    }

    public void saveData(){

    }
}