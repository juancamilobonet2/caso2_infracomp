public class EntradaPagina {
    private Boolean presente = false;
    private int numeroMarco;
    private int numeroPagina;
    private RAM ram;


    public EntradaPagina(int numeroPagina, RAM ram) {
        this.numeroPagina = numeroPagina;
        this.ram = ram;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setNumeroMarco(int numeroMarco) {
        this.numeroMarco = numeroMarco;
    }

    public int getNumeroMarco() {
        return numeroMarco;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void marcarReferenciado() {
        ram.marcarReferenciado(numeroMarco);
    }
}