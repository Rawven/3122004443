package www.rawven.learn;

import java.io.IOException;

import static java.lang.Double.NaN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @org.junit.Test
    public void testExactMatch() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("hello", "hello");
        assertEquals(1.0, similarity, 0.001);
    }

    @org.junit.Test
    public void testNoSimilarity() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("hello", "world");
        assertEquals(0.2, similarity, 0.001);
    }

    @org.junit.Test
    public void testPartialMatch() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("hello", "helicopter");
        assertEquals(0.5333, similarity, 0.001);
    }

    @org.junit.Test
    public void testSameLengthDifferentContent() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("abcd", "efgh");
        assertEquals(0.0, similarity, 0.001);
    }

    @org.junit.Test
    public void testDifferentLengthWithCommonSubsequence() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("abcde", "ace");
        assertEquals(0.75, similarity, 0.001);
    }

    @org.junit.Test
    public void testEmptyStrings() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("", "");
        assertEquals(NaN, similarity, 0.001);
    }

    @org.junit.Test
    public void testOneEmptyString() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("abc", "");
        assertEquals(0.0, similarity, 0.001);
    }

    @org.junit.Test
    public void testSameCharactersDifferentOrder() {
        SimilarityCalculator calc = new SimilarityCalculator();
        double similarity = calc.calculateSimilarity("abc", "cba");
        assertEquals(0.3333, similarity, 0.001);
    }

    @org.junit.Test
    public void testFileHandlerReadWrite() throws IOException {
        FileHandler fileHandler = new FileHandler();
        String testFilePath = "test.txt";

        fileHandler.writeFile(testFilePath, "Hello, World!");
        String content = fileHandler.readFile(testFilePath);
        assertTrue(content.contains("Hello, World!"));
    }

    @org.junit.Test
    public void testFileHandlerAppend() throws IOException {
        FileHandler fileHandler = new FileHandler();
        String testFilePath = "test.txt";

        fileHandler.writeFile(testFilePath, "First line.");
        fileHandler.writeFile(testFilePath, "Second line.");
        String content = fileHandler.readFile(testFilePath);
        assertTrue(content.contains("First line."));
        assertTrue(content.contains("Second line."));
    }
}
