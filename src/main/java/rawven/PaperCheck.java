package rawven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;

public class PaperCheck {

    public static void main(String[] args) {
        // 检查命令行参数的数量
        if (args.length != 3) {
            System.out.println("用法: java PaperCheck <原始文件路径> <抄袭文件路径> <输出文件路径>");
            return; // 退出程序
        }

        // 获取文件路径
        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String outputFilePath = args[2];
        FileHandler fileHandler = new FileHandler();
        SimilarityCalculator similarityCalculator = new SimilarityCalculator();

        try {
            String originalText = fileHandler.readFile(originalFilePath);
            String plagiarizedText = fileHandler.readFile(plagiarizedFilePath);
            double similarity = similarityCalculator.calculateSimilarity(originalText, plagiarizedText);
            // 保留前两位小数
            DecimalFormat df = new DecimalFormat("#.##");
            String result = "相似度: " + df.format(similarity);
            fileHandler.writeFile(outputFilePath, result);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}