/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Tanya 
 * Represents the contact details of any person.
 */
public class Contact implements Serializable {

    private String cell;
    private String email;

    public Contact(String cell, String email) {
        this.cell = cell;
        this.email = email;
    }

    public Contact() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email.equals("")) ? "N.A" : email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = (cell.equals("")) ? "N.A" : cell;
    }

    @Override
    public String toString() {
        return "Contact{" + "cell=" + cell + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.cell);
        hash = 89 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.cell, other.cell)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

}
