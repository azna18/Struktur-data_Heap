import heapq
 
def heapify_up(heap, index):
    while index > 0:
        parent = (index - 1) // 2
        if heap[parent] < heap[index]:
            heap[parent], heap[index] = heap[index], heap[parent]
            index = parent
        else:
            break
 
def heapify_down(heap, index, size):
    largest = index
    left  = 2 * index + 1
    right = 2 * index + 2
 
    if left  < size and heap[left]  > heap[largest]: largest = left
    if right < size and heap[right] > heap[largest]: largest = right
 
    if largest != index:
        heap[index], heap[largest] = heap[largest], heap[index]
        heapify_down(heap, largest, size)
 
def insert_max(heap, value):
    heap.append(value)
    heapify_up(heap, len(heap) - 1)
 
def get_max(heap):
    if not heap: return -1
    return heap[0]
 
def extract_max(heap):
    if not heap: return -1
    max_val = heap[0]
    heap[0] = heap[-1]
    heap.pop()
    heapify_down(heap, 0, len(heap))
    return max_val
 
def delete_node(heap, value):
    index = -1
    for i in range(len(heap)):
        if heap[i] == value:
            index = i
            break
    if index == -1:
        print(f"Elemen {value} tidak ditemukan!")
        return
    heap[index] = heap[-1]
    heap.pop()
    if index < len(heap):
        heapify_up(heap, index)
        heapify_down(heap, index, len(heap))
 
def print_heap(heap, label):
    print(f"{label}: [ {' '.join(map(str, heap))} ]")
 
 
 
print("========================================")
print("   MAX-HEAP & MIN-HEAP - INPUT MANUAL   ")
print("________________________________________\n")
 
# Minta jumlah elemen dari pengguna
n = int(input("Masukkan jumlah elemen: "))
values = []
print(f"Masukkan {n} angka:")
for i in range(n):
    val = int(input(f"  Elemen ke-{i+1}: "))
    values.append(val)
 
print("\n========================================")
print("        MAX-HEAP IMPLEMENTATION         ")
print("  (Root selalu menyimpan nilai TERBESAR) ")
print("__________________________________________\n")
 
max_heap = []
 
for v in values:
    insert_max(max_heap, v)
    print(f"Insert {v} -> ", end="")
    print_heap(max_heap, "Isi Max-Heap (index 0 = root/terbesar)")
 
print(f"\nElemen terbesar saat ini (root): {get_max(max_heap)}")
 
print(f"Extract Max (ambil & hapus root): {extract_max(max_heap)}")
print_heap(max_heap, "Isi heap setelah extract         ")
 
del_val = int(input("\nMasukkan elemen yang ingin dihapus: "))
delete_node(max_heap, del_val)
print_heap(max_heap, f"Isi heap setelah delete({del_val})  ")
 
print("\n========================================")
print("        MIN-HEAP IMPLEMENTATION         ")
print("  (Root selalu menyimpan nilai TERKECIL) ")
print("__________________________________________\n")
 
min_heap = []
 
for v in values:
    heapq.heappush(min_heap, v)
    print(f"Insert {v} -> elemen terkecil saat ini (root): {min_heap[0]}")
 
print(f"\nElemen terkecil saat ini (root): {min_heap[0]}")
 
temp = min_heap[:]
result = []
while temp:
    result.append(heapq.heappop(temp))
print("Extract semua dari Min-Heap (otomatis urut ascending/terkecil ke terbesar):")
print("[ " + " ".join(map(str, result)) + " ]")
 
print("\n========================================")
print("  PERBANDINGAN MAX-HEAP vs MIN-HEAP     ")
print("________________________________________")
 
max_h = []
for x in values: heapq.heappush(max_h, -x)
max_order = []
while max_h: max_order.append(-heapq.heappop(max_h)) 
 
min_h = []
for x in values: heapq.heappush(min_h, x)
min_order = []
while min_h: min_order.append(heapq.heappop(min_h))
 
print("Max-Heap extract order (terbesar ke terkecil): [ " + " ".join(map(str, max_order)) + " ]")
 
print("Min-Heap extract order (terkecil ke terbesar): [ " + " ".join(map(str, min_order)) + " ]")
 
print("\nKesimpulan:")
print("  Max-Heap -> root = elemen TERBESAR, extract urut dari besar ke kecil")
print("  Min-Heap -> root = elemen TERKECIL, extract urut dari kecil ke besar")
