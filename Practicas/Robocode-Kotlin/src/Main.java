import estrategias.EstrategiaAgresiva;

public class Main {
    public static void main(String[] args) {
        LaboRobot robot = new LaboRobot(new EstrategiaAgresiva());
        robot.run();
        // En tiempo de ejecución podrías cambiarla:
        // robot.setEstrategia(new EstrategiaDefensiva());
    }
}
