package primeraversionruleta;

import java.util.Random;
import java.util.Scanner;

public class PrimeraVersionRuleta {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int numerosTotalesRuleta = 37;

        int numJugadores = 0;

        System.out.println("Hola, ¿cuantos jugadores venis a jugar?");
        numJugadores = sc.nextInt();
        if (numJugadores <= 0 || numJugadores > 8) {
            System.out.println("El programa se va a cerrar, tiene que elegir un numero positivo de jugadores");
        } else {
            String[] nombres = new String[numJugadores];
            int[] creditos = new int[numJugadores];
            int[] numApostado = new int[numJugadores];

            // Pedir información de cada jugador
            for (int i = 0; i < numJugadores; i++) {
                sc.nextLine();  // Limpiar el buffer de entrada
                System.out.println("Introduzca el nombre del jugador " + (i + 1) + ":");
                nombres[i] = sc.nextLine();
                System.out.println("Introduzca el crédito del jugador " + (i + 1) + ":");
                creditos[i] = sc.nextInt();
                System.out.println("Introduzca el número apostado por el jugador " + (i + 1) + ":");
                numApostado[i] = sc.nextInt();
            }

            int numeroRuleta = girarRuleta(numerosTotalesRuleta);

            for (int i = 0; i < numJugadores; i++) {
                Jugador(nombres[i], creditos[i], numApostado[i], numeroRuleta);
            }

        }

        //For para declarar todos los numeros de la ruleta
        int[] numerosRuleta = new int[numerosTotalesRuleta];

        for (int j = 0; j < numerosRuleta.length; j++) {
            numerosRuleta[j] = j;
        }

        System.out.println(numerosRuleta);
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
