import java.util.*;
 
public class HeapProgram {
 
    static List<Integer> maxHeap = new ArrayList<>();
 
    static void heapifyUp(List<Integer> heap, int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(parent) < heap.get(index)) {
                int temp = heap.get(parent);
                heap.set(parent, heap.get(index));
                heap.set(index, temp);
                index = parent;
            } else break;
        }
    }
 
    static void heapifyDown(List<Integer> heap, int index, int size) {
        int largest = index;
        int left  = 2 * index + 1;
        int right = 2 * index + 2;
 
        if (left  < size && heap.get(left)  > heap.get(largest)) largest = left;
        if (right < size && heap.get(right) > heap.get(largest)) largest = right;
 
        if (largest != index) {
            int temp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, temp);
            heapifyDown(heap, largest, size);
        }
    }
 
    static void insertMax(List<Integer> heap, int value) {
        heap.add(value);
        heapifyUp(heap, heap.size() - 1);
    }
 
    static int getMax(List<Integer> heap) {
        if (heap.isEmpty()) return -1;
        return heap.get(0);
    }
 
    static int extractMax(List<Integer> heap) {
        if (heap.isEmpty()) return -1;
        int maxVal = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(heap, 0, heap.size());
        return maxVal;
    }
 
    static void deleteNode(List<Integer> heap, int value) {
        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) == value) { index = i; break; }
        }
        if (index == -1) { System.out.println("Elemen " + value + " tidak ditemukan!"); return; }
        heap.set(index, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        if (index < heap.size()) {
            heapifyUp(heap, index);
            heapifyDown(heap, index, heap.size());
        }
    }
 
    static void printHeap(List<Integer> heap, String label) {
        System.out.print(label + ": [ ");
        for (int x : heap) System.out.print(x + " ");
        System.out.println("]");
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        System.out.println("========================================");
        System.out.println("   MAX-HEAP & MIN-HEAP - INPUT MANUAL   ");
        System.out.println("________________________________________\n");
 
        System.out.print("Masukkan jumlah elemen: ");
        int n = sc.nextInt();
 
        int[] values = new int[n];
        System.out.println("Masukkan " + n + " angka:");
        for (int i = 0; i < n; i++) {
            System.out.print("  Elemen ke-" + (i + 1) + ": ");
            values[i] = sc.nextInt();
        }
 
        System.out.println("\n========================================");
        System.out.println("        MAX-HEAP IMPLEMENTATION           ");
        System.out.println("  (Root selalu menyimpan nilai TERBESAR)  ");
        System.out.println("__________________________________________\n");
 
        for (int v : values) { 
            insertMax(maxHeap, v);
            System.out.print("Insert " + v + " -> ");
            printHeap(maxHeap, "Isi Max-Heap (index 0 = root/terbesar)");
        }
 
        System.out.println("\nElemen terbesar saat ini (root): " + getMax(maxHeap));
 
        System.out.println("Extract Max (ambil & hapus root): " + extractMax(maxHeap));
        printHeap(maxHeap, "Isi heap setelah extract         ");
 
        System.out.print("\nMasukkan elemen yang ingin dihapus: ");
        int del = sc.nextInt();
        deleteNode(maxHeap, del);
        printHeap(maxHeap, "Isi heap setelah delete(" + del + ")  ");
 
        System.out.println("\n========================================");
        System.out.println("        MIN-HEAP IMPLEMENTATION           ");
        System.out.println("  (Root selalu menyimpan nilai TERKECIL)  ");
        System.out.println("__________________________________________\n");
 
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
 
        for (int v : values) {
            minHeap.add(v);
            System.out.println("Insert " + v + " -> elemen terkecil saat ini (root): " + minHeap.peek());
        }
 
        System.out.println("\nElemen terkecil saat ini (root): " + minHeap.peek());
 
        System.out.print("\nExtract semua dari Min-Heap (otomatis urut ascending/terkecil ke terbesar):\n[ ");
        while (!minHeap.isEmpty()) System.out.print(minHeap.poll() + " ");
        System.out.println("]");
 
        System.out.println("\n========================================");
        System.out.println("  PERBANDINGAN MAX-HEAP vs MIN-HEAP       ");
        System.out.println("__________________________________________");
 
        PriorityQueue<Integer> mxH = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> mnH = new PriorityQueue<>();
        for (int x : values) { mxH.add(x); mnH.add(x); }
 
        System.out.print("Max-Heap extract order (terbesar ke terkecil): [ ");
        while (!mxH.isEmpty()) System.out.print(mxH.poll() + " ");
        System.out.println("]");
 
        System.out.print("Min-Heap extract order (terkecil ke terbesar): [ ");
        while (!mnH.isEmpty()) System.out.print(mnH.poll() + " ");
        System.out.println("]");
 
        System.out.println("\nKesimpulan:");
        System.out.println("  Max-Heap -> root = elemen TERBESAR, extract urut dari besar ke kecil");
        System.out.println("  Min-Heap -> root = elemen TERKECIL, extract urut dari kecil ke besar");
 
        sc.close();
    }
}
