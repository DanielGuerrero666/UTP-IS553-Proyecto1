package pryto1.main;

import pryto1.classes.Phonebook;

public class Main {
    public static void main(String[] args) {
        Phonebook misContactos = new Phonebook();
        misContactos.defaultLoadData();
        misContactos.showContacts();
    }
}

