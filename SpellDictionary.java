import java.io.File;
import java.io.FileNotFoundException;
import java.io.*; 
import java.util.*;

public class SpellDictionary implements SpellingOperations {

    private HashSet<String> dictionary;


    public SpellDictionary() {
      HashSet<String> dictionary = new HashSet<>();

    }

    public boolean isListed(String query) {
      if (dictionary.contains(query)) {
        return true;
      }
      else {
        return false;
      }      
    }

// You will provide a method to check whether the dictionary containsWord for a particular spelling, returning true or false.
    // public boolean containsWord(String query) {
    //   if query = 

    //}



// You will also provide a nearMisses method that will return an ArrayList of correctly spelled words that are exactly one edit away from a given incorrect word spelling. It will do this by constructing all possible near misses, checking them against the dictionary, and returning any that are real words, without duplicates.



// Substitutions
// Replace one character with another. (25*n possibilities for a word of length n)
// E.g.: caxtle -> cattle

// Transpositions
// Swap two adjacent characters. (n-1 possibilities for a word of length n)
// E.g.: cattel -> cattle

// Splits
// Divide the word into two legal words. (n-1 possibilities for a word of length n) -- for this kind of near miss, the pair of words together should be recorded as a single entry, with a space between them. E.g.: cattell -> cat tell

    public ArrayList<String> nearMisses(String query) {
        ArrayList<String> possibleCorrections = new ArrayList<String>(); 
        //Deletions:
        // Program indexes through word, looking at one letter at a time. For each letter of the word:
        // E.g.: catttle -> cattle
        for (int i = 0; i < query.length(); i++) {

          String[] separatedWord = query.split("");
          ArrayList<String> characters = new ArrayList<>(Arrays.asList(separatedWord));
          // Program removes letter
          characters.remove(i);
          
          String checkForThis = String.join("", characters);
          // System.out.println(checkForThis);
          // Checks if word without letter matches a preexisting word in the dictionary of words
          // If it does:
          if (isListed(checkForThis)) {
            // Add to possibleCorrections ArrayList
            possibleCorrections.add(checkForThis);
          }
            // Continue indexing through word
          // If it does not:
            //Nothing happens; continue indexing through word
        }

        System.out.println("Deletions test completed. Beginning Insertions test.");

        // Insertions:
        // Insert one letter into the word at any point. (26*(n+1) possibilities for a word of length n)
        // E.g.: catle -> cattle
        String alphabet = new String("abcdefghijklmnopqrstuvwxyz");
        String[] separatedAlphabet = alphabet.split("");
        ArrayList<String> alphabetList = new ArrayList<>(Arrays.asList(separatedAlphabet));

        for (int i = 0; i < query.length(); i++) {
          
          //for each letter in the word, run through the entire alphabet and put one letter before and after each letter. we can use an iterator for this.
          //(26*(n+1)) 
          String[] separatedWord = query.split("");
        
          ArrayList<String> characters = new ArrayList<>(Arrays.asList(separatedWord));

            // Iterates through each letter in alphabet Array
            // Adds letter to beginning of word
            // i.e 'a' + 'lpaca'
            
            // 'Outer loop will be a position, won't update until the end of the loop, so it could check for every letter within ' - Halie
            // 'Maybe do Substitution first?' - Halie
            // for (int j = 0; j < ) {
            //   characters.add(alphabet[i]);
            // }


            if (isListed("uh")) {
              // Add to possibleCorrections ArrayList
              possibleCorrections.add("uhm");
          }
        }
        
          System.out.println("Insertions test completed. Beginning Substitutions test.");

          // Substitutions
          // Replace one character with another. (25*n possibilities for a word of length n)
          // E.g.: caxtle -> cattle
          for (int i = 0; i < query.length(); i++) {
            //for each letter in the word, run through the entire alphabet and switch one letter with another.
            //(26*(n+1)) 
             String[] separatedWord = query.split("");
             ArrayList<String> characters = new ArrayList<>(Arrays.asList(separatedWord));
             
             alphabet = new String("abcdefghijklmnopqrstuvwxyz");
             separatedAlphabet = alphabet.split("");
             alphabetList = new ArrayList<>(Arrays.asList(separatedAlphabet));
             
            for (int j = 0; j < alphabetList.size(); j++) {
              characters.set(i, alphabetList.get(j));
              String checkForThis = String.join("", characters);

              if (isListed(checkForThis)) {
                // Add to possibleCorrections ArrayList
                possibleCorrections.add(checkForThis);
              }

              System.out.println(checkForThis);
            }
            
          }
          
          System.out.println("Substitutions test completed. Beginning Transpositions test.");

        return possibleCorrections;

    }

    



    
  public static void main(String[] args) {

    String filename = (args.length > 0) ? args[0] : "words.txt";
    SpellDictionary spellChecker = new SpellDictionary();
    Scanner file = null;

    try {
      file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }
    
    spellChecker.dictionary = new HashSet<String>();

    while (file.hasNextLine()) {
      String line = file.nextLine();
      spellChecker.dictionary.add(line);
    }
    file.close();

    //spellChecker.nearMisses("beeetle");
    String corrections = spellChecker.nearMisses("cax").toString();
    System.out.println(corrections);

  }
}  

