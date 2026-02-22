package util;

import model.entity.Abonnement;
import model.entity.Paiement;
import model.enums.StatutPaiement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class testMenu {

    public static void main(String[] args) {
        LocalDate NOW = LocalDate.now();

        List<Paiement> paiements = Arrays.asList(
                new Paiement("ab1", "p1", LocalDate.of(2025, 1, 5), NOW, StatutPaiement.PAYE, "Credit Card", 120.0),
                new Paiement("ab2", "p2", LocalDate.of(2025, 1, 10), NOW, StatutPaiement.NON_PAYE, "PayPal", 80.0),
                new Paiement("ab3", "p3", LocalDate.of(2025, 2, 3), NOW, StatutPaiement.EN_RETARD, "Bank Transfer", 200.0),
                new Paiement("ab4", "p4", LocalDate.of(2025, 2, 15), NOW, StatutPaiement.PAYE, "Credit Card", 150.0),
                new Paiement("ab5", "p5", LocalDate.of(2025, 3, 7), NOW, StatutPaiement.NON_PAYE, "PayPal", 60.0),
                new Paiement("ab6", "p6", LocalDate.of(2025, 3, 21), NOW, StatutPaiement.PAYE, "Credit Card", 300.0),
                new Paiement("ab7", "p7", LocalDate.of(2025, 4, 2), NOW, StatutPaiement.EN_RETARD, "Bank Transfer", 90.0),
                new Paiement("ab8", "p8", LocalDate.of(2025, 4, 18), NOW, StatutPaiement.NON_PAYE, "PayPal", 110.0),
                new Paiement("ab9", "p9", LocalDate.of(2025, 5, 12), NOW, StatutPaiement.PAYE, "Credit Card", 450.0),
                new Paiement("ab10", "p10", LocalDate.of(2025, 5, 25), NOW, StatutPaiement.NON_PAYE, "PayPal", 75.0),
                new Paiement("ab11", "p11", LocalDate.of(2025, 6, 1), NOW, StatutPaiement.EN_RETARD, "Bank Transfer", 130.0),
                new Paiement("ab12", "p12", LocalDate.of(2025, 6, 15), NOW, StatutPaiement.PAYE, "Credit Card", 210.0),
                new Paiement("ab13", "p13", LocalDate.of(2025, 7, 4), NOW, StatutPaiement.NON_PAYE, "PayPal", 95.0),
                new Paiement("ab14", "p14", LocalDate.of(2025, 7, 20), NOW, StatutPaiement.EN_RETARD, "Bank Transfer", 170.0),
                new Paiement("ab15", "p15", LocalDate.of(2025, 8, 6), NOW, StatutPaiement.PAYE, "Credit Card", 400.0),
                new Paiement("ab16", "p16", LocalDate.of(2025, 8, 22), NOW, StatutPaiement.NON_PAYE, "PayPal", 220.0),
                new Paiement("ab17", "p17", LocalDate.of(2025, 9, 9), NOW, StatutPaiement.PAYE, "Credit Card", 310.0),
                new Paiement("ab18", "p18", LocalDate.of(2025, 9, 30), NOW, StatutPaiement.EN_RETARD, "Bank Transfer", 140.0),
                new Paiement("ab19", "p19", LocalDate.of(2025, 10, 10), NOW, StatutPaiement.PAYE, "Credit Card", 500.0),
                new Paiement("ab20", "p20", LocalDate.of(2025, 10, 25), NOW, StatutPaiement.NON_PAYE, "PayPal", 60.0),
                new Paiement("ab21", "p21", LocalDate.of(2025, 11, 5), NOW, StatutPaiement.PAYE, "Credit Card", 350.0),
                new Paiement("ab22", "p22", LocalDate.of(2025, 11, 18), NOW, StatutPaiement.EN_RETARD, "Bank Transfer", 180.0),
                new Paiement("ab23", "p23", LocalDate.of(2025, 12, 2), NOW, StatutPaiement.NON_PAYE, "PayPal", 75.0),
                new Paiement("ab24", "p24", LocalDate.of(2025, 12, 16), NOW, StatutPaiement.PAYE, "Credit Card", 270.0),
                new Paiement("ab25", "p25", LocalDate.of(2025, 12, 30), NOW, StatutPaiement.EN_RETARD, "Bank Transfer", 160.0),
                new Paiement("ab26", "p26", LocalDate.of(2025, 1, 12), NOW, StatutPaiement.NON_PAYE, "PayPal", 85.0),
                new Paiement("ab27", "p27", LocalDate.of(2025, 2, 8), NOW, StatutPaiement.PAYE, "Credit Card", 190.0),
                new Paiement("ab28", "p28", LocalDate.of(2025, 3, 14), NOW, StatutPaiement.EN_RETARD, "Bank Transfer", 210.0),
                new Paiement("ab29", "p29", LocalDate.of(2025, 4, 27), NOW, StatutPaiement.NON_PAYE, "PayPal", 70.0),
                new Paiement("ab30", "p30", LocalDate.of(2025, 5, 30), NOW, StatutPaiement.PAYE, "Credit Card", 330.0)
        );


//        List<Paiement> ps= new ArrayList<>();

      paiements.stream().filter(pd -> pd.getMontent() <100.0).sorted().forEach(System.out::println);





  }
}
