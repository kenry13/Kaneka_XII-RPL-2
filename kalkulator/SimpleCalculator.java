import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Selamat datang di Kalkulator Sederhana!");
        
        while (true) {
            System.out.println("\nPilih operasi:");
            System.out.println("1. Penjumlahan");
            System.out.println("2. Pengurangan");
            System.out.println("3. Perkalian");
            System.out.println("4. Pembagian");
            System.out.println("5. Keluar");
            
            int choice = scanner.nextInt();
            
            if (choice == 5) {
                System.out.println("Terima kasih telah menggunakan kalkulator sederhana.");
                break;
            }
            
            if (choice >= 1 && choice <= 4) {
                System.out.print("Masukkan angka pertama: ");
                double num1 = scanner.nextDouble();
                
                System.out.print("Masukkan angka kedua: ");
                double num2 = scanner.nextDouble();
                
                double result = 0;
                
                switch (choice) {
                    case 1:
                        result = num1 + num2;
                        break;
                    case 2:
                        result = num1 - num2;
                        break;
                    case 3:
                        result = num1 * num2;
                        break;
                    case 4:
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            System.out.println("Tidak dapat dibagi oleh nol");
                            continue;
                        }
                        break;
                }
                
                System.out.println("Hasil: " + result);
            } else {
                System.out.println("Pilihan tidak valid. Silakan masukkan pilihan yang benar.");
            }
        }
        
        scanner.close();
    }
}
