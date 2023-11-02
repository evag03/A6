import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;


/**
 * Interacts with the user by taking in the words input by the user and printing out the correct, incorrect, and suggested words.
 */
public class SpellChecker {

  public static void main(String[] args) {
        
    SpellDictionary spellChecker = new SpellDictionary();
    spellChecker.dictionary = new HashSet<String>();

    String filename = "words.txt";
    Scanner file = null;
      
    try {
      file = new Scanner(new File("words.txt"));
    }
    catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }

    while (file.hasNextLine()) {
      String line = file.nextLine();
      spellChecker.dictionary.add(line);
    }
    file.close();


    if (args.length > 0) {
      ArrayList<String> line = new ArrayList<>();
            
      for (int i = 0; i < args.length; i++) {
        line.add(args[i]);
      }

      for (int i = 0; i < line.size(); i++) {
        if (spellChecker.isListed(line.get(i))) {
            System.out.println("'" + line.get(i) + "'" + " is spelled correctly.");
        }
                
        else {
          System.out.println("Not found: " + line.get(i));
          System.out.print("Suggestions: ");
          for (int j = 0; j < spellChecker.nearMisses(line.get(i)).size(); j++) {
            System.out.print(spellChecker.nearMisses(line.get(i)).get(j) + " ");
          }
          System.out.println("");
        }
      }
    }
       
      
    else {
      Scanner scanner = new Scanner(System.in);
      HashSet<String> checkedWords = new HashSet<>();

      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        String[] separatedLine = line.split(" ");
        ArrayList<String> lineList = new ArrayList<>(Arrays.asList(separatedLine));
            
        for (int i = 0; i < lineList.size(); i++) {
          if (spellChecker.isListed(lineList.get(i))) {
            continue;
          }

          else if (!checkedWords.contains(lineList.get(i))) {
            System.out.println("Possibly mispelled: " + lineList.get(i));
            System.out.print("Suggested corrections: ");
              for (int j = 0; j < spellChecker.nearMisses(lineList.get(i)).size(); j++) {
                System.out.print(spellChecker.nearMisses(lineList.get(i)).get(j) + " ");
              }
            System.out.println("");
            checkedWords.add(lineList.get(i));
          }
          
        }
          
      }
    }      
  }
}