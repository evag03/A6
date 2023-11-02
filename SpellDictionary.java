import java.io.File;
import java.io.FileNotFoundException;
import java.io.*; 
import java.util.*;

/**
 * Implements the class that constructs the dictionary and will hold the nearMisses method. 
 * @attribute dictionary (HashSet<String>) The dictionary being referred to by the input
 */
public class SpellDictionary implements SpellingOperations {

  public HashSet<String> dictionary;

    /**
     * Constructor for the dictionary that contains the words to be compared with the input.
     */
    public SpellDictionary() {
      this.dictionary = new HashSet<>();
      String filename = "words.txt";
      Scanner file = null;

      try {
        file = new Scanner(new File(filename));
      } catch (FileNotFoundException e) {
        System.err.println("Cannot locate file.");
        System.exit(-1);
      }
      
      this.dictionary = new HashSet<String>();

      while (file.hasNextLine()) {
        String line = file.nextLine();
        this.dictionary.add(line);
      }
      file.close();

    }

    /**
     * Checks to see if the word entered is listed in the dictionary.
     * @param query String The word being input.
     * @return True if the word is in the dictionary, false if not.
     */
    
    public boolean isListed(String query) {
      if (dictionary.contains(query)) {
        return true;
      }
      else {
        return false;
      }      
    }

    /**
     * Method for all the ways the program will adjust words to compare them to words in the dictionary.
     * @param query String The word being input.
     * @return possibleCorrections (ArrayList<String>) A list of the words that were created by the near misses that also exist in the dictionary.
     */
    
    public ArrayList<String> nearMisses(String query) {
      query = query.toLowerCase();
      String[] withPuncutation = query.split("");
      ArrayList<String> noPuncutation = new ArrayList<>();

      for (int i = 0; i < withPuncutation.length; i++) {
        if (withPuncutation[i] == "," || withPuncutation[i] == "." || withPuncutation[i] == "!" || withPuncutation[i] == "?") {
          continue;
        }
        else {
          noPuncutation.add(withPuncutation[i]);
        }
      }
      query = String.join("", noPuncutation);
      ArrayList<String> possibleCorrections = new ArrayList<String>(); 

      //Deletions:
      // Program indexes through word, looking at one letter at a time. For each letter of the word:
      // E.g.: catttle -> cattle

      for (int i = 0; i < query.length(); i++) {
        String[] separatedWord = query.split("");
        ArrayList<String> characters = new ArrayList<>(Arrays.asList(separatedWord));

        characters.remove(i);
        String checkForThis = String.join("", characters);
          
        if (isListed(checkForThis)) {
          possibleCorrections.add(checkForThis);
        }
      }

      // Insertions:
      // Insert one letter into the word at any point. (26*(n+1) possibilities for a word of length n)
      // E.g.: catle -> cattle
        
      String alphabet = new String("abcdefghijklmnopqrstuvwxyz");
      String[] separatedAlphabet = alphabet.split("");
      ArrayList<String> alphabetList = new ArrayList<>(Arrays.asList(separatedAlphabet));

      for (int i = 0; i <= query.length(); i++) {
        String[] separatedWord = query.split("");
        ArrayList<String> characters = new ArrayList<>(Arrays.asList(separatedWord));

        for (int j = 0; j < alphabetList.size(); j++) {
          characters.add(i, alphabetList.get(j));
          String checkForThis = String.join("", characters);
            
          if (isListed(checkForThis)) {
            // Add to possibleCorrections ArrayList
            possibleCorrections.add(checkForThis);
          }
          characters.remove(i);
        }
      }

      // Substitutions
      // Replace one character with another. (25*n possibilities for a word of length n)
      // E.g.: caxtle -> cattle

      for (int i = 0; i < query.length(); i++) {
        String[] separatedWord = query.split("");
        ArrayList<String> characters = new ArrayList<>(Arrays.asList(separatedWord)); 
        alphabet = new String("abcdefghijklmnopqrstuvwxyz");
        separatedAlphabet = alphabet.split("");
        alphabetList = new ArrayList<>(Arrays.asList(separatedAlphabet));
            
        for (int j = 0; j < alphabetList.size(); j++) {
          characters.set(i, alphabetList.get(j));
          String checkForThis = String.join("", characters);

          if (isListed(checkForThis)) {
            possibleCorrections.add(checkForThis);
          }
        }
      }

        // Transpositions
        // Swap two adjacent characters. (n-1 possibilities for a word of length n)
        // E.g.: cattel -> cattle

        for (int i = 0; i < query.length() - 1; i++) {
          String[] separatedWord = query.split("");
          ArrayList<String> characters = new ArrayList<>(Arrays.asList(separatedWord));
          String replaceWithThis = characters.get(i);
          characters.set(i, characters.get(i+1));
          characters.set(i+1, replaceWithThis);
          String checkForThis = String.join("", characters);
            
          if (isListed(checkForThis)) {
            possibleCorrections.add(checkForThis);
          }
        }

        // Splits
        // Divide the word into two legal words. (n-1 possibilities for a word of length n) -- for this kind of near miss, the pair of words together should be recorded as a single entry, with a space between them. E.g.: cattell -> cat tell
          
        for (int i = 0; i < query.length() - 1; i++) {
          String firstPossibleWord = query.substring(0, i+1);
          String secondPossibleWord = query.substring(i+1, query.length());
           
          if (isListed(firstPossibleWord) && isListed(secondPossibleWord)) {
            possibleCorrections.add(firstPossibleWord + " " + secondPossibleWord);
          }    
        }
          
      return possibleCorrections;
    }
    
  public static void main(String[] args) {
    SpellDictionary spellChecker = new SpellDictionary();
    System.out.println(spellChecker.nearMisses("bderidden").toString());

  }
}  

