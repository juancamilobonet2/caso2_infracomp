import java.util.ArrayList;

public class TP {
    private ArrayList<EntradaPagina> tablaPaginas = new ArrayList<EntradaPagina>();
    private RAM ram;

    public TP(RAM ram) {
        this.ram = ram;

        for (int i = 0; i<64; i++) {
            EntradaPagina entrada = new EntradaPagina(i, ram);
            tablaPaginas.add(entrada);
        }
    }

    public EntradaPagina buscarPagina(int numeroPagina) {
        EntradaPagina entrada = tablaPaginas.get(numeroPagina);
        if (entrada.getPresente()) {
            entrada.marcarReferenciado();
        }
        return entrada;
    }

    public void cambiarPagina(int numeroPagina, int numeroMarco) {
        EntradaPagina entrada = tablaPaginas.get(numeroPagina);
        entrada.setPresente(true);
        entrada.setNumeroMarco(numeroMarco);
        ram.cambiarReferencia(numeroMarco, entrada);
    }

}
