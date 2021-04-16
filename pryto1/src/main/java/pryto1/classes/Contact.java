/* Clase Contact, JavaSE-1.8, 16/04/2021, Juan Daniel Noriega Guerrero
 *
 * 
 */
package pryto1.classes;


public class Contact{
    private String name;
    private String numbers;
    private String meetPlace;
    private String mail;
    private String direction;
    private String nick;

    public Contact(){}

    public Contact(String name, String numbers, String meetPlace, String mail, String direction, String nick) {
        this.name = name;
        this.numbers = numbers;
        this.meetPlace = meetPlace;
        this.mail = mail;
        this.direction = direction;
        this.nick = nick;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeetPlace() {
        return meetPlace;
    }

    public void setMeetPlace(String meetPlace) {
        this.meetPlace = meetPlace;
    }


    /* Metodo que recibe una opcion y un valor y determina el cambio de informacion
    *  a realizar en un contacto.
    */

    public void editData(int option, String newValue){
        String confirmation;
        switch(option){
            case 1: 
                confirmation = (newValue.isBlank())? getName():newValue;
                setName(confirmation);
                break;
            case 2: 
                setNumbers(getNumbers()+","+newValue);
                break;
            case 3:    
                if(getNumbers().indexOf(",")==-1){
                    confirmation = newValue.isBlank()? getNumbers():newValue;
                    setNumbers(getNumbers().replaceAll(confirmation.substring(0, confirmation.indexOf("|")),
                    confirmation.substring(confirmation.indexOf("|")+1, confirmation.length())));
                }else{
                    setNumbers(getNumbers().replaceAll(newValue.substring(0, newValue.indexOf("|")),
                    newValue.substring(newValue.indexOf("|")+1, newValue.length())));
                }
                break;
            case 4:
                confirmation = (newValue.isBlank())? getMeetPlace():newValue;
                setMeetPlace(confirmation);
                break;
            case 5:
                setMail(newValue);
                break;
            case 6: 
                setDirection(newValue);
                break;
            case 7: 
                setNick(newValue);
                break;
        }
    }

}