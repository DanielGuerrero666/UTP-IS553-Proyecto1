/* Clase ContactTest, JavaSE-1.8, 16/04/2021, Juan Daniel Noriega Guerrero
 *  Clase de testeo de los metodos relevantes de la clase Contact
 */
package pryto1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pryto1.classes.Contact;

public class ContactTest {

    @DisplayName("Testeo de Setters & Getters de clase Contact")
    @ParameterizedTest()
    @CsvSource(
        {
            "Daniel, 3204675890, Daniel@utp.edu.co, Quimbaya, Dani"
        })
    void testingSettersAndGetters(String expectedName, String expectedNumber, String expectedMail,
        String expectedDirection, String expectedNick){

        Contact testContact = new Contact(expectedName, expectedNumber, expectedMail,
            expectedDirection, expectedNick);

            assertEquals(expectedName, testContact.getName());
            assertEquals(expectedNumber, testContact.getNumbers());
            assertEquals(expectedMail, testContact.getMail());
            assertEquals(expectedDirection, testContact.getDirection());
            assertEquals(expectedNick, testContact.getNick());
        }

}
