import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;



public class SpellChecker {

    public static void main(String[] args) {
        
      //working with the dictionary
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

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            ArrayList<String> lineList = line.split(" ");
        }
      }
      
    
    }
}

//   Input:

//   java SpellChecker qest questt quest

//   Output:

//   Not found:  qest
//   Suggestions:  cest est fest gest hest jest mest nest quest rest test vest yest zest 
//   Not found:  questt
//   Suggestions:  quest quests 
//   'quest' is spelled correctly.