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

    public MarcoPagina buscarMenosUtilizado() {
        MarcoPagina marcoMenosUtilizado = null;

        for(MarcoPagina marco : marcos) {
            if (marcoMenosUtilizado == null) {
                marcoMenosUtilizado = marco;
            } else if (marcoMenosUtilizado.getEdad() > marco.getEdad()) {
                //En nuestra implementacion, el mas viejo es el mas usado
                marcoMenosUtilizado = marco;
            }
        }

        return marcoMenosUtilizado;
    }

    public MarcoPagina buscarLibre() {
        for(MarcoPagina marco : marcos) {
            if (marco.estaLibre()) {
                return marco;
            }
        }

        return null;
    }

    public synchronized EntradaPagina resolverFalla(EntradaPagina entrada){
        //encontrar marco a reemplazar
        MarcoPagina marco = buscarLibre();
        EntradaPagina entradaReemplazada = null;

        if(marco == null) {
            marco = buscarMenosUtilizado();
            entradaReemplazada = marco.getEntradaActual();
            entradaReemplazada.setPresente(false);
        }

        marco.setEntradaActual(entrada);
        entrada.setNumeroMarco(marco.getNumero());
        entrada.setPresente(true);

        return entradaReemplazada;
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
