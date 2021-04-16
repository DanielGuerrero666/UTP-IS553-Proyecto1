/* Clase OriginalException, JavaSE-1.8, 16/04/2021, Juan Daniel Noriega Guerrero
 *  Excepcion original para los metodos del sistema, modelo clasico de creacion 
 *  de una excepcion propia.
 */
package pryto1.classes;

public class OriginalException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public OriginalException(String msg){
        super(msg);
    }
    
}
