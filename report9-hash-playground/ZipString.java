import java.io.BufferedReader;
import java.io.FileReader;

public class ZipString {
    Area[] areas;
    int max = 10000;

    public class Area {
        String postnr = null;
        String stad = null;
        Integer ppl = null;

        Area(String postnr, String stad, Integer ppl) {
            this.postnr = postnr;
            this.stad = stad;
            this.ppl = ppl;
        }

        public String getPostnr() {
            return postnr;
        }
    }

    public ZipString(String file) {
        this.areas = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                areas[i++] = new Area(row[0], row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public static boolean linear_search_String(Area[] array, String key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] != null && array[index].getPostnr().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean binary_search_String(Area[] array, String key) { // unecessary impl.
        int first = 0;
        int last = array.length - 1;
        int index = 0;
        while (true) {
            index = first + ((last - first) / 2);
            if (array[index] != null) {
                if (array[index].getPostnr().equals(key)) {
                    return true;
                }
                if (array[index].getPostnr().compareTo(key) > 0 && index < last) {
                    first = index;
                    continue;
                }
                if (array[index].getPostnr().compareTo(key) < 0 && index > first) {
                    last = index;
                    continue;
                }
            }
            return false;
        }
    }

    public static long bench(Area[] array, String key) {
        long t0 = System.nanoTime();
        linear_search_String(array, key);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static long bench2(Area[] array, String key) {
        long t0 = System.nanoTime();
        binary_search_String(array, key);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void main(String[] args) {
        int k = 20;

        int[] sizes = new int[k]; // Sizes is an array of n values.

        for (int i = 0; i < k; i++) {
            sizes[i] = (int) (Math.pow(2, i) * 100);
        }

        ZipString zip = new ZipString("postnummer.csv");
        Area[] area = zip.areas;

        // JIT warmup
        bench(area, "111 15");
        bench(area, "985 99");

        bench2(area, "111 15");
        bench2(area, "985 99");

        for (int n : sizes) {
            long min1 = Long.MAX_VALUE;
            long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t1 = bench(area, "985 99");
                long t2 = bench2(area, "985 99");
                if (t1 < min1)
                    min1 = t1;
                if (t2 < min2) {
                    min2 = t2;
                }
            }
            System.out.println("n: " + n + " time1: " + min1 + " time2: " + min2 + " diff: " + (min2 - min1));
            // System.out.println("(" + n + ", " + min + ")");
        }
    }
}