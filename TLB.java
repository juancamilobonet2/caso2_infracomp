
import java.util.LinkedList;

public class TLB {
    private LinkedList<EntradaPagina> tlb = new LinkedList<EntradaPagina>();
    private int cantidadEntradas;

    public TLB(int cantidadEntradas) {
        this.cantidadEntradas = cantidadEntradas;
    }

    public EntradaPagina buscarPagina(int numeroPagina) {
        for (EntradaPagina entrada : tlb) {
            if (entrada.getNumeroPagina() == numeroPagina) {
                entrada.marcarReferenciado();
                return entrada;
            }
        }
        return null;
    }

    public void agregarPagina(EntradaPagina entrada) {
        if (tlb.size() == cantidadEntradas) {
            tlb.removeLast();
        }
        tlb.addFirst(entrada);
    }

    public void cambiarPagina(int numeroPagina, EntradaPagina entrada) {
        tlb.set(numeroPagina, entrada);
    }

    public void quitarEntrada(EntradaPagina reemplazada) {
        tlb.remove(reemplazada);
    }
}

