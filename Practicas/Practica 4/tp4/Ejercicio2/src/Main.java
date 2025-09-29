public class Main {
    public static void main(String[] args) {
        //i
        //Veterinaria <Animal> vet = new Veterinaria<Gato>(); // ERROR, el new deberia tener Veterinaria<Animal>();
        //ii
        //Veterinaria <Gato> vet2 = new Veterinaria<Animal>(); // ERROR, el new deberia tener Veterinaria<Gato>();
        //iii
        Veterinaria <?> vet3 = new Veterinaria<Gato>();
        //vet3.setAnimal(new Gato()); //Es necesario cambiar el tipo del parametro a Gato...
        //iv
        Veterinaria vet4 = new Veterinaria();
        vet4.setAnimal(new Perro());
        //v
        //Veterinaria vet5 = new Veterinaria<?>(); //No es posible instanciar ?...
        //vi
        Veterinaria <? extends Animal> vet6 = new Veterinaria<Gato>();
    }
}
