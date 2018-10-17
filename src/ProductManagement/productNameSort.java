/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import java.util.Comparator;

/**
 *
 * @author Eldane
 */
public class productNameSort implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Product p1 = (Product)o1;
        Product p2 = (Product)o2;
        
        return p1.getName().compareTo(p2.getName());
    }
    
}
