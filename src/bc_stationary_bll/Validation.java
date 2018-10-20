/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;


/**
 *
 * @author Tanya
 */
public class Validation {
    
    public static boolean testProperString(String input){
        for (int i = 0; i < input.length()-1; i++) {
            if(!Character.isLetter(input.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    public static boolean testNumericString(String input){
        for (int i = 0; i < input.length()-1; i++) {
            if(!Character.isDigit(input.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
    public static boolean testLength(String input,int min,int max){
        if((input.length()>=min) && (input.length()<=max)){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean testInteger(String input){
        try{
            int i = Integer.parseInt(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static boolean testDouble(String input){
        try{
            double d = Double.parseDouble(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static boolean testContains(String input,char[] mandatory){
        for(Character c:mandatory){
            if(!input.contains(c.toString())){
                return false;
            }
        }
        return true;
    }
}
