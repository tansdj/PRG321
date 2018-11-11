/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Eldane This class is used to serialize or deserialize any object or
 * collection used to instantiate the class.
 */
public class GenericSerializer<T> {

    private String filename;
    private T data;

    public GenericSerializer(String filename, T data) {
        this.filename = filename;
        this.data = data;
    }

    public GenericSerializer() {
    }

    public String getFilename() {
        return filename;
    }

    public T getData() {
        return data;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void Serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);
            oos.writeObject(this.data);

            oos.flush();
            fileOut.close();
            oos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public T Deserialize() {
        T dataInFile = null;
        try {
            FileInputStream fileIn = new FileInputStream(this.filename);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            dataInFile = (T) ois.readObject();

            fileIn.close();
            ois.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        return dataInFile;
    }
}
