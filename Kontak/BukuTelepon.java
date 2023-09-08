import java.io.*;
import java.util.ArrayList;

public class BukuTelepon {
    private ArrayList<Kontak> daftarKontak = new ArrayList<>();

    public void tambahKontak(Kontak kontak) {
        daftarKontak.add(kontak);
    }

    public void hapusKontak(String nama) {
        daftarKontak.removeIf(k -> k.getNama().equalsIgnoreCase(nama));
    }

    public void hapusKontakByNomor(String nomorTelepon) {
        daftarKontak.removeIf(k -> k.getNomorTelepon().equals(nomorTelepon));
    }

    public ArrayList<Kontak> getDaftarKontak() {
        return daftarKontak;
    }

    public void simpanKeFile(String namaFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(namaFile))) {
            oos.writeObject(daftarKontak);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void bacaDariFile(String namaFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(namaFile))) {
            daftarKontak = (ArrayList<Kontak>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
