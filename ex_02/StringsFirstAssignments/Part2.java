
/**
 * Write a description of Part2 here.
 * 
 * @author (Tingting Hu) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna,String startCodon,String stopCodon )
    {
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1)
        {
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
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
            if(dna.equals(dna.toUpperCase()))
            {
                return newString.toUpperCase();
            }
            else
                return newString.toLowerCase();
        
        }
        else 
            return "";
        
        
    }

    
    public void testSimpleGene()
    {
        String dna1 = "AATTGG";
        System.out.println("orignal string = " + dna1);
        System.out.println("DNA = " + findSimpleGene(dna1,"ATG","TAA"));
        String dna2 = "AGGTAA";
        System.out.println("orignal string = " + dna2);
        System.out.println("DNA = " + findSimpleGene(dna2,"ATG","TAA"));
        String dna3 = "ATGTGG";
        System.out.println("orignal string = " + dna3);
        System.out.println("DNA = " + findSimpleGene(dna3,"ATG","TAA"));
        String dna4 = "ATGGATTAA";
        System.out.println("orignal string = " + dna4);
        System.out.println("DNA = " + findSimpleGene(dna4,"ATG","TAA"));
        String dna5 = "GTATGGATAA";
        System.out.println("orignal string = " + dna5);
        System.out.println("DNA = " + findSimpleGene(dna5,"ATG","TAA"));
        String dna6 = "atggattaa";
        System.out.println("orignal string = " + dna6);
        System.out.println("DNA = " + findSimpleGene(dna6,"atg","taa"));
        
        
        
    }
    
    

}
