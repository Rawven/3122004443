import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PaperCheck {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java PaperCheck <original_file_path> <plagiarized_file_path> <output_file_path>");
            return;
        }

        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String outputFilePath = args[2];

        try {
            String originalText = new String(Files.readAllBytes(Paths.get(originalFilePath)));
            String plagiarizedText = new String(Files.readAllBytes(Paths.get(plagiarizedFilePath)));
            
            double similarity = calculateSimilarity(originalText, plagiarizedText);
            
            String result = String.format("%.2f", similarity);
            Files.write(Paths.get(outputFilePath), result.getBytes());
            
            System.out.println("Similarity rate: " + result);
            
        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }

    private static double calculateSimilarity(String original, String plagiarized) {
        int lcsLength = longestCommonSubsequence(original, plagiarized);
        return (double) lcsLength * 2 / (original.length() + plagiarized.length());
    }

    private static int longestCommonSubsequence(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}