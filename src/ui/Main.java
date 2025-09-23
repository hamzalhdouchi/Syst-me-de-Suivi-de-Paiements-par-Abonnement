package ui;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       boolean exists = true;
       while (!exists){
           menu.afficherMenu();
           Scanner sc = new Scanner(System.in);
           System.out.println("Veuillez choisir une option : ");
           int choix = sc.nextInt();
           sc.nextLine();
           switch (choix){
                case 1:
                     break;
                case 2:
                     break;
                case 3:
                     break;
                case 4:
                     break;
                case 5:
                     break;
                case 6:
                     break;
                case 7:
                   break;
                case 8:
                   break;
                case 9:
                   break;
                case 10:
                   break;
                case 11:
                   break;
                case 12:
                   break;
                case 0:
                     System.out.println("Au revoir!");
                        exists = true;
                   break;
                default:
                     System.out.println("Option invalide. Veuillez réessayer.");
           }
       }

    }
}