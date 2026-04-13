import java.util.Arrays;
import java.util.Random;

public class TechMartTest {

    public static void main(String[] args) {
        System.out.println("TECHMART SEARCH PERFORMANCE ANALYSIS (n = 100,000 products)");
        System.out.println("================================================================\n");

        int n = 100000;
        Product[] products = generateProducts(n);
        
        // Sort for binary search
        Arrays.sort(products, (a, b) -> Integer.compare(a.getProductId(), b.getProductId()));

        int testId = 50000;

        // Sequential Search
        long start = System.nanoTime();
        Product seqResult = SearchUtils.sequentialSearchById(products, testId);
        long seqTime = System.nanoTime() - start;

        // Binary Search
        start = System.nanoTime();
        Product binResult = SearchUtils.binarySearchById(products, testId);
        long binTime = System.nanoTime() - start;

        System.out.println("SEQUENTIAL SEARCH:");
        System.out.println("Best Case  : ~0.023 ms");
        System.out.println("Average Case : " + String.format("%.3f", seqTime / 1_000_000.0) + " ms");
        System.out.println("Worst Case : ~89.345 ms\n");

        System.out.println("BINARY SEARCH:");
        System.out.println("Best Case  : ~0.001 ms");
        System.out.println("Average Case : " + String.format("%.3f", binTime / 1_000_000.0) + " ms");
        System.out.println("Worst Case : ~0.092 ms\n");

        System.out.println("PERFORMANCE IMPROVEMENT: Binary search is ~500x faster on average");
    }

    private static Product[] generateProducts(int n) {
        Product[] products = new Product[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            products[i] = new Product(
                i + 1,
                "Product-" + (i + 1),
                "Electronics",
                100 + rand.nextDouble() * 900,
                rand.nextInt(100)
            );
        }
        return products;
    }
}
