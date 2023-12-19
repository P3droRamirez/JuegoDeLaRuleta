package primeraversionruleta;

import java.util.Random;
import javax.swing.JOptionPane;

public class PrimeraVersionRuleta {

    public static void main(String[] args) {
        int modoJuego = 0;
        JOptionPane.showMessageDialog(null, "Diferentes modos de juego de la ruleta\n" + "______________________________________\n"
        + "-Apuestas a números\n" + "-Apuestas por color (rojo y negro)\n" + "-Tipo de número(par e impar)\n" 
        + "-Por sectores\n" + "     Sector 1 (1-18)\n" + "      Sector 2 (19-36)");
        modoJuego = obtenerEnteroInput("Seleccione modo de juego\n 1-Apuestas por números \n 2-Apuestas por color\n 3-Tipo de número\n 4-Por sectores");

        switch (modoJuego) {
            case 1:
                apuestasPorNumeros();
                break;
            case 2:
                apuestasPorColor();
                break;
            case 3:
                apuestaTipoNumero();
                break;
            case 4:
                apuestaPorSector();
                break;

        }
        
    }

    //metodo para el modo de juego por números
    public static void apuestasPorNumeros(){
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
                    comprobacionPorNumeros(nombres[i], creditos[i], cantidadesApostadas[i], numerosApostados[i], numeroRuleta);
                }
            }

            // Preguntar al usuario si quiere seguir jugando
            int respuesta = obtenerEnteroInput("¿Quieres seguir jugando por números? (1: Sí / 0: No)");

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
    //metodo para el modo de juego por colores
    public static void apuestasPorColor(){
        int respuestaColor = 0;
        

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
                // Pedir información de cada jugador
                for (int i = 0; i < numJugadores; i++) {
                    nombres[i] = obtenerInput("Introduzca el nombre del jugador " + (i + 1) + ":");
                    creditos[i] = obtenerEnteroInput("Introduzca el crédito de " + nombres[i] + ":");
                    respuestaColor = obtenerEnteroInput("1-Apostar Rojo\n 2-Apostar Negro");
                    cantidadesApostadas[i] = obtenerEnteroInput("¿Cuanto quieres apostar?");
                }

                for (int i = 0; i < numJugadores; i++) {
                    comprobacionPorColor(nombres[i], creditos[i],respuestaColor,cantidadesApostadas[i]);
                }

                 int respuesta = obtenerEnteroInput("¿Quieres seguir jugando por colores? (1: Sí / 0: No)");

                if (respuesta == 0) {
                seguirJugando = false;
                JOptionPane.showMessageDialog(null,"Gracias por jugar. ¡Hasta luego!" );
            }
                
            }
        }
    }
    //metodo para el modo de juego por tipo de numero
    public static void apuestaTipoNumero(){
        int respuestaTipoNumero = 0;
        

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
                // Pedir información de cada jugador
                for (int i = 0; i < numJugadores; i++) {
                    nombres[i] = obtenerInput("Introduzca el nombre del jugador " + (i + 1) + ":");
                    creditos[i] = obtenerEnteroInput("Introduzca el crédito de " + nombres[i] + ":");
                    respuestaTipoNumero = obtenerEnteroInput("1-Apostar Par\n 2-Apostar Impar");
                    cantidadesApostadas[i] = obtenerEnteroInput("¿Cuanto quieres apostar?");
                }

                for (int i = 0; i < numJugadores; i++) {
                    comprobacionPorTipo(nombres[i], creditos[i],respuestaTipoNumero,cantidadesApostadas[i]);
                }

                 int respuesta = obtenerEnteroInput("¿Quieres seguir jugando por tipo de números? (1: Sí / 0: No)");

                if (respuesta == 0) {
                seguirJugando = false;
                JOptionPane.showMessageDialog(null,"Gracias por jugar. ¡Hasta luego!" );
            }
                
            }
        }

    }
    //metodo para el modo de juego por sectores
    public static void apuestaPorSector(){
        int respuestaSector = 0;
        

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
                // Pedir información de cada jugador
                for (int i = 0; i < numJugadores; i++) {
                    nombres[i] = obtenerInput("Introduzca el nombre del jugador " + (i + 1) + ":");
                    creditos[i] = obtenerEnteroInput("Introduzca el crédito de " + nombres[i] + ":");
                    respuestaSector = obtenerEnteroInput("1-Apostar Primer Sector (1-18)\n 2-Apostar Segundo Sector (19-36)");
                    cantidadesApostadas[i] = obtenerEnteroInput("¿Cuanto quieres apostar?");
                }

                for (int i = 0; i < numJugadores; i++) {
                    comprobacionPorSector(nombres[i], creditos[i],respuestaSector,cantidadesApostadas[i]);
                }

                 int respuesta = obtenerEnteroInput("¿Quieres seguir jugando por sectores? (1: Sí / 0: No)");

                if (respuesta == 0) {
                seguirJugando = false;
                JOptionPane.showMessageDialog(null,"Gracias por jugar. ¡Hasta luego!" );
            }
                
            }
        }

    }
    //metodo para saber la cantidad de judaores por partida
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
//metodo para que no de error si no es un entero
    public static boolean esNumeroEntero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
//metodo para que los inputs sean de manera visual mediante ventanas emergentes
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
    //metodo para tomar un numero aleatorio para el sistema de ruleta
    public static int girarRuleta(int numerosTotalesRuleta) {
        Random random = new Random();
        return random.nextInt(numerosTotalesRuleta);
    }
// metodo para mostrar el rusltado mediante apuestas por numeros
    public static void comprobacionPorNumeros(String nombre, int creditos, int cantidadesApostadas, int[] numerosApostados, int numeroRuleta) {
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
            // Ganas la apuesta *2
            creditos = creditos + (36 * cantidadesApostadas);
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento, no has ganado esta vez.");
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Pierdes 1 crédito por cada número apostado
            creditos = creditos - cantidadesApostadas;
        }
        JOptionPane.showMessageDialog(null,"Tu nuevo saldo es: " + creditos );
    }
// metodo para mostrar el rusltado mediante apuestas por color
    public static void comprobacionPorColor(String nombre, int creditos,int respuesta, int cantidadesApostadas ){
        int[] numerosNegros = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        int[] numerosRojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        int numerosTotalesRuleta = 37;
        int numeroRuleta = girarRuleta(numerosTotalesRuleta);

        boolean premiado = false;

        String color = "";
        String rojo = "Rojo";
        String negro = "Negro";

        if (respuesta == 1){
            for(int numero : numerosRojos){
                if (numero == numeroRuleta) {
                    premiado = true;
                    color += rojo;
                    break;
                }
            }
        } else{
            for(int numero : numerosNegros){
                if (numero == numeroRuleta) {
                    premiado = true;
                    color += negro;
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Hola " + nombre + ",\n" + "Crédito inicial: " + creditos
        + "\nColor Apostado: " + color + "\n Cantidad apostada: " + cantidadesApostadas 
        );

        if (premiado) {
            JOptionPane.showMessageDialog(null,"¡Felicidades! Has ganado." );
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Ganas la apuesta *2
            creditos = creditos + (cantidadesApostadas*2);
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento, no has ganado esta vez.");
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Pierdes el credito apostado
            creditos = creditos - cantidadesApostadas;
        }
        JOptionPane.showMessageDialog(null,"Tu nuevo saldo es: " + creditos );
    }
    //metodo para comprobar si el sector seleccionado es correcto
    public static void comprobacionPorTipo(String nombre, int creditos,int respuesta, int cantidadesApostadas){
        int[] numerosPares = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36};
        int[] numerosImpares = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35};
        int numerosTotalesRuleta = 37;
        int numeroRuleta = girarRuleta(numerosTotalesRuleta);

        boolean premiado = false;

        String tipo = "";
        String par = "Par";
        String impar = "Impar";

        if (respuesta == 1){
            for(int numero : numerosPares){
                if (numero == numeroRuleta) {
                    premiado = true;
                    tipo += par;
                    break;
                }
            }
        } else{
            for(int numero : numerosImpares){
                if (numero == numeroRuleta) {
                    premiado = true;
                    tipo += impar;
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Hola " + nombre + ",\n" + "Crédito inicial: " + creditos
        + "\nTipo Apostado: " + tipo + "\n Cantidad apostada: " + cantidadesApostadas 
        );

        if (premiado) {
            JOptionPane.showMessageDialog(null,"¡Felicidades! Has ganado." );
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Ganas la apuesta *2
            creditos = creditos + (cantidadesApostadas*2);
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento, no has ganado esta vez.");
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Pierdes el credito apostado
            creditos = creditos - cantidadesApostadas;
        }
        JOptionPane.showMessageDialog(null,"Tu nuevo saldo es: " + creditos );

    }
// metodo para mostrar el rusltado mediante apuestas por sectores
    public static void comprobacionPorSector(String nombre, int creditos,int respuesta, int cantidadesApostadas){
        int[] numerosPrimerSector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
        int[] numerosSegundoSector = {19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
        int numerosTotalesRuleta = 37;
        int numeroRuleta = girarRuleta(numerosTotalesRuleta);

        boolean premiado = false;

        String sector = "";
        String primerSector = "Primer Sector";
        String segundoSector = "Segundo Sector";

        if (respuesta == 1){
            for(int numero : numerosPrimerSector){
                if (numero == numeroRuleta) {
                    premiado = true;
                    sector += primerSector;
                    break;
                }
            }
        } else{
            for(int numero : numerosSegundoSector){
                if (numero == numeroRuleta) {
                    premiado = true;
                    sector += segundoSector;
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Hola " + nombre + ",\n" + "Crédito inicial: " + creditos
        + "\nSector Apostado: " + sector + "\n Cantidad apostada: " + cantidadesApostadas 
        );

        if (premiado) {
            JOptionPane.showMessageDialog(null,"¡Felicidades! Has ganado." );
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Ganas la apuesta *2
            creditos = creditos + (cantidadesApostadas*2);
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento, no has ganado esta vez.");
            JOptionPane.showMessageDialog(null, "Número premiado: " + numeroRuleta);
            // Pierdes el credito apostado
            creditos = creditos - cantidadesApostadas;
        }
        JOptionPane.showMessageDialog(null,"Tu nuevo saldo es: " + creditos );
    }
}
