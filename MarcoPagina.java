public class MarcoPagina {
    private int numero;
    private Boolean referenciado;
    //Para edad, no se usa el bit mas significativo ya que este cambia el signo del entero
    //y despues habra problemas al buscar el marco mas viejo. Es decir, solo se usan 31 bits.
    private long edad;
    private EntradaPagina entradaActual;

    public MarcoPagina(int numero) {
        this.numero = numero;
        this.referenciado = false;
        this.edad = 0;
        this.entradaActual = null;
    }
    
    public void envejecer() {
        this.edad = this.edad / 0x2;
        if(Boolean.TRUE.equals(this.referenciado)) {
            this.edad = this.edad + 0x4000000000000000L;
        }
        this.referenciado = false;
    }

    //Getters y Setters
    public Boolean getReferenciado() {
        return referenciado;
    }
    public void setReferenciado(Boolean referenciado) {
        this.referenciado = referenciado;
    }

    public long getEdad() {
        return edad;
    }
    
    public void setEntradaActual(EntradaPagina entradaActual) {
        this.entradaActual = entradaActual;
    }

    public EntradaPagina getEntradaActual() {
        return entradaActual;
    }

    public Boolean estaLibre(){
        return this.entradaActual == null;
    }

    public int getNumero() {
        return numero;
    }
}
