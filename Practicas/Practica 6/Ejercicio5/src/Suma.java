public class Suma {
    public static void main(String[] args){
        int suma=0;
        for(int i=0;i<=args.length -1 ;i++)
            try {
                suma+= Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.out.println("El valor '" + args[i] + "' no es un entero...");
            }
        System.out.print("La suma es: " + suma);
    }
}