
/**
 * Write a description of Part3 here.
 * Print the part that is not same after comparing two strings.
 * @author (Tingting Hu) 
 * @version (a version number or a date)
 */
public class Part3 {
    public Boolean twoOccurrences(String stringa,String stringb)
    {
       int count = 0;
       int firstindex = stringb.indexOf(stringa);
       //System.out.println("firstindex= " + firstindex); 
       int firstLength = stringa.length();
       int seconSearch = firstindex + firstLength;
       int secondindex = stringb.indexOf(stringa,seconSearch);
       //System.out.println("stringalength= " + stringa.length());
       //System.out.println("seconSearch= " + seconSearch);
       //System.out.println("secondindex= " + secondindex);
       
       if(firstindex == -1 ||secondindex == -1)
       {
          return false; 
        }
       
       return true;
    
    }
    
    public void testing()
    {
        String a = "by";
        String b = "A story by Abby Long";
        String c = "atg";
        String d = "ctgtatgta";
        String e = "a";
        String f = "banana";
        String g = "zoo";
        String h ="forest";
        String i = "an";
        String j = "banana";
        //boolean t = twoOccurrences(a,b);
        boolean m = twoOccurrences(c,d);
        boolean n = twoOccurrences(e,f);
        
   
        //System.out.println("repeat same words at least twice is " + t);
        //System.out.println("repeat same words at least twice is " + m);
        //System.out.println("repeat same words at least twice is " + n);
        System.out.println("last part of string is " + lastPart(g,h));
        System.out.println("last part of string is " + lastPart(i,j));
        
    
    
    }
    
    public String lastPart(String stringa,String stringb)
    {
        int firstIndex = stringb.indexOf(stringa);
        int restIndex = firstIndex + stringa.length();
        if(firstIndex == -1)
        {
            return stringb;
        }
        else
            return stringb.substring(restIndex);
    }

}
