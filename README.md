📊 Projekt 1: Kalkulator BMI (Java Swing)
Aplikacja okienkowa napisana w języku Java, służąca do obliczania wskaźnika masy ciała (BMI). Projekt został stworzony w celu demonstracji zasad programowania obiektowego (OOP) oraz obsługi błędów w interfejsie graficznym.

🛠️ Charakterystyka Techniczna
Język programowania: Java (wersja 21+)
Interfejs użytkownika: Okienkowy (GUI - biblioteka Swing)
Środowisko programistyczne: IntelliJ IDEA
System budowania: IntelliJ / Maven
Wykorzystane elementy obiektowości:
Klasy i Metody: Logika matematyczna została wydzielona do osobnej klasy BmiKalkulator, co pozwala na separację logiki od widoku (zasada Single Responsibility).
Konstruktory: Wykorzystane do inicjalizacji obiektu obliczeniowego danymi użytkownika.
Hermetyzacja: Pola klasy są prywatne (private), dostęp do nich odbywa się przez kontrolowane metody.
Metody: oblicz() (logika matematyczna) oraz interpretuj() (logika warunkowa).
📝 Opis Działania
Program pobiera od użytkownika dwie wartości zmiennoprzecinkowe:

Waga (w kilogramach)
Wzrost (w metrach, np. 1.85)
Po kliknięciu przycisku Oblicz BMI, aplikacja przetwarza dane zgodnie z algorytmem: BMI = waga / (wzrost * wzrost)

Dane wyjściowe:
Liczbowy współczynnik BMI (formatowany do 2 miejsc po przecinku).
Słowna interpretacja (Niedowaga, Waga prawidłowa, Nadwaga, Otyłość).
🛡️ Obsługa Błędów
Program został zabezpieczony przed błędnymi danymi wprowadzonymi przez użytkownika:

Błąd twardy (NumberFormatException): Przechwytywany przez blok try-catch. Jeśli użytkownik wpisze litery zamiast liczb, program wyświetla okno dialogowe z ostrzeżeniem zamiast ulec awarii.
Błąd miękki (Walidacja logiczna): Instrukcja if sprawdza, czy wprowadzone liczby są większe od zera. Zapobiega to m.in. błędom dzielenia przez zero.
📸 Przebieg działania (Zrzuty ekranu)
Scenariusz	Opis	Zrzut ekranu
Prawidłowe obliczenia	Użytkownik podał poprawne dane (np. 80kg, 1.80m).	Prawidłowy wynik
Błąd twardy	Wpisanie znaków nienumerycznych (tekstu).	Błąd liter
Błąd miękki	Wpisanie wartości nielogicznych (np. wzrost 0).	Błąd zera
💻 Kod źródłowy
import javax.swing.*;
import java.awt.*;

// KLASA LOGIKI
class BmiKalkulator {
    private double waga;
    private double wzrost;

    public BmiKalkulator(double waga, double wzrost) {
        this.waga = waga;
        this.wzrost = wzrost;
    }

    public double oblicz() {
        return waga / (wzrost * wzrost);
    }

    public String interpretuj(double bmi) {
        if (bmi < 18.5) return "Niedowaga";
        if (bmi < 25) return "Waga prawidłowa";
        if (bmi < 30) return "Nadwaga";
        return "Otyłość";
    }
}

// KLASA OKNA
public class BmiApp extends JFrame {
    private JTextField poleWagi, poleWzrostu;
    private JLabel etykietaWyniku;

    public BmiApp() {
        setTitle("Kalkulator BMI");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        add(new JLabel("Podaj wagę (kg):", SwingConstants.CENTER));
        poleWagi = new JTextField();
        add(poleWagi);

        add(new JLabel("Podaj wzrost (np. 1.80):", SwingConstants.CENTER));
        poleWzrostu = new JTextField();
        add(poleWzrostu);

        JButton przyciskOblicz = new JButton("Oblicz BMI");
        etykietaWyniku = new JLabel("Wynik: ", SwingConstants.CENTER);

        przyciskOblicz.addActionListener(e -> {
            try {
                double w = Double.parseDouble(poleWagi.getText().replace(",", "."));
                double h = Double.parseDouble(poleWzrostu.getText().replace(",", "."));

                if (h <= 0 || w <= 0) {
                    JOptionPane.showMessageDialog(this, "Wartości muszą być większe od 0!");
                    return;
                }

                BmiKalkulator kalkulator = new BmiKalkulator(w, h);
                double wynik = kalkulator.oblicz();
                String opis = kalkulator.interpretuj(wynik);

                etykietaWyniku.setText(String.format("BMI: %.2f - %s", wynik, opis));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Błąd: Wprowadź poprawne liczby!");
            }
        });

        add(przyciskOblicz);
        add(etykietaWyniku);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BmiApp::new);
    }
}
