package www.rawven.learn;

public class SimilarityCalculator {
    public double calculateSimilarity(String original, String plagiarized) {
        int lcsLength = longestCommonSubsequence(original, plagiarized);
        return (2.0 * lcsLength) / (original.length() + plagiarized.length());
    }

    private int longestCommonSubsequence(String s1, String s2) {
    int[] prev = new int[s2.length() + 1];
    int[] curr = new int[s2.length() + 1];

    for (int i = 1; i <= s1.length(); i++) {
        for (int j = 1; j <= s2.length(); j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                curr[j] = prev[j - 1] + 1;
            } else {
                curr[j] = Math.max(prev[j], curr[j - 1]);
            }
        }
        // 交换当前行和前一行
        int[] temp = prev;
        prev = curr;
        curr = temp;
    }
    return prev[s2.length()];
}
}