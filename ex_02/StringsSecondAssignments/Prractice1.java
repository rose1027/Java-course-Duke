
/**
 * Write a description of Prractice1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Prractice1 {
    public void findAbc(String input) 
    {   int totallength=input.length();
        System.out.println("length="+totallength);
        int index = input.indexOf("abc");
        while (true) 
        {
            if (index == -1|| index >= input.length() - 3)
            {
                break;
            }
            int index1 = index+1;
            int index2 = index+4;
            
            System.out.println("index= "+index);
            String found = input.substring(index+1, index+4);
            System.out.println("index+1= "+ index1);
            System.out.println("index+4= "+ index2);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
            System.out.println("index after updating " + index);
                  
        }
}
   public void test() 
   {
    //no code yet
    // findAbc("abcabcabcabca");
     findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
   }

}
