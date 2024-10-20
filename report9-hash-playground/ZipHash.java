import java.io.BufferedReader;
import java.io.FileReader;

public class ZipHash {
    Area[] areas;
    int max = 100000;

    public class Area {
        Integer postnr = null;
        String stad = null;
        Integer ppl = null;

        Area(Integer postnr, String stad, Integer ppl) {
            this.postnr = postnr;
            this.stad = stad;
            this.ppl = ppl;
        }

        public Integer getPostnr() {
            return postnr;
        }
    }

    public ZipHash(String file) {
        this.areas = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                areas[code] = new Area(code, row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public static boolean linear_search(Area[] array, Integer key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] != null && array[index].getPostnr().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hashLookup(Area[] array, Integer key) {
        if (array[key] != null && array[key].getPostnr().equals(key)) {
            System.out.print("hash true");
            return true;
        }
        return false;
    }

    public static boolean binary_search(Area[] array, Integer key) {
        int first = 0;
        int last = array.length - 1;
        int index = 0;
        while (true) {
            index = first + ((last - first) / 2);
            if (array[index] != null) {
                if (array[index].getPostnr().equals(key)) {
                    System.out.print("binary true");
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
            } else {
                continue;
            }
            return false;
        }
    }

    public static long bench(Area[] array, Integer key) {
        long t0 = System.nanoTime();
        hashLookup(array, key);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static long bench2(Area[] array, Integer key) {
        long t0 = System.nanoTime();
        binary_search(array, key);
        long t1 = System.nanoTime();
        return (t1 - t0);
    }

    public static void main(String[] args) {
        int k = 20;

        int[] sizes = new int[k]; // Sizes is an array of n values.

        for (int i = 0; i < k; i++) {
            sizes[i] = (int) (Math.pow(2, i) * 100);
        }

        ZipHash zip = new ZipHash("postnummer.csv");
        Area[] area = zip.areas;

        // JIT warmup

        for (int n : sizes) {
            long min1 = Long.MAX_VALUE;
            long min2 = Long.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                long t1 = bench(area, 98599);
                // long t2 = bench2(area, 98599);
                if (t1 < min1)
                    min1 = t1;
                // if (t2 < min2) {
                // min2 = t2;
                // }
            }
            System.out.println("n: " + n + " linear: " + min1 + " binary: " + min2 + " diff: " + (min2 - min1));
            // System.out.println("(" + n + ", " + min + ")");
        }
    }
}