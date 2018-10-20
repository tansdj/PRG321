/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_stationary_bll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Eldane
 */
public class InputValidation {
    public static void messageBox(String message,String messageType, String title)
    {
        switch(messageType)
        {
            case "Error: ":
                JOptionPane.showMessageDialog(null, message, messageType + title, JOptionPane.ERROR_MESSAGE);
                break;
            case"Success: ":
                JOptionPane.showMessageDialog(null, message, messageType + title, JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    
    public static boolean isNumeric(String val)
    {
        for (char c : val.toCharArray()) 
        {
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }
    
    public static boolean validateString(String[][] inputArray)
    {       
        boolean validated = false;
        int nullCounter = 0, 
            stringCounter = 0;
        
        try 
        {             
            for (int i = 0; i < inputArray.length; i++) 
            {
                String[] subArray = inputArray[i];
                
                for (int j = 1; j <= subArray.length; j++) 
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                        
                    if (isNumeric(subArray[i])) 
                    {
                        messageBox("Please enter a valid: " + Arrays.toString(inputArray[i]), "Error: ", "number in value.");
                    }
                    else
                    {
                        stringCounter++;
                    }
                        
                    if (nullCounter == inputArray.length && stringCounter == inputArray.length) 
                    {
                        validated = true;
                    }
                }
            }            
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            messageBox("Successfully completed process", "Success: ", "Data validated");
            return validated;
        }       
    }
    
    public static boolean validateName_Surname(String[][] inputArray)
    {       
        boolean validated = false;
        boolean special = true;
        int nullCounter = 0, 
            stringCounter = 0,
                specialCounter = 0;
        
        try 
        {             
            for (int i = 0; i < inputArray.length; i++) 
            {
                String[] subArray = inputArray[i];
                
                for (int j = 1; j <= subArray.length; j++) 
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                        
                    if (isNumeric(subArray[i])) 
                    {
                        messageBox("Please enter a valid: " + Arrays.toString(inputArray[i]), "Error: ", "number in value.");
                    }
                    else
                    {
                        stringCounter++;
                    }
                    
                    String[] specialChars = {"!","@","#","$","^",
                        "&","*","(",")","{","}","[","]","`","~","+","=","'\'",
                        "|",":",";","'","<",">",",",".","_","-"};
                    
                        for (int k = 0; k < specialChars.length; k++) 
                        {
                            if (val.contains(specialChars[k])) 
                            {
                                specialCounter++;
                                messageBox("Please enter a valid " + Arrays.toString(inputArray[i]), "Error: ", "special character in value."); 
                            }
                            
                            if (specialCounter <= 0) 
                            {
                                special = true;
                            }
                        }
                        
                    if (nullCounter == inputArray.length && stringCounter == inputArray.length && special) 
                    {
                        validated = true;
                    }
                }
            }            
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            messageBox("Successfully completed process", "Success: ", "Data validated");
            return validated;
        }       
    }
    
    public static boolean validateInt(Object[][] inputArray)
    {
        boolean validated = false;
        boolean special = false;
        int intCounter = 0, nullCounter = 0, specialCounter = 0;
        
        try 
        {            
            for (int i = 0; i < inputArray.length; i++) 
            {               
                String[] subArray = (String[]) inputArray[i];
                for (int j = 1; j < subArray.length; j++) 
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                                        
                    if (isNumeric(val)) 
                    {
                        intCounter++;
                    } 
                    else 
                    {
                    messageBox("Please enter a valid " + Arrays.toString(inputArray[i]), "Error: ", "letter in value.");    
                    }
                    
                    String[] specialChars = {"!","@","#","$","^",
                        "&","*","(",")","{","}","[","]","`","~","+","=","'\'",
                        "|",":",";","'","<",">",",",".","_"};
                    
                        for (int k = 0; k < specialChars.length; k++) 
                        {
                            if (val.contains(specialChars[k])) 
                            {
                                specialCounter++;
                                messageBox("Please enter a valid " + Arrays.toString(inputArray[i]), "Error: ", "special character in value."); 
                            }
                            
                            if (specialCounter <= 0) 
                            {
                                special = true;
                            }
                        }
 
                   
                    if (nullCounter == inputArray.length && intCounter == inputArray.length && special) 
                    {
                        validated = true;
                    }
                }
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            messageBox("Successfully completed process", "Success: ", "Data validated");
            return validated;
        }
    }
    
    public static boolean validateDouble(Object[][] inputArray)
    {
        boolean validated = false;
        boolean isDouble = false;
        boolean special = false;
        int doubleCounter = 0, nullCounter = 0, specialCounter = 0;
               
        try 
        {
            for (int i = 0; i < inputArray.length; i++) 
            {
                String[] subArray = (String[])inputArray[i];
                for (int j = 1; j < subArray.length; j++) 
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                    
                    if (val.contains(".")) 
                    {
                        try 
                        {
                            Double.parseDouble(val);
                        } 
                        catch (Exception e) 
                        {
                            JOptionPane.showMessageDialog(null, "Value is not a number");
                        }
                        finally
                        {
                            isDouble = true;
                        }
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Value does not contain a '.'");
                    }
                    
                    if (isDouble) 
                    {
                        doubleCounter++;
                    }
                    
                    String[] specialChars = {"!","@","#","$","^",
                        "&","*","(",")","{","}","[","]","`","~","+","=","'\'",
                        "|",":",";","'","<",">",",","_"};
                    
                        for (int k = 0; k < specialChars.length; k++) 
                        {
                            if (val.contains(specialChars[k])) 
                            {
                                specialCounter++;
                                messageBox("Please enter a valid " + Arrays.toString(inputArray[i]), "Error: ", "special character in value."); 
                            }
                            
                            if (specialCounter <= 0) 
                            {
                                special = true;
                            }
                        }
                        
                    if (nullCounter == inputArray.length && doubleCounter == inputArray.length && special) 
                    {
                        validated = true;
                    }
                }
            }
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            messageBox("Successfully completed process", "Success: ", "Data validated");
            return validated;
        }
    }
    
    public static boolean validateCell(Object[][] inputArray)
    {
        boolean validated = false;
        boolean correctLength = false;
        boolean special = false;
        int nullCounter = 0, specialCounter = 0;
        try 
        {
            for (int i = 0; i < inputArray.length; i++)
            {
                String[] subArray = (String[])inputArray[i];
                for (int j = 1; j < subArray.length; j++) 
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                    
                    if (val.length() != 10) 
                    {
                        JOptionPane.showMessageDialog(null, "Number not long enough or too short.");
                    }
                    else
                    {
                        correctLength = true;
                    }
                    
                    String[] specialChars = {"!","@","#","$","^",
                        "&","*","(",")","{","}","[","]","`","~","+","=","'\'",
                        "|",":",";","'","<",">",",",".","_"};
                    
                        for (int k = 0; k < specialChars.length; k++) 
                        {
                            if (val.contains(specialChars[k])) 
                            {
                                specialCounter++;
                                messageBox("Please enter a valid " + Arrays.toString(inputArray[i]), "Error: ", "special character in value."); 
                            }
                            
                            if (specialCounter <= 0) 
                            {
                                special = true;
                            }                           
                        }
                        
                    if (nullCounter == inputArray.length && correctLength && special) 
                    {
                        validated = true;
                    }                         
                }
            }
        }
        catch (Exception e) 
        {
           JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE); 
        }
        finally
        {
            messageBox("Successfully completed process", "Success: ", "Data validated");
            return validated;
        }
    }
    
    public static boolean validateEmail(Object[][] inputArray)
    {
        boolean validated = false;
        boolean isEmail = false;
        int nullCounter = 0;
        String regex = "[A-Z0-9a-z._%+-] +@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,64}";
        
        try 
        {
            for (int i = 0; i < inputArray.length; i++) 
            {
                String[] subArray = (String[])inputArray[i];
                for (int j = 1; j < subArray.length; j++)
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                        Pattern checkPattern = Pattern.compile(regex);
                        Matcher matcher = checkPattern.matcher(val);
                        
                        if (matcher.matches())
                        {
                            isEmail = true;
                        }
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                    
                    if (nullCounter == inputArray.length && isEmail)
                    {
                        validated = true;
                    }          
                }
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE); 
        }
        finally
        {
            return validated;
        }
    }
    
    public static boolean validateStringInt(String[][] inputArray)
    {       
        boolean validated = false;
        int nullCounter = 0;
        
        try 
        {             
            for (int i = 0; i < inputArray.length; i++) 
            {
                String[] subArray = inputArray[i];
                
                for (int j = 1; j <= subArray.length; j++) 
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                        
                    if (nullCounter == inputArray.length) 
                    {
                        validated = true;
                    }
                }
            }            
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            messageBox("Successfully completed process", "Success: ", "Data validated");
            return validated;
        }
    }
    
    public static boolean validateID(String[][] inputArray)
    {
        boolean validated = false;
        boolean correctLength = false;
        boolean special = false;
        int nullCounter = 0, specialCounter = 0;
        try 
        {
            for (int i = 0; i < inputArray.length; i++)
            {
                String[] subArray = (String[])inputArray[i];
                for (int j = 1; j < subArray.length; j++) 
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                    
                    if (val.length() != 13) 
                    {
                        JOptionPane.showMessageDialog(null, "Number not long enough or too short.");
                    }
                    else
                    {
                        correctLength = true;
                    }
                    
                    String[] specialChars = {"!","@","#","$","^",
                        "&","*","(",")","{","}","[","]","`","~","+","=","'\'",
                        "|",":",";","'","<",">",",",".","_"};
                    
                        for (int k = 0; k < specialChars.length; k++) 
                        {
                            if (val.contains(specialChars[k])) 
                            {
                                specialCounter++;
                                messageBox("Please enter a valid " + Arrays.toString(inputArray[i]), "Error: ", "special character in value."); 
                            }
                            
                            if (specialCounter <= 0) 
                            {
                                special = true;
                            }                           
                        }
                        
                    if (nullCounter == inputArray.length && correctLength && special) 
                    {
                        validated = true;
                    }                         
                }
            }
        }
        catch (Exception e) 
        {
           JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE); 
        }
        finally
        {
            messageBox("Successfully completed process", "Success: ", "Data validated");
            return validated;
        }
    }    
    
        public static boolean validatePass(String[][] inputArray)
    {       
        boolean validated = false;
        int nullCounter = 0;
        
        try 
        {             
            for (int i = 0; i < inputArray.length; i++) 
            {
                String[] subArray = inputArray[i];
                
                for (int j = 1; j <= subArray.length; j++) 
                {
                    String val = subArray[j];
                    
                    if (val != null && !val.equals("")) 
                    {
                        nullCounter++;
                    }
                    else
                    {
                        messageBox("Please enter a " + Arrays.toString(inputArray[i]), "Error: ", "empty value.");
                    }
                        
                    if (nullCounter == inputArray.length) 
                    {
                        validated = true;
                    }
                }
            }            
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation failed: ", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            messageBox("Successfully completed process", "Success: ", "Data validated");
            return validated;
        }
    }
    
}
