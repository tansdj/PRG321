/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

/**
 *
 * @author Eldane
 */
public class CustomException extends Exception{
    
    public final int errorCode;
    
    public CustomException(int code) {
		super();
		this.errorCode = code;
	}

	public CustomException(String message, Throwable cause, int code) {
		super(message, cause);
		this.errorCode = code;
	}

	public CustomException(String message, int code) {
		super(message);
		this.errorCode = code;
	}

	public CustomException(Throwable cause, int code) {
		super(cause);
		this.errorCode = code;
	}

    public int getErrorCode() {
        return errorCode;
    }
	
	
    
}
