import java.util.Random;
import java.text.DecimalFormat;


public class MonteCarloRealisticScenarioGenerator {

    // Metoda generująca realistyczne scenariusze
    public static void generateScenarios(double initialPrice, int numberOfDays, double volatility, Random random, int numberoftries, double[][] tabela) {

        // Generowanie scenariuszy dla kolejnych dni
        for(int i = 0; i < numberoftries; i++) {
            double price = initialPrice;
            for (int day = 1; day < numberOfDays; day++) {
                // Generowanie zmiany ceny aktywa na podstawie rozkładu normalnego
                double dailyReturn = random.nextGaussian() * volatility;
                price *= (1 + dailyReturn);

                // Wyświetlenie ceny aktywa na danym dniu
                //String formattedPrice = df.format(price);
                tabela[day][i] = price;
                //System.out.println(df.format(price));
            }
        }
    }

    public static void main(String[] args) {
        double initialPrice = 66000.0; // początkowa cena aktywa
        int numberOfDays = 200; // liczba dni symulacji
        double volatility = 0.03; // zmienność (np. odchylenie standardowe zwrotu dziennego)
        int numberoftries = 10;

        double[][] tabela = new double[numberOfDays][numberoftries];

        Random random = new Random();

        // Generowanie realistycznych scenariuszy
        generateScenarios(initialPrice, numberOfDays, volatility, random, numberoftries, tabela);
        DecimalFormat df = new DecimalFormat("#.##");

        for(int j = 0; j < numberOfDays; j++){
            for(int i = 0; i < numberoftries; i++){
                String formatprice = df.format(tabela[j][i]);
                System.out.print(formatprice + "\t");
            }
            System.out.print("\n");
        }
    }
}