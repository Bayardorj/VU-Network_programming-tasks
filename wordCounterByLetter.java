import java.util.Scanner;

public class wordCounterByLetter {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

       // Enter input
       System.out.print("Enter the string: ");
       String string = scanner.nextLine();

       System.out.print("Enter the starting letter: ");
       char startLetter = scanner.next().charAt(0);

       System.out.print("Enter the ending letter: ");
       char endLetter = scanner.next().charAt(0);

       // Set case-insensitive to scan both lower letter and upper letter
       char lowerStartLetter = Character.toLowerCase(startLetter);
       char lowerEndLetter = Character.toLowerCase(endLetter);

       // Store the string by each word as a separate
       String [] words = string.split("\\s+");
       int count = 0;
       // in for function, i assigned each words of "words" array to "word" variable. 
       for (String word : words) {
        if(!word.isEmpty()) {
            String lowerWord = word.toLowerCase();
        
            if (lowerWord.charAt(0) == lowerStartLetter && lowerWord.charAt(lowerWord.length()-1) == lowerEndLetter) {
                count++;
            }
            
         }

       }
       System.out.println("Number of word that meet requirements: " + count);
       scanner.close();
   } 

}
