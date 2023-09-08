import java.io.Serializable;

public class Kontak implements Serializable {
    private String nama;
    private String nomorTelepon;
    private String alamatEmail;

    public Kontak(String nama, String nomorTelepon, String alamatEmail) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.alamatEmail = alamatEmail;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getAlamatEmail() {
        return alamatEmail;
    }

    public void setAlamatEmail(String alamatEmail) {
        this.alamatEmail = alamatEmail;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + "\nNomor Telepon: " + nomorTelepon + "\nAlamat Email: " + alamatEmail;
    }
}
