public class EntradaPagina {
    private Boolean presente = false;
    private int numeroMarco;
    private int numeroPagina;

    public EntradaPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
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
}