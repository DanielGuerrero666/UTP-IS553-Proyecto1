package pryto1.main;

import pryto1.classes.Contact;
import pryto1.classes.Phonebook;

public class Main {
    public static void main(String[] args) {
        Phonebook misContactos = new Phonebook();
        misContactos.loadData("contactosdeayer");

        misContactos.addContact(new Contact("Amelia", "1232121232", "ame@hotmail.com", "EEUU", "Ame"));
        misContactos.saveData("contactosdeayer");
        misContactos.loadData("contactosdeayer");
        misContactos.showContacts();
    }
}

