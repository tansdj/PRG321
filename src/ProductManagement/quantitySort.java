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
public class quantitySort implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Stock s1 = (Stock)o1;
        Stock s2 = (Stock)o2;
        
        if(s1.getQuantity() == s2.getQuantity())
        {
            return 0;
        }
        else if(s1.getQuantity() > s2.getQuantity())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
    
}
