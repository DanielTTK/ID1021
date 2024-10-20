import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {
    Area[] areas;
    int max = 10000;

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

    public Zip(String file) {
        this.areas = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                areas[i++] = new Area(code, row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public static void collisions(int mod) {
        Zip zip = new Zip("postnummer.csv");
        Area[] area = zip.areas;

        Integer[] keys = new Integer[zip.max];
        for (int i = 0; i < zip.max; i++) {
            keys[i] = area[i].getPostnr();
        }

        int mx = 14;
        int[] data = new int[mod];
        int[] cols = new int[mx];
        // keys[] are the zip codes
        for (int i = 0; i < zip.max; i++) {
            Integer index = keys[i] % mod;
            data[index]++;
        }
        for (int i = 0; i < mod; i++) {
            if (data[i] < mx)
                cols[data[i]]++;
        }
        System.out.print(mod + ": ");
        for (int i = 1; i < mx; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public static void collisionsLinear(int mod) {
        Zip zip = new Zip("postnummer.csv");
        Area[] area = zip.areas;

        Integer[] keys = new Integer[zip.max];
        for (int i = 0; i < zip.max; i++) {
            keys[i] = area[i].getPostnr();
        }

        // int mx = 14;
        int probetime = 0;
        Integer[] data = new Integer[mod];
        // Integer[] cols = new Integer[mx];
        // keys[] are the zip codes
        for (int i = 0; i < zip.max; i++) {
            Integer index = keys[i] % mod;
            while (data[index] != null) {
                index = (index + 1) % mod; // Linear probing
                probetime++;
            }
            data[index] = keys[i]; // Insert the element
        }

        System.out.println("Mod: " + mod + " Probed: " + probetime);
    }

    public static boolean linear_search(Area[] array, Integer key) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] != null && array[index].getPostnr().equals(key)) {
                return true;
            }
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

    public static long bench(int mod) {
        long t0 = System.nanoTime();
        collisions(mod);
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
        int[] sizes = { 12345, 13513, 13600, 14000, 17389 }; // Sizes is an array of n values.

        // JIT warmup

        for (int n : sizes) {
            // long min1 = Long.MAX_VALUE;

            collisionsLinear(n);
            for (int i = 0; i < 1; i++) {
                // long t1 = bench(n);
                // if (t1 < min1)
                // min1 = t1;
            }
            // System.out.println("n: " + n + " time: " + min1 + "ratio: " + ((min2 + min1)
            // / min1));
            // System.out.println("(" + n + ", " + min1 + ")");
        }
    }
}