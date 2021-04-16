package pryto1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pryto1.classes.Contact;
import pryto1.classes.OriginalException;
import pryto1.classes.Phonebook;

public class PhonebookTests {
    Phonebook testPhonebook = new Phonebook();

    @DisplayName("Testeando metodo de busqeda por nombre")
    @Test
    void testingSearcByName(){
        try {
            testPhonebook.loadData("testresourcearchive");
            assertEquals("TestName", testPhonebook.searchContactByName("TestName")[0].getName());
        } catch (OriginalException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Testeando metodo de busqeda por Correo")
    @Test
    void testingSearcByMail(){
        Phonebook testPhonebook = new Phonebook();
        testPhonebook.loadData("testresourcearchive");
        try {
            assertEquals("TestMail", testPhonebook.searchContactByMail("TestMail")[0].getMail());
        } catch (OriginalException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Testeando metodo de busqeda por direccion")
    @Test
    void testingSearcByDirection(){
        Phonebook testPhonebook = new Phonebook();
        testPhonebook.loadData("testresourcearchive");
        try {
            assertEquals("TestDirection", testPhonebook.searchContactByDirection("TestDirection")[0].getDirection());
        } catch (OriginalException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Testeando metodo de busqeda por sobrenombre")
    @Test
    void testingSearcByNick(){
        Phonebook testPhonebook = new Phonebook();
        testPhonebook.loadData("testresourcearchive");
        try {
            assertEquals("TestNick", testPhonebook.searchContactByNick("TestNick")[0].getNick());
        } catch (OriginalException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Testeando metodo returnInfo")
    @Test
    void testingReturnInfo(){
        Phonebook testPhonebook = new Phonebook();
        Contact testContact = new Contact("TestName", "TestNumber", "TestMail", "TestDirection", "TestNick");
        Contact testArray[] = {testContact};
        testPhonebook.addContact(testContact);
        assertEquals(testArray[0],testPhonebook.returnInfo()[0]);
    }

    @DisplayName("Testeando metodo de identificacion de un contacto en la agenda")
    @Test
    void testingIdentifyEspecificContact(){
        Phonebook testPhonebook = new Phonebook();
        Contact testContact = new Contact("TestName", "TestNumber", "TestMail", "TestDirection", "TestNick");
        testPhonebook.addContact(testContact);
        try {
            assertEquals(testContact, testPhonebook.identifyEspecificContact(1));
        } catch (OriginalException e) {
            e.printStackTrace();
        }
    }
    
    @DisplayName("Testeando metodo de edicion, nombre de un contacto")
    @Test
    void testingEditEspecificContactInfo1(){
        Phonebook testPhonebook = new Phonebook();
        Contact testContact = new Contact("TestName", "TestNumber", "TestMail", "TestDirection", "TestNick");
        testPhonebook.addContact(testContact);
        testPhonebook.editEspecificContactInfo(1, 1, "NewTestName");
        assertEquals("NewTestName", testPhonebook.returnInfo()[0].getName());
    }

    @DisplayName("Testeando metodo de edicion, agregando nuevo numero a un contacto")
    @Test
    void testingEditEspecificContactInfo2(){
        Phonebook testPhonebook = new Phonebook();
        Contact testContact = new Contact("TestName", "TestNumber", "TestMail", "TestDirection", "TestNick");
        testPhonebook.addContact(testContact);
        testPhonebook.editEspecificContactInfo(2, 1, "NewTestNumber");
        assertEquals("TestNumber,NewTestNumber", testPhonebook.returnInfo()[0].getNumbers());
    }

    @DisplayName("Testeando metodo de edicion, editando numero en un contacto")
    @Test
    void testingEditEspecificContactInfo3(){
        Phonebook testPhonebook = new Phonebook();
        Contact testContact = new Contact("TestName", "TestNumber", "TestMail", "TestDirection", "TestNick");
        testPhonebook.addContact(testContact);
        testPhonebook.editEspecificContactInfo(3, 1, "TestNumber|NewTestNumber");
        assertEquals("NewTestNumber", testPhonebook.returnInfo()[0].getNumbers());
    }

    @DisplayName("Testeando metodo de edicion, correo de un contacto")
    @Test
    void testingEditEspecificContactInfo4(){
        Phonebook testPhonebook = new Phonebook();
        Contact testContact = new Contact("TestName", "TestNumber", "TestMail", "TestDirection", "TestNick");
        testPhonebook.addContact(testContact);
        testPhonebook.editEspecificContactInfo(4, 1, "NewTestMail");
        assertEquals("NewTestMail", testPhonebook.returnInfo()[0].getMail());
    }

    @DisplayName("Testeando metodo de edicion, direccion de un contacto")
    @Test
    void testingEditEspecificContactInfo5(){
        Phonebook testPhonebook = new Phonebook();
        Contact testContact = new Contact("TestName", "TestNumber", "TestMail", "TestDirection", "TestNick");
        testPhonebook.addContact(testContact);
        testPhonebook.editEspecificContactInfo(5, 1, "NewTestDirection");
        assertEquals("NewTestDirection", testPhonebook.returnInfo()[0].getDirection());
    }

    @DisplayName("Testeando metodo de edicion, sobrenombre de un contacto")
    @Test
    void testingEditEspecificContactInfo6(){
        Phonebook testPhonebook = new Phonebook();
        Contact testContact = new Contact("TestName", "TestNumber", "TestMail", "TestDirection", "TestNick");
        testPhonebook.addContact(testContact);
        testPhonebook.editEspecificContactInfo(6, 1, "NewTestNick");
        assertEquals("NewTestNick", testPhonebook.returnInfo()[0].getNick());
    }

}
