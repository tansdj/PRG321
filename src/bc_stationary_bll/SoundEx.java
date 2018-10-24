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
public class SoundEx {
    
    public static String Soundex(String data)
    {
        StringBuilder result = new StringBuilder();
        if((!data.equals(""))&&(data.length() > 0))
        {
            String previousCode = "";
            String currentCode = "";
            String currentLetter = "";

            result.append(data.substring(0, 1));
            
            for (int i = 1; i < data.length(); i++) {
                System.out.println(data.length());
                System.out.println(i);
                currentLetter = data.substring(i,1).toLowerCase();
                System.out.println(currentLetter);
                currentCode = "";
                
                if("bfpv".indexOf(currentLetter) > -1)
                {
                    currentCode = "1";
                }
                else if("cgjkqsxz".indexOf(currentLetter) > -1)
                {
                    currentCode = "2";
                }
                else if("dt".indexOf(currentLetter) > -1)
                {
                    currentCode = "3";
                }
                else if(currentLetter.equals("l"))
                {
                    currentCode = "4";
                }
                else if("mn".indexOf(currentLetter) > -1)
                {
                    currentCode = "5";
                }
                else if(currentLetter.equals("r"))
                {
                    currentCode = "6";
                }
                
                if(!currentCode.equals(previousCode))
                {
                    result.append(currentCode);
                }
                
                if(result.length() == 4)
                {
                    break;
                }
                
                if(!currentCode.equals(""))
                {
                    previousCode = currentCode;
                }
            }
        }
        if(result.length() < 4)
            {
                for (int i = 0; i < 4-result.length(); i++) {
                    result.append('0'); // a 0 will be added until the length is four
                } 
            }
            
            return result.toString().toUpperCase();
    }   
}
