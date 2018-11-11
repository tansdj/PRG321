/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Eldane The custom exception class is used to create different
 * exceptions and pass our own specific message through, based on the situation
 * in the front end.
 */
public class CustomException extends Exception {
    
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
