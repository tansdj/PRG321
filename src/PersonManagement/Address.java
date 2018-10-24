/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonManagement;


import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Tanya
 */
public class Address{
    
    private String line1;
    private String line2;
    private String city;
    private String postalCode;

    public Address(String line1, String line2, String city, String postalCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Address() {
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = (city.equals(""))?"N.A":city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = (postalCode.equals(""))?"N.A":postalCode;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = (line2.equals(""))?"N.A":line2;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = (line1.equals(""))?"N.A":line1;
    }

    @Override
    public String toString() {
        return "Address{" + "line1=" + line1 + ", line2=" + line2 + ", city=" + city + ", postalCode=" + postalCode + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.line1);
        hash = 53 * hash + Objects.hashCode(this.line2);
        hash = 53 * hash + Objects.hashCode(this.city);
        hash = 53 * hash + Objects.hashCode(this.postalCode);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.line1, other.line1)) {
            return false;
        }
        if (!Objects.equals(this.line2, other.line2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        return true;
    }

    
    
    

    
}
