import csv
 
min_heap = []
max_heap = []
 
def min_naik(heap, i):
    while i > 0:
        parent = (i - 1) // 2
        if heap[i][0] < heap[parent][0]:
            heap[i], heap[parent] = heap[parent], heap[i]
            i = parent
        else:
            break
 
def min_turun(heap, i):
    n = len(heap)
    while True:
        terkecil = i
        kiri, kanan = 2*i+1, 2*i+2
        if kiri  < n and heap[kiri][0]  < heap[terkecil][0]: terkecil = kiri
        if kanan < n and heap[kanan][0] < heap[terkecil][0]: terkecil = kanan
        if terkecil == i: break
        heap[i], heap[terkecil] = heap[terkecil], heap[i]
        i = terkecil
 
def tambah_min(id, nama):
    min_heap.append([id, nama])
    min_naik(min_heap, len(min_heap) - 1)
 
def hapus_min(id):
    for i in range(len(min_heap)):
        if min_heap[i][0] == id:
            nama = min_heap[i][1]
            min_heap[i] = min_heap[-1]
            min_heap.pop()
            if i < len(min_heap):
                min_naik(min_heap, i)
                min_turun(min_heap, i)
            print(f"✔ Dihapus dari MinHeap: [{id}, {nama}]")
            return
    print(f"✖ ID {id} tidak ditemukan di MinHeap.")
 
def max_naik(heap, i):
    while i > 0:
        parent = (i - 1) // 2
        if heap[i][0] > heap[parent][0]:
            heap[i], heap[parent] = heap[parent], heap[i]
            i = parent
        else:
            break
 
def max_turun(heap, i):
    n = len(heap)
    while True:
        terbesar = i
        kiri, kanan = 2*i+1, 2*i+2
        if kiri  < n and heap[kiri][0]  > heap[terbesar][0]: terbesar = kiri
        if kanan < n and heap[kanan][0] > heap[terbesar][0]: terbesar = kanan
        if terbesar == i: break
        heap[i], heap[terbesar] = heap[terbesar], heap[i]
        i = terbesar
 
def tambah_max(id, nama):
    max_heap.append([id, nama])
    max_naik(max_heap, len(max_heap) - 1)
 
def hapus_max(id):
    for i in range(len(max_heap)):
        if max_heap[i][0] == id:
            nama = max_heap[i][1]
            max_heap[i] = max_heap[-1]
            max_heap.pop()
            if i < len(max_heap):
                max_naik(max_heap, i)
                max_turun(max_heap, i)
            print(f"✔ Dihapus dari MaxHeap: [{id}, {nama}]")
            return
    print(f"✖ ID {id} tidak ditemukan di MaxHeap.")
 
def tampil_ascending():
    if not min_heap:
        print("Heap kosong!"); return
    tmp = [x[:] for x in min_heap]
    print(f"\n{'No':<5} {'ID':<8} Nama")
    print("-" * 30)
    no = 1
    while tmp:
        idx = min(range(len(tmp)), key=lambda i: tmp[i][0])
        item = tmp.pop(idx)
        print(f"{no:<5} {item[0]:<8} {item[1]}")
        no += 1
 
def tampil_descending():
    if not max_heap:
        print("Heap kosong!"); return
    tmp = [x[:] for x in max_heap]
    print(f"\n{'No':<5} {'ID':<8} Nama")
    print("-" * 30)
    no = 1
    while tmp:
        idx = max(range(len(tmp)), key=lambda i: tmp[i][0])
        item = tmp.pop(idx)
        print(f"{no:<5} {item[0]:<8} {item[1]}")
        no += 1
 
def load_csv(filename):
    try:
        with open(filename, newline='', encoding='utf-8') as file:
            reader = csv.reader(file)
            next(reader)  # skip header
 
            for row in reader:
                if len(row) < 2:
                    continue
 
                # support ; atau ,
                if ";" in row[0]:
                    row = row[0].split(";")
 
                id   = int(row[0].strip())
                nama = row[1].strip()
 
                tambah_min(id, nama)
                tambah_max(id, nama)
 
        print(f"✅ Semua data berhasil dimasukkan ke Min-Heap & Max-Heap!")
 
    except Exception as e:
        print("❌ Error:", e)
 
while True:
    print("\n=== MENU HEAP (PYTHON) ===")
    print("1. Load Data dari CSV")
    print("2. Tambah Data")
    print("3. Tampil ASCENDING  (Min-Heap)")
    print("4. Tampil DESCENDING (Max-Heap)")
    print("5. Hapus dari Min-Heap")
    print("6. Hapus dari Max-Heap")
    print("0. Keluar")
 
    pilih = input("Pilih: ")
 
    if pilih == "1":
        load_csv("D:/kuliah/semester 4/Struktur Data/praktikum/#7 Heap/data100.csv")
 
    elif pilih == "2":
        id   = int(input("ID   : "))
        nama = input("Nama : ")
        tambah_min(id, nama)
        tambah_max(id, nama)
        print(f"✔ [{id}, {nama}] berhasil ditambahkan!")
 
    elif pilih == "3":
        tampil_ascending()
 
    elif pilih == "4":
        tampil_descending()
 
    elif pilih == "5":
        id = int(input("Masukkan ID: "))
        hapus_min(id)
 
    elif pilih == "6":
        id = int(input("Masukkan ID: "))
        hapus_max(id)
 
    elif pilih == "0":
        print("Program selesai.")
        break
 
    else:
        print("Pilihan tidak valid!")
