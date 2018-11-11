/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Tanya This is a generic class used for any and all communication
 * between the server and client. Based on the situation, the appropriate
 * constructor is called, either a request object that tells the server what is
 * needed, or a result object, of different types, that is sent to the client in
 * response to its request.
 */
public class Communication<T> implements Serializable {

    public int methodIdentifier;
    public T requestObject;
    public T objectResult;
    public ArrayList<T> listResult;
    public boolean boolResult;
    public int intResult;

    public Communication(int methodIdentifier, T requestObject) {
        this.methodIdentifier = methodIdentifier;
        this.requestObject = requestObject;
    }

    public Communication(T objectResult) {
        this.objectResult = objectResult;
    }

    public Communication(ArrayList<T> listResult) {
        this.listResult = listResult;
    }

    public Communication(boolean boolResult) {
        this.boolResult = boolResult;
    }

    public Communication(int intResult) {
        this.intResult = intResult;
    }

}
