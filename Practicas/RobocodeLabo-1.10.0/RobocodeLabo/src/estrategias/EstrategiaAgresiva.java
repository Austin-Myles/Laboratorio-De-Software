package estrategias;

public class EstrategiaAgresiva implements Strategy {

    public void onScannedRobot() {
        fire(1);
    }

    public void onHitByBullet() {
        back(10);
    }

    public void onHitWall() {
        back(20);
    }

}
