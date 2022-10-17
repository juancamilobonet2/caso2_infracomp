import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args) {
        System.out.println("Bienvenido al simulador de memoria virtual");
        System.out.println("Ingrese el número de entradas para el TLB: ");
        Scanner sc = new Scanner(System.in);
        int tlbSize = sc.nextInt();
        System.out.println("Ingrese el número de marcos de página de la RAM que el sistema le asigna al proceso: ");
        int ramSize = sc.nextInt();
        System.out.println("Ingrese el nombre del archivo en donde se encuentran las referencias del proceso: ");
        String fileName = sc.next();
        sc.close();
        try {
            File file = new File("./pruebas/" + fileName);
            Scanner fileReader = new Scanner(file);
            ArrayList<Integer> referencias = new ArrayList<Integer>();

            while(fileReader.hasNextLine()) {
                referencias.add(fileReader.nextInt());
            }
            fileReader.close();

            TLB tlb = new TLB(tlbSize);
            RAM ram = new RAM(ramSize);
            TP tp = new TP(ram);
            Envejecimiento envejecimiento = new Envejecimiento(ram);
            CargarReferencias cargarReferencias = new CargarReferencias(
                envejecimiento,
                ram,
                tlb,
                tp,
                ramSize,
                tlbSize,
                referencias
            );

            envejecimiento.start();
            cargarReferencias.start();

        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo");
        }
    }
}