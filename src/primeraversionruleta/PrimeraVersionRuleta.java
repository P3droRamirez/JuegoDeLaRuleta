package primeraversionruleta;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class PrimeraVersionRuleta {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int numerosTotalesRuleta = 37;
        int saldo = 0;

        boolean seguirJugando = true;

        while (seguirJugando) {
            int numJugadores = 0;

             String numJugadoresStr = JOptionPane.showInputDialog("Hola, ¿cuántos jugadores vienen a jugar?");
        try {
            numJugadores = Integer.parseInt(numJugadoresStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido de jugadores.");
            System.exit(0);
        }
            if (numJugadores <= 0 || numJugadores > 8) {
                 JOptionPane.showMessageDialog(null, "El programa se va a cerrar. Debe elegir un número positivo de jugadores.");
                break;
            } else {
                String[] nombres = new String[numJugadores];
                int[] creditos = new int[numJugadores];
                int[] numApostado = new int[numJugadores];

                // Pedir información de cada jugador
                for (int i = 0; i < numJugadores; i++) {
                nombres[i] = JOptionPane.showInputDialog("Introduzca el nombre del jugador " + (i + 1) + ":");
                String creditos2 = JOptionPane.showInputDialog("Introduzca el crédito del jugador " + (i + 1) + ":");
                String numApostado2 = JOptionPane.showInputDialog("Introduzca el número apostado por el jugador " + (i + 1) + ":");

                try {
                    creditos[i] = Integer.parseInt(creditos2);
                    numApostado[i] = Integer.parseInt(numApostado2);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos para créditos y número apostado.");
                    System.exit(0);
                }
            }

                int numeroRuleta = girarRuleta(numerosTotalesRuleta);

                for (int i = 0; i < numJugadores; i++) {
                    Jugador(nombres[i], creditos[i], numApostado[i], numeroRuleta);
                }
            }

            // Preguntar al usuario si quiere seguir jugando
            System.out.println("¿Quieres seguir jugando? (1: Sí / 0: No)");
            int respuesta = sc.nextInt();

            if (respuesta == 0) {
                seguirJugando = false;
                System.out.println("Gracias por jugar. ¡Hasta luego!");
                
            }
            
        }
         int[] numerosRuleta = new int[numerosTotalesRuleta];

        for (int j = 0; j < numerosRuleta.length; j++) {
            numerosRuleta[j] = j;
        }

        // Mostrar los números de la ruleta
        JOptionPane.showMessageDialog(null, "Números de la ruleta: " + java.util.Arrays.toString(numerosRuleta));

        sc.close();
    }

    public static int girarRuleta(int numerosTotalesRuleta) {
        Random random = new Random();
        return random.nextInt(numerosTotalesRuleta);
    }

    public static void Jugador(String nombre, int creditos, int numApostado, int numeroRuleta) {
        System.out.println("Hola, soy el jugador " + nombre + ". Mi crédito es: " + creditos
                + " y aposté por el número " + numApostado + ". El número en la ruleta es: " + numeroRuleta);

        // Comparar el número apostado con el resultado de la ruleta
        if (numApostado == numeroRuleta) {
            System.out.println("¡Felicidades! Has ganado.");
            // Ganas 36 veces la apuesta
            creditos = creditos + 36;
        } else {
            System.out.println("Lo siento, no has ganado esta vez.");
            // Pierdes 1 crédito
            creditos = creditos - 1;
        }
        System.out.println("Tu nuevo saldo es: " + creditos);
        System.out.println("--------");
    }
}