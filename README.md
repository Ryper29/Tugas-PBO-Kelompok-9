# Tugas PBO Kelompok 9 - Dungeons & Dragons (Java)

Tugas Pemrograman Berorientasi Objek (PBO) berbahasa **Java** dengan tema simulasi pertempuran **Dungeons & Dragons**.

> **Mata Kuliah:** Pemrograman Berorientasi Objek  
> **Pengampu:** Dr. Eng. Helmy Fitriawan, S.T., M.Sc.

## Struktur Project

```
├── Tugas-1/    → Kode awal & referensi dari dosen
│   ├── Main.java   (Dasar OOP: Class, Object, Encapsulation)
│   └── DND.java    (Inheritance, Polymorphism, Static Member, D20 System)
│
└── Tugas-2/    → Pengembangan oleh mahasiswa
    ├── Main.java   (Refactor + Full Encapsulation + Level System)
    └── DND.java    (EXP System, Level Up, Bonus Armor, Race Attribute)
```

## Konsep OOP yang Diimplementasikan

| Konsep | Keterangan |
|---|---|
| **Encapsulation** | Atribut `private` + Getter & Setter |
| **Inheritance** | `Fighter` dan `Wizard` extends `Player` |
| **Polymorphism** | Override method `attack()` di setiap subclass |
| **Object Member** | `Player` memiliki objek `Weapon` dan `Armor` (Has-A) |
| **Static Member** | `totalCharacters` pada class `Player` (Tugas 1) |
| **EXP & Leveling** | Sistem pengalaman dan naik level otomatis (Tugas 2) |

## Cara Menjalankan

```bash
# Tugas 1 - Main
cd Tugas-1
javac Main.java
java Main

# Tugas 1 - DnD
javac DND.java
java DND

# Tugas 2 - Main
cd Tugas-2
javac Main.java
java Main

# Tugas 2 - DnD
javac DND.java
java DND
```
