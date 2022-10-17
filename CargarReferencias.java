import java.util.ArrayList;

public class CargarReferencias extends Thread{
    private Envejecimiento envejecimiento;
    private RAM ram;
    private TLB tlb;
    private TP tp;
    private ArrayList<Integer> referencias; 

    private long tiempoTotal = 0; // en nanosegundos
    private int hits = 0;
    private int totales = 0;


    public CargarReferencias(Envejecimiento env, RAM ram, 
                            TLB tlb, TP tp, int cantidadMarcos, int tamanoTLB, 
                            ArrayList<Integer> referencias) {
        this.envejecimiento = env;
        this.ram = ram;
        this.tlb = tlb;
        this.tp = tp;
        this.referencias = referencias;
    }
    
    @Override
    public void run(){
        for(int ref : referencias) {
            //Primero buscar tlb
            //Si no esta, buscar tp
            //Si no esta, falla pagina
            //en todos los casos, actualizar tlb
            EntradaPagina entrada = tlb.buscarPagina(ref);
            tiempoTotal += 2;

            if(entrada==null || !entrada.getPresente()) {
                //No esta en tlb
                
                //Buscar en tp
                tiempoTotal += 30;
                entrada = tp.buscarPagina(ref);
                if(!entrada.getPresente()){
                    //No esta en tp
                    
                    System.out.println("Hay fallo");
                    System.out.println(ref);
                    //Falla pagina
                    EntradaPagina reemplazada = ram.resolverFalla(entrada); 
                    tlb.quitarEntrada(reemplazada);

                    tiempoTotal += 10000000;

                    //Segunda lectura en TP
                    tiempoTotal += 30;
                }
                else{
                    //Si esta en tp
                    //lectura en ram exitosa
                    tiempoTotal += 30;

                }
            }
            else{
                System.out.println("Se encontro en tlb : " + ref);
                //Si esta en tlb
                hits += 1;
                //lectura en ram exitosa
                tiempoTotal += 30;
            }
 
            //Actualizar tlb
            tlb.agregarPagina(entrada);

            //Marcar referenciado
            ram.marcarReferenciado(entrada.getNumeroMarco());
            totales += 1;
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        envejecimiento.stopRunning();


        System.out.println("Tiempo total: " + tiempoTotal);
        System.out.println("Hit ratio: " + (double)hits/totales);
    }
}
