public class ZipB {
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

    public ZipB(String file) {
        this.areas = new Area[this.max];
        // Same logic for loading areas from file
    }

    // Linear Probing Approach
    public static void linearProbingCollisions(int mod) {
        ZipB zip = new ZipB("postnummer.csv");
        Area[] area = zip.areas;

        Integer[] keys = new Integer[zip.max];
        for (int i = 0; i < zip.max; i++) {
            if (area[i] != null) { // Check for null before accessing area[i]
                keys[i] = area[i].getPostnr();
            }
        }

        // Create an array for linear probing (no buckets)
        Area[] data = new Area[mod];

        for (int i = 0; i < zip.max; i++) {
            if (keys[i] != null) { // Check for null before inserting into data array
                Integer index = keys[i] % mod;
                while (data[index] != null) {
                    index = (index + 1) % mod; // Linear probing
                }
                data[index] = area[i]; // Insert the element
            }
        }

        // Lookup and count how many checks are made
        int keyToFind = keys[0]; // Example lookup key (now checked for null)
        int checks = 0;
        Integer index = keyToFind % mod;

        while (data[index] != null) {
            checks++;
            if (data[index].getPostnr().equals(keyToFind)) {
                break; // Found the element
            }
            index = (index + 1) % mod; // Continue probing
        }
        System.out.println("Linear probing checks: " + checks);
    }

    public static void main(String[] args) {
        int[] sizes = { 12345, 13513, 13600, 14000, 17389 };

        // Run bucket collision method
        for (int n : sizes) {
            long t1 = System.nanoTime();
            // bucketCollisions(n);
            long t2 = System.nanoTime();
            System.out.println("Bucket collision time for size " + n + ": " + (t2 - t1) + " ns");
        }

        // Run linear probing method
        for (int n : sizes) {
            long t1 = System.nanoTime();
            linearProbingCollisions(n);
            long t2 = System.nanoTime();
            System.out.println("Linear probing time for size " + n + ": " + (t2 - t1) + " ns");
        }
    }
}
