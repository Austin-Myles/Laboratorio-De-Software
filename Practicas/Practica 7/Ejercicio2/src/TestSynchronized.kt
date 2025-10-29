class TestSynchronized(id: String) : Thread(id) {
    var frase: Array<String> = arrayOf("UNLP", "PÚBLICA", "AHORA", "Y", "SIEMPRE")
    override fun run() {
        synchronized(System.out) {
            for (palabra in frase) {
                println("${this.name} : ${palabra} ")
            }
        }
    }
    /*
    El synchronized(System.out) es una manera de asegurar acceso seguro al objeto System.out por parte de los hilos, ya que
    al ser un objeto, este es un recurso compartido. No es una practica recomendada dado que System.out es un objeto global, y su
    uso en sincronización puede llevar a efectos no deseados, como bloquearle el programa el uso del System.out....
     */

    /*
    - Hace un lock del recurso System.out, no permitiendo que otro pueda ejecutarlo hasta que el mismo hilo que primero lo obtuvo lo libere.
    En sí el synchronized(...), usa como un "monitor". Y todo lo que este dentro de las llaves sera la sección crítica.
     */

}
fun main(args: Array<String>) {
    val t1 = TestSynchronized("Thread 1")
    val t2 = TestSynchronized("Thread 2")
    val t3 = TestSynchronized("Thread 3")
    t1.start()
    t2.start()
    t3.start()
}