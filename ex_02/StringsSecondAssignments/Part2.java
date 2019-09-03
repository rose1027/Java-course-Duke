
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb)
    {
        int count = 0;
        int startIndex = stringb.indexOf(stringa);
        while(startIndex > 0)
        {
           int sensearch = startIndex + stringa.length();
          
            startIndex = stringb.indexOf(stringa,sensearch);
           // System.out.println("startIndex = " + startIndex);
            count = count + 1;
            //System.out.println("count=" + count);
        }
        return count;
    }
    
    public void testhowMany()
    {
        String a1 = "GAA";
        String b1 = "ATGAACGAATTGAATC";
        int count1 = howMany(a1,b1);
        System.out.println(" totalcount=" + count1);
        String a2 = "AA";
        String b2 = "ATAAAA";
        int count2 = howMany(a2,b2);
        System.out.println("totalcount= " + count2);
        String a3 = "ba";
        String b3 = "zoo";
        int count3 = howMany(a3,b3);
        System.out.println("totalcount=" + count3);
        
    }

}
