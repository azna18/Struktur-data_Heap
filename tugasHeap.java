import java.util.*;
import java.io.*;
 
public class heap {
 
    static int[]    minId   = new int[1000];
    static String[] minNama = new String[1000];
    static int      minSize = 0;
 
    static int[]    maxId   = new int[1000];
    static String[] maxNama = new String[1000];
    static int      maxSize = 0;
 
    static void tambahMin(int id, String nama) {
        minId[minSize]   = id;
        minNama[minSize] = nama;
        int i = minSize;
        while (i > 0 && minId[i] < minId[(i-1)/2]) {
            int t = minId[i]; minId[i] = minId[(i-1)/2]; minId[(i-1)/2] = t;
            String s = minNama[i]; minNama[i] = minNama[(i-1)/2]; minNama[(i-1)/2] = s;
            i = (i-1)/2;
        }
        minSize++;
    }
 
    static void hapusMin(int id) {
        int idx = -1;
        for (int i = 0; i < minSize; i++)
            if (minId[i] == id) { idx = i; break; }
 
        if (idx == -1) { System.out.println("✖ ID " + id + " tidak ditemukan di MinHeap."); return; }
 
        System.out.println("✔ Dihapus dari MinHeap: [" + id + ", " + minNama[idx] + "]");
        minSize--;
        minId[idx] = minId[minSize]; minNama[idx] = minNama[minSize];
        int i = idx;
        while (true) {
            int terkecil = i, kiri = 2*i+1, kanan = 2*i+2;
            if (kiri  < minSize && minId[kiri]  < minId[terkecil]) terkecil = kiri;
            if (kanan < minSize && minId[kanan] < minId[terkecil]) terkecil = kanan;
            if (terkecil == i) break;
            int t = minId[i]; minId[i] = minId[terkecil]; minId[terkecil] = t;
            String s = minNama[i]; minNama[i] = minNama[terkecil]; minNama[terkecil] = s;
            i = terkecil;
        }
    }
 
    static void tambahMax(int id, String nama) {
        maxId[maxSize]   = id;
        maxNama[maxSize] = nama;
        int i = maxSize;
        while (i > 0 && maxId[i] > maxId[(i-1)/2]) {
            int t = maxId[i]; maxId[i] = maxId[(i-1)/2]; maxId[(i-1)/2] = t;
            String s = maxNama[i]; maxNama[i] = maxNama[(i-1)/2]; maxNama[(i-1)/2] = s;
            i = (i-1)/2;
        }
        maxSize++;
    }
 
    static void hapusMax(int id) {
        int idx = -1;
        for (int i = 0; i < maxSize; i++)
            if (maxId[i] == id) { idx = i; break; }
 
        if (idx == -1) { System.out.println("✖ ID " + id + " tidak ditemukan di MaxHeap."); return; }
 
        System.out.println("✔ Dihapus dari MaxHeap: [" + id + ", " + maxNama[idx] + "]");
        maxSize--;
        maxId[idx] = maxId[maxSize]; maxNama[idx] = maxNama[maxSize];
        int i = idx;
        while (true) {
            int terbesar = i, kiri = 2*i+1, kanan = 2*i+2;
            if (kiri  < maxSize && maxId[kiri]  > maxId[terbesar]) terbesar = kiri;
            if (kanan < maxSize && maxId[kanan] > maxId[terbesar]) terbesar = kanan;
            if (terbesar == i) break;
            int t = maxId[i]; maxId[i] = maxId[terbesar]; maxId[terbesar] = t;
            String s = maxNama[i]; maxNama[i] = maxNama[terbesar]; maxNama[terbesar] = s;
            i = terbesar;
        }
    }
 
    static void tampilAscending() {
        if (minSize == 0) { System.out.println("Heap kosong!"); return; }
        int[]    tmpId   = Arrays.copyOf(minId,   minSize);
        String[] tmpNama = Arrays.copyOf(minNama, minSize);
        int      tmpSize = minSize;
 
        System.out.printf("%n%-5s %-8s %s%n", "No", "ID", "Nama");
        System.out.println("-".repeat(30));
        for (int no = 1; no <= minSize; no++) {
            int idx = 0;
            for (int i = 1; i < tmpSize; i++)
                if (tmpId[i] < tmpId[idx]) idx = i;
            System.out.printf("%-5d %-8d %s%n", no, tmpId[idx], tmpNama[idx]);
            tmpId[idx] = tmpId[tmpSize-1]; tmpNama[idx] = tmpNama[tmpSize-1]; tmpSize--;
        }
    }
 
    static void tampilDescending() {
        if (maxSize == 0) { System.out.println("Heap kosong!"); return; }
        int[]    tmpId   = Arrays.copyOf(maxId,   maxSize);
        String[] tmpNama = Arrays.copyOf(maxNama, maxSize);
        int      tmpSize = maxSize;
 
        System.out.printf("%n%-5s %-8s %s%n", "No", "ID", "Nama");
        System.out.println("-".repeat(30));
        for (int no = 1; no <= maxSize; no++) {
            int idx = 0;
            for (int i = 1; i < tmpSize; i++)
                if (tmpId[i] > tmpId[idx]) idx = i;
            System.out.printf("%-5d %-8d %s%n", no, tmpId[idx], tmpNama[idx]);
            tmpId[idx] = tmpId[tmpSize-1]; tmpNama[idx] = tmpNama[tmpSize-1]; tmpSize--;
        }
    }
 
    static void loadCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // skip header
 
            while ((line = br.readLine()) != null) {
                String[] data;
 
                // support ; atau ,
                if (line.contains(";"))
                    data = line.split(";");
                else
                    data = line.split(",");
 
                int id      = Integer.parseInt(data[0].trim());
                String nama = data[1].trim();
 
                tambahMin(id, nama);
                tambahMax(id, nama);
            }
 
            System.out.println("✔ Semua data berhasil dimasukkan ke Min-Heap & Max-Heap!");
 
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilih;
 
        do {
            System.out.println("\n=== MENU HEAP (JAVA) ===");
            System.out.println("1. Load Data dari CSV");
            System.out.println("2. Tambah Data");
            System.out.println("3. Tampil ASCENDING  (Min-Heap)");
            System.out.println("4. Tampil DESCENDING (Max-Heap)");
            System.out.println("5. Hapus dari Min-Heap");
            System.out.println("6. Hapus dari Max-Heap");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
 
            switch (pilih) {
                case 1:
                    loadCSV("D:/kuliah/semester 4/Struktur Data/praktikum/#7 Heap/data100.csv");
                    break;
 
                case 2:
                    System.out.print("ID   : ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nama : ");
                    String nama = sc.nextLine();
                    tambahMin(id, nama);
                    tambahMax(id, nama);
                    System.out.println("✔ [" + id + ", " + nama + "] berhasil ditambahkan!");
                    break;
 
                case 3:
                    tampilAscending();
                    break;
 
                case 4:
                    tampilDescending();
                    break;
 
                case 5:
                    System.out.print("Masukkan ID: ");
                    hapusMin(sc.nextInt());
                    break;
 
                case 6:
                    System.out.print("Masukkan ID: ");
                    hapusMax(sc.nextInt());
                    break;
 
                case 0:
                    System.out.println("Program selesai.");
                    break;
 
                default:
                    System.out.println("Pilihan tidak valid!");
            }
 
        } while (pilih != 0);
 
        sc.close();
    }
}
