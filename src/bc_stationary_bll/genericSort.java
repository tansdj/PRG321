/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tanya
 * Implementation example: Collections.sort(books, new genericSort(Book.class.getField("name")));--> referenced field must be public
 */
public class genericSort <T> implements Comparator<T>{

    Field f;
    
    public genericSort(Field f) throws ClassNotFoundException {
        this.f = f;
    }
    
    
    
    @Override
    public int compare(T o1, T o2) {
        
        if(f.getType().equals(String.class)){
            try {
                return f.get(o1).toString().toUpperCase().compareTo(f.get(o2).toString().toUpperCase());
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(genericSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (f.getType().equals(Integer.TYPE)){
            try {
                if(f.get(o1)==f.get(o2)){
                    return 0;
                }else if(Integer.parseInt(f.get(o1).toString())>Integer.parseInt(f.get(o2).toString())){
                    return 1;
                }else{
                    return -1;
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(genericSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (f.getType().equals(Double.TYPE)){
            try {
                if(f.get(o1)==f.get(o2)){
                    return 0;
                }else if(Double.parseDouble(f.get(o1).toString())>Double.parseDouble(f.get(o2).toString())){
                    return 1;
                }else{
                    return -1;
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(genericSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
}
