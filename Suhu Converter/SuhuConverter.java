import java.util.Scanner;

public class SuhuConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan suhu dalam Celsius: ");
        double suhuCelsius = scanner.nextDouble();

        double suhuFahrenheit = (suhuCelsius * 9/5) + 32;
        double suhuKelvin = suhuCelsius + 273.15;

        System.out.println("Suhu dalam Fahrenheit: " + suhuFahrenheit);
        System.out.println("Suhu dalam Kelvin: " + suhuKelvin);

        scanner.close();
    }
}
