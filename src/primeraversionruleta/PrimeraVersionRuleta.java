package primeraversionruleta;

import java.util.Random;
import javax.swing.JOptionPane;

public class PrimeraVersionRuleta {

    public static void main(String[] args) {

        int numerosTotalesRuleta = 37;

        boolean seguirJugando = true;

        while (seguirJugando) {
            int numJugadores = obtenerNumeroJugadores();

            if (numJugadores <= 0 || numJugadores > 8) {
                JOptionPane.showMessageDialog(null, "El programa se va a cerrar. Debe elegir un número positivo de jugadores.");
                break;
            } else {
                String[] nombres = new String[numJugadores];
                int[] creditos = new int[numJugadores];
                int[] cantidadesApostadas = new int[numJugadores];
                int[][] numerosApostados = new int[numJugadores][];

                // Pedir información de cada jugador
                for (int i = 0; i < numJugadores; i++) {
                    nombres[i] = obtenerInput("Introduzca el nombre del jugador " + (i + 1) + ":");
                    creditos[i] = obtenerEnteroInput("Introduzca el crédito de " + nombres[i] + ":");
                    cantidadesApostadas[i] = obtenerEnteroInput("Introduzca la cantidad de números a los que desea apostar " + nombres[i] + ":");

                    // Crear un nuevo arreglo para almacenar los números apostados por el jugador
                    numerosApostados[i] = new int[cantidadesApostadas[i]];

                    for (int j = 0; j < cantidadesApostadas[i]; j++) {
                        numerosApostados[i][j] = obtenerEnteroInput("Introduzca el número apostado por  " + nombres[i] + " (apuesta " + (j + 1) + "):");
                    }
                }

                int numeroRuleta = girarRuleta(numerosTotalesRuleta);

                for (int i = 0; i < numJugadores; i++) {
                    Jugador(nombres[i], creditos[i], cantidadesApostadas[i], numerosApostados[i], numeroRuleta);
                }
            }

            // Preguntar al usuario si quiere seguir jugando
            int respuesta = obtenerEnteroInput("¿Quieres seguir jugando? (1: Sí / 0: No)");

            if (respuesta == 0) {
                seguirJugando = false;
                JOptionPane.showMessageDialog(null,"Gracias por jugar. ¡Hasta luego!" );
            }
        }

        int[] numerosRuleta = new int[numerosTotalesRuleta];

        for (int j = 0; j < numerosRuleta.length; j++) {
            numerosRuleta[j] = j;
        }
    }

    public static int obtenerNumeroJugadores() {
        int numJugadores = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            String numJugadoresStr = JOptionPane.showInputDialog("Hola, ¿cuántos jugadores vienen a jugar?");

            if (esNumeroEntero(numJugadoresStr)) {
                numJugadores = Integer.parseInt(numJugadoresStr);

                if (numJugadores > 0 && numJugadores <= 8) {
                    entradaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe elegir un número positivo de jugadores (hasta 8).");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido de jugadores.");
            }
        }

        return numJugadores;
    }

    public static boolean esNumeroEntero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String obtenerInput(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    public static int obtenerEnteroInput(String mensaje) {
        int resultado = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            String input = obtenerInput(mensaje);

            if (esNumeroEntero(input)) {
                resultado = Integer.parseInt(input);
                entradaValida = true;
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            }
        }

        return resultado;
    }

    public static int girarRuleta(int numerosTotalesRuleta) {
        Random random = new Random();
        return random.nextInt(numerosTotalesRuleta);
    }

    public static void Jugador(String nombre, int creditos, int cantidadesApostadas, int[] numerosApostados, int numeroRuleta) {
         JOptionPane.showMessageDialog(null, "Hola " + nombre + ",\n" + "Crédito inicial: " + creditos
        + "\nNúmeros de apuestas: " + cantidadesApostadas + "\n Números apostados: " + java.util.Arrays.toString(numerosApostados)
        );

        // Comparar los números apostados con el resultado de la ruleta
        boolean gano = false;
        for (int k = 0; k < numerosApostados.length; k++) {
            if (numerosApostados[k] == numeroRuleta) {
                gano = true;
                break;
            }
        }

        if (gano) {
            JOptionPane.showMessageDialog(null,"¡Felicidades! Has ganado." );
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Ganas 36 veces la apuesta por cada número
            creditos = creditos + (36 * cantidadesApostadas);
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento, no has ganado esta vez.");
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Pierdes 1 crédito por cada número apostado
            creditos = creditos - cantidadesApostadas;
        }
        JOptionPane.showMessageDialog(null,"Tu nuevo saldo es: " + creditos );
    }
}