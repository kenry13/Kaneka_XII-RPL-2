import java.util.Scanner;
import java.io.*;

public class Aplikasi {
    public static void main(String[] args) {
        BukuTelepon bukuTelepon = new BukuTelepon();
        Scanner scanner = new Scanner(System.in);
        boolean keluar = false;
        String namaFile = "kontak.dat";

        // Membaca data kontak dari file jika ada
        bukuTelepon.bacaDariFile(namaFile);

        while (!keluar) {
            System.out.println("Menu:");
            System.out.println("1. Tambah Kontak");
            System.out.println("2. Hapus Kontak by Nama");
            System.out.println("3. Hapus Kontak by Nomor Telepon");
            System.out.println("4. Tampilkan Semua Kontak");
            System.out.println("5. Keluar");

            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Nomor Telepon: ");
                    String nomorTelepon = scanner.nextLine();
                    System.out.print("Alamat Email: ");
                    String alamatEmail = scanner.nextLine();
                    Kontak kontak = new Kontak(nama, nomorTelepon, alamatEmail);
                    bukuTelepon.tambahKontak(kontak);
                    break;
                case "2":
                    System.out.print("Nama yang akan dihapus: ");
                    String namaHapus = scanner.nextLine();
                    bukuTelepon.hapusKontak(namaHapus);
                    break;
                case "3":
                    System.out.print("Nomor Telepon yang akan dihapus: ");
                    String nomorTeleponHapus = scanner.nextLine();
                    bukuTelepon.hapusKontakByNomor(nomorTeleponHapus);
                    break;
                case "4":
                    for (Kontak k : bukuTelepon.getDaftarKontak()) {
                        System.out.println(k);
                        System.out.println("-------------------------");
                    }
                    break;
                case "5":
                    System.out.print("Apakah Anda yakin ingin keluar? (y/n): ");
                    String konfirmasi = scanner.nextLine();
                    if (konfirmasi.equalsIgnoreCase("y")) {
                        System.out.println("Terima kasih!");
                        keluar = true;
                    } else if (konfirmasi.equalsIgnoreCase("n")) {
                        // Simpan data kontak ke file sebelum keluar
                        bukuTelepon.simpanKeFile(namaFile);
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }

        scanner.close();
    }
}
