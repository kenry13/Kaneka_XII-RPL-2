import java.sql.*;
import java.util.Scanner;

public class koneksi {

  private static final String JDBC_URL =
    "jdbc:mysql://localhost:3306/siswa";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "";
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Connection connection = null;
    boolean exit = false; // Deklarasi variabel exit di sini

    try {
        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        System.out.println("Koneksi ke database berhasil.");

        int choice;
        
        do {
            System.out.println("Pilih Opsi:");
            System.out.println(" 1. Tambah Siswa");
            System.out.println(" 2. Perbarui Data Siswa");
            System.out.println(" 3. Hapus Siswa");
            System.out.println(" 4. Tampilkan Daftar Siswa");
            System.out.println(" 5. Keluar");

            System.out.print("Masukkan nomor tindakan: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Mengonsumsi newline

            switch (choice) {
                case 1:
                    tambahSiswa(connection);
                    break;
                case 2:
                    perbaruiDataSiswa(connection);
                    break;
                case 3:
                    hapusSiswa(connection);
                    break;
                case 4:
                    tampilkanDaftarSiswa(connection);
                    break;
                    case 5:
                    System.out.print("Anda yakin ingin keluar dari aplikasi? (y/t): ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("y")) {
                        exit = true;
                        System.out.println("Keluar dari aplikasi.");
                    } else if (confirmation.equalsIgnoreCase("t")) {
                        System.out.println("Kembali ke menu utama.");
                    } else {
                        System.out.println("Anda ngacapruk. Silakan coba lagi. 'y', atau 't'.");
                    }
                    break;
                
                
                default:
                    System.out.println("Anda ngacapruk. Silakan coba lagi.");
            }
        } while (!exit);
    } catch (SQLException e) {
        System.err.println("Koneksi gagal: " + e.getMessage());
    } finally {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi ditutup.");
            }
        } catch (SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
        }
    }
}


  private static void tambahSiswa(Connection connection) throws SQLException {
    System.out.println("### Menambah Siswa ###");
    System.out.print("Masukkan Nama Siswa: ");
    String nama = scanner.nextLine();
    System.out.print("Masukkan NIS Siswa: ");
    String nis = scanner.nextLine();
    System.out.print("Masukkan Jurusan Siswa: ");
    String jurusan = scanner.nextLine();

    String query = "INSERT INTO tabel_siswa (nama, nis, jurusan) VALUES (?, ?, ?)";
    try (
      PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
      preparedStatement.setString(1, nama);
      preparedStatement.setString(2, nis);
      preparedStatement.setString(3, jurusan);
      preparedStatement.executeUpdate();
      System.out.println("Data siswa berhasil ditambahkan.");
    } catch (SQLException e) {
      System.err.println("Gagal menambahkan data siswa: " + e.getMessage());
    }
  }

  private static void perbaruiDataSiswa(Connection connection)
    throws SQLException {
    System.out.println("### Perbarui Data Siswa ###");
    System.out.print("Masukkan NIS siswa yang ingin diperbarui: ");
    String nisToUpdate = scanner.nextLine();

    // Meminta input pengguna untuk data baru
    System.out.print("Masukkan Nama Baru: ");
    String newNama = scanner.nextLine();
    System.out.print("Masukkan NIS Baru: ");
    String newNis = scanner.nextLine();
    System.out.print("Masukkan Jurusan Baru: ");
    String newJurusan = scanner.nextLine();

    String updateQuery =
      "UPDATE tabel_siswa SET nama = ?, nis = ?, jurusan = ? WHERE nis = ?";
    try (
      PreparedStatement preparedStatement = connection.prepareStatement(
        updateQuery
      )
    ) {
      preparedStatement.setString(1, newNama);
      preparedStatement.setString(2, newNis);
      preparedStatement.setString(3, newJurusan);
      preparedStatement.setString(4, nisToUpdate);

      int affectedRows = preparedStatement.executeUpdate();
      if (affectedRows > 0) {
        System.out.println("Data siswa berhasil diperbarui.");
      } else {
        System.out.println(
          "Gagal memperbarui data siswa. NIS tidak ditemukan."
        );
      }
    } catch (SQLException e) {
      System.err.println("Gagal memperbarui data siswa: " + e.getMessage());
    }
  }

  private static void hapusSiswa(Connection connection) throws SQLException {
    System.out.println("### Hapus Data Siswa ###");
    System.out.print("Masukkan NIS siswa yang ingin dihapus: ");
    String nisToDelete = scanner.nextLine();

    String deleteQuery = "DELETE FROM tabel_siswa WHERE nis = ?";
    try (
      PreparedStatement preparedStatement = connection.prepareStatement(
        deleteQuery
      )
    ) {
      preparedStatement.setString(1, nisToDelete);

      int affectedRows = preparedStatement.executeUpdate();
      if (affectedRows > 0) {
        System.out.println(
          "Data siswa dengan NIS " + nisToDelete + " berhasil dihapus."
        );
      } else {
        System.out.println("Gagal menghapus data siswa. NIS tidak ditemukan.");
      }
    } catch (SQLException e) {
      System.err.println("Gagal menghapus data siswa: " + e.getMessage());
    }
  }

  private static void tampilkanDaftarSiswa(Connection connection)
    throws SQLException {
    System.out.println("### Daftar Siswa ###");

    String query = "SELECT * FROM tabel_siswa";

    try (
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query)
    ) {
      System.out.println(
        "-----------------------------------------------------------"
      );
      System.out.printf(
        "%-5s | %-15s | %-10s | %-15s%n",
        "ID",
        "NIS",
        "Nama",
        "Jurusan"
      );
      System.out.println(
        "-----------------------------------------------------------"
      );

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String nis = resultSet.getString("nis");
        String nama = resultSet.getString("nama");
        String jurusan = resultSet.getString("jurusan");

        System.out.printf(
          "%-5d | %-15s | %-10s | %-15s%n",
          id,
          nis,
          nama,
          jurusan
        );
      }
    } catch (SQLException e) {
      System.err.println("Gagal menampilkan daftar siswa: " + e.getMessage());
    }
  }
}