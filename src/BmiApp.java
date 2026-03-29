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