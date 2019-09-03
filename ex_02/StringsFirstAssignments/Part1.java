
/**
 * Write a description of Part1 here.
 * 
 * @author (Tingting Hu) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene(String dna)
    {
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
        {
            return "";
        }
        int stopIndex = dna.indexOf("TAA",startIndex+3);
        if(stopIndex == -1)
        {
            return "";
        }
        int different;
        different = stopIndex - startIndex;
        String newString = null;
        if(different%3 == 0)
        {
            
            newString = dna.substring(startIndex, stopIndex+3);
            return newString;
            
        }
        else 
        return "";
        
        
    }
    
    public void testSimpleGene()
    {
        String dna1 = "AATTGG";
        System.out.println("orignal string = " + dna1);
        System.out.println("DNA = " + findSimpleGene(dna1));
        String dna2 = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("orignal string = " + dna2);
        System.out.println("DNA = " + findSimpleGene(dna2));
        String dna3 = "ATGTGG";
        System.out.println("orignal string = " + dna3);
        System.out.println("DNA = " + findSimpleGene(dna3));
        String dna4 = "ATGGATTAA";
        System.out.println("orignal string = " + dna4);
        System.out.println("DNA = " + findSimpleGene(dna4));
        String dna5 = "GTATGGATAA";
        System.out.println("orignal string = " + dna5);
        System.out.println("DNA = " + findSimpleGene(dna5));
        
        
    }
    
    

}
