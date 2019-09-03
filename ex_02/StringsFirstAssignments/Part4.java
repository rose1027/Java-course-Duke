
/**
 * Write a description of Part4 here.
 *
 * @author (Tingting Hu)
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class Part4
{
    public String Findurl(String s)
    {
        //URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    // String word = "^\"youtube.com\"$";
        String word = "youtube.com";
        String quote = "\"";
      //  String link = null;
        String origlink = null;
        String newstring = s.toLowerCase();
        int startIndex = newstring.indexOf(word);
        if(startIndex != -1)
            {
                int urstarIndex = newstring.lastIndexOf(quote,startIndex);
                int urendIndex = newstring.indexOf(quote,startIndex+11);
              //  link = newstring.substring(urstarIndex,urendIndex+1);
                origlink = s.substring(urstarIndex,urendIndex+1);
            }


       return origlink;

   }

    void testFindurl()
    {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : ur.words())
        {
            String findlink = Findurl(s) ;
            if(findlink == null)
                continue;
            else
                System.out.println("youtube link is " + findlink);
        }

    }
}
