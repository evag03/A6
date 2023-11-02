/**
 * Tests the various methods of near misses.
 * @attribute spellDict (SpellDictionary) The dictionary of words being compared to the query in the tests.
 */
public class Main {

    SpellDictionary spellDict = new SpellDictionary();
    
    /**
     * Tests the deletions near miss.
     */
    public void deletionsTest() { 
        String[] test1 = {"lemonn"};
        boolean inCorrections = spellDict.nearMisses("lemonn").contains("lemon");  
        TestCode.runTest("Deletion Test #1: 'lemonn'", spellDict.nearMisses("lemonn").contains("lemon"));
    }

    /**
     * Tests the insertion near miss.
     */
    public void insertionsTest() {        
        TestCode.runTest("Insertions Test: 'mgic'", spellDict.nearMisses("magic").contains("magic"));
    }

    /**
     * Tests the substitutions near miss.
     */
    public void substitutionsTest() {
        TestCode.runTest("Subtitutions Test #1: 'cxt'", spellDict.nearMisses("cat").contains("cat"));
    }

    /**
     * Tests the transposition near miss.
     */
    public void transpositionsTest() {
        String[] test1 = {"bderest"};

        boolean inCorrections = spellDict.nearMisses("bderidden").contains("bedridden");
        
        TestCode.runTest("Transpositions Test #1: 'bderidden'", spellDict.nearMisses("bderidden").contains("bedridden"));
    }
    
    /**
     * Tests the split near miss.
     */
    public void splitTest() {
           String[] test1 = {"lovewriting"};

        boolean inCorrections = spellDict.nearMisses("lovewriting").contains("love writing");
        
        TestCode.runTest("Split Test #1: 'lovewriting'", spellDict.nearMisses("lovewriting").contains("love writing"));
    }

    public static void main(String[] args) {
        Main test = new Main();
        test.deletionsTest();
        test.insertionsTest();
        test.substitutionsTest();
        test.transpositionsTest();
        test.splitTest();
    }
}


