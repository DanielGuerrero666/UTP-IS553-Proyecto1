package pryto1.main;

import pryto1.classes.Contact;
import pryto1.classes.Phonebook;

public class Main {
    static Phonebook misContactos = new Phonebook();
    public static void main(String[] args) {
        //Phonebook misContactos = new Phonebook();
        misContactos.loadData("contactosdeayer");
        //mainMenu();
        //editContactMenu(1);
        misContactos.saveData("contactosdeayer");
    }

    public static void mainMenu(){
        System.out.println("Menu Principal");
    }

    public static void editContactMenu(int option){
        misContactos.searchContactByNumber("23123123123");
    }
}