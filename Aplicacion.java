public class Aplicacion {
    public static void main(String[] args) {
        
        /** 
         * 
        */
        int numero = 0x40101010;
        System.out.println(numero);
        System.out.println(Integer.toBinaryString(numero));
        numero = numero / 0x2;
        System.out.println(numero);
        System.out.println(Integer.toBinaryString(numero));
    }
}