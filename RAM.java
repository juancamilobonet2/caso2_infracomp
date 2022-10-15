import java.util.ArrayList;

public class RAM {
    public int cantidadMarcos;
    public ArrayList<MarcoPagina> marcos = new ArrayList<MarcoPagina>();

    public RAM(int cantidadMarcos) {
        this.cantidadMarcos = cantidadMarcos;

        for (int i = 0; i < cantidadMarcos; i++) {
            MarcoPagina marco = new MarcoPagina(i);
            marcos.add(marco);
        }
    }

    public synchronized MarcoPagina buscarMarco() {
        MarcoPagina marcoMasViejo = null;
        for (MarcoPagina marco : marcos) {
            if (marco.estaLibre()) {
                return marco;
            }

            if (marcoMasViejo == null){
                marcoMasViejo = marco;
            } else {
                if (marcoMasViejo.getEdad() >= marco.getEdad()) {
                    marcoMasViejo = marco;
                }
            }
            
        }
        return marcoMasViejo;
    }

    public synchronized void envejecerMarcos() {
        for (MarcoPagina marco : marcos) {
            marco.envejecer();
        }
    }

    public synchronized void marcarReferenciado(int numeroMarco) {
        marcos.get(numeroMarco).setReferenciado(true);
    }

    public synchronized void cambiarReferencia (int numeroMarco, EntradaPagina entrada) {
        marcos.get(numeroMarco).setEntradaActual(entrada);
    }
    
}
