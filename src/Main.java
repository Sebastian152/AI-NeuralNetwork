
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Sebastián G.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Seleccione la operación lógica:");
        System.out.println("1. XOR");
        System.out.println("2. NAND");
        System.out.println("3. NOR");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();

        double[][] entradas = {{0,0}, {0,1}, {1,0}, {1,1}};
        double[] salidas = null;

        switch(opcion) {
            case 1:
                salidas = new double[]{0.0, 1.0, 1.0, 0.0}; // XOR
                break;
            case 2:
                salidas = new double[]{1.0, 1.0, 1.0, 0.0}; // NAND
                break;
            case 3:
                salidas = new double[]{1.0, 0.0, 0.0, 0.0}; // NOR
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        double MOMENTO = 0.5;
        double RAZON_APRENDIZAJE = 0.2;
        int inputs = 2;
        int[] array_ocultas = {4};
        int outputs = 1;

        Red red = new Red(inputs, array_ocultas, outputs, RAZON_APRENDIZAJE, MOMENTO);

        int errores = 0;
        int intentos = 0;

        for(int etapa = 0; etapa < 8000; etapa++) {
            for(int i=0; i<4; i++) {
                ArrayList<Double> ins = new ArrayList<Double>();
                ins.add(entradas[i][0]);
                ins.add(entradas[i][1]);

                double[] arr = red.epoca(ins);
                
                for(int j=0; j<arr.length; j++) {
                    double error = Math.abs(salidas[i] - arr[j]);
                    System.out.printf("Ciclo %d Epoca: %d Entrada: %.0f %.0f Salida: %.4f Esperado: %.1f ERROR: %.4f%n",
                            etapa, i, entradas[i][0], entradas[i][1], arr[j], salidas[i], error);

                    if(error >= 0.1) errores++;
                    intentos++;
                    
                    double[] salidaEsperada = new double[1];
                    salidaEsperada[0] = salidas[i];
                    red.calibrar(salidaEsperada);
                }
            }
        }

        System.out.println("\n\nERRORES TOTALES: " + errores + ", intentos: " + intentos);
        
        // Prueba final
        System.out.println("\nPrueba final:");
        for(int i=0; i<4; i++) {
            ArrayList<Double> ins = new ArrayList<Double>();
            ins.add(entradas[i][0]);
            ins.add(entradas[i][1]);
            double[] arr = red.epoca(ins);
            System.out.printf("Entrada: %.0f %.0f -> Salida: %.4f (Esperado: %.1f)%n",
                    entradas[i][0], entradas[i][1], arr[0], salidas[i]);
        }
    }
}
