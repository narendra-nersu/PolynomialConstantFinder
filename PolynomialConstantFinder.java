import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class PolynomialConstantFinder{
    static class Point {
        int x;
        BigInteger y;
        Point(int x, BigInteger y) {
            this.x = x;
            this.y = y;
        }
    }
    static BigInteger findConstant(List<Point> points) {
        BigInteger result = BigInteger.ZERO;
        for (int i=0;i<points.size();i++) {
            Point pi = points.get(i);
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            for (int j = 0; j < points.size(); j++) {
                if (i == j) continue;
                Point pj = points.get(j);
                numerator = numerator.multiply(BigInteger.valueOf(-pj.x));
                denominator = denominator.multiply(
                        BigInteger.valueOf(pi.x - pj.x)
                );
            }
            result = result.add(
                    pi.y.multiply(numerator).divide(denominator)
            );
        }
        return result;
    }
    static BigInteger processFile(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int k = 0;
        List<Point> points = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.contains("\"k\"")) {
                k = Integer.parseInt(line.replaceAll("[^0-9]", ""));
            }
            if (line.matches("\"\\d+\".*")) {
                int x = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                String baseLine = br.readLine().trim();
                String valueLine = br.readLine().trim();
                int base = Integer.parseInt(baseLine.replaceAll("[^0-9]", ""));
                String value = valueLine.split(":")[1]
                        .replace("\"", "")
                        .replace(",", "")
                        .trim();
                BigInteger y = new BigInteger(value, base);
                points.add(new Point(x, y));
                if (points.size() == k) break;
            }
        }
        br.close();
        return findConstant(points);
    }

    public static void main(String[] args) throws Exception {

        BigInteger result1 = processFile("input1.json");
        BigInteger result2 = processFile("input2.json");
        
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"testCase1\": {\n");
        json.append("    \"secret\": \"").append(result1).append("\"\n");
        json.append("  },\n");
        json.append("  \"testCase2\": {\n");
        json.append("    \"secret\": \"").append(result2).append("\"\n");
        json.append("  }\n");
        json.append("}");

        System.out.println(json);
        
        FileWriter fw = new FileWriter("output.json");
        fw.write(json.toString());
        fw.close();
    }
}
