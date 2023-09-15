import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Kontak {
    private String nama;
    private String email;
    private String alamat;
    private String nomor;

    public Kontak(String nama, String email, String alamat, String nomor) {
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail (){
        return email;
    }

    public String getAlamat () {
        return alamat;
    }

    public String getNomor () {
        return nomor;
    }

    @Override
    public String toString() {
        return "nama  :" + nama + "\nemail :" + email + "\nalamat:" + alamat + "\nnomor :" + nomor + "\n" + "==================================\n" ;
    }

}

public class AplikasiKontak {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Kontak> daftarKontak = new ArrayList<>();
        loadFromFile(daftarKontak);
        boolean loop = true;

    while (loop) {
            System.out.println("==================================");
            System.out.println("           MENU KONTAK            ");
            System.out.println("==================================");
            System.out.println("[1] Semua  Kontak");
            System.out.println("[2] Buat   Kontak");
            System.out.println("[3] Hapus  Kontak");
            System.out.println("[4] Keluar Kontak");
            System.out.println("[5] Simpan Kontak");
            System.out.println("==================================");
            System.out.println("Pilih salah satu :");
            System.out.println("==================================");
            int pilihan = scanner.nextInt();
            System.out.print("\n\n");

            if (pilihan == 1) {
                System.out.println("==================================");
                System.out.println("           SEMUA KONTAK           ");
                System.out.println("==================================");
                for (Kontak kontak : daftarKontak) {
                    System.out.print(kontak);
                }
                System.out.print("\n\n");
            } else if (pilihan == 2) {
                System.out.println("==================================");
                System.out.println("           BUAT KONTAK            ");
                System.out.println("==================================");

                System.out.print("Nama   :");
                String nama = scanner.next();
                System.out.print("Email  :");
                String email = scanner.next();
                System.out.print("Alamat :");
                String alamat = scanner.next();
                System.out.print("Nomor  :");
                String nomor = scanner.next();

                Kontak baru = new Kontak(nama, email, alamat, nomor);
                daftarKontak.add(baru);
                System.out.println("==================================");
                System.out.println("             SUCCESS              ");
                System.out.println("==================================");

                System.out.print("\n\n");
            }else if (pilihan == 3){
                System.out.println("==================================");
                System.out.println("           HAPUS KONTAK           ");
                System.out.println("==================================");

                System.out.print("Masukkan nama :");
                String namaHapus = scanner.next();

                for (Kontak kontak : daftarKontak) {
                    if(kontak.getNama().equals(namaHapus)){
                        daftarKontak.remove(kontak);
                        break;
                    }
                }

                System.out.println("==================================");
                System.out.println("             SUCCESS              ");
                System.out.println("==================================");

                System.out.print("\n\n");
            }else if (pilihan == 4){
                System.out.println("==================================");
                System.out.println("              THANKS              ");
                System.out.println("==================================");
                return;
            }else if (pilihan == 5){
                saveToFile(daftarKontak);

                System.out.println("==================================");
                System.out.println("             SUCCESS              ");
                System.out.println("==================================");

                System.out.print("\n\n");
            }  else{
                System.out.println("==================================");
                System.out.println("             FAILED              ");
                System.out.println("==================================");
            }
        }

    }

    private static void saveToFile(ArrayList<Kontak> daftarKontak) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("kontak.txt"));

            for (Kontak kontak : daftarKontak) {
                String line = "nama  :" + kontak.getNama() + "\nemail :" + kontak.getEmail() + "\nalamat:" + kontak.getAlamat() + "\nnomor :" + kontak.getNomor() + "\n" + "==================================\n" ;;
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     private static void loadFromFile(ArrayList<Kontak> daftarKontak) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Kontak.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("nama  :")) {
                    String nama = line.substring(7); // Mengambil nama setelah "nama  :"
                    String email = reader.readLine().substring(7);
                    String alamat = reader.readLine().substring(8);
                    String nomor = reader.readLine().substring(7);

                    Kontak kontak = new Kontak(nama, email, alamat, nomor);
                    daftarKontak.add(kontak);

                    // Membaca garis pemisah dan mengabaikannya
                    reader.readLine();
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

