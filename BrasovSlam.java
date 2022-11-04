package BrasovSlam;
import robocode.*;
import robocode.util.Utils;

public class BrasovSlam extends AdvancedRobot
{
	public void run() {
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);
		setAdjustGunForRobotTurn(true);
		while(true) { turnRadarLeftDegrees(360); }
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		setTurnRadarRightRadians(Utils.normalRelativeAngle((getHeadingRadians() + e.getBearingRadians()) - getRadarHeadingRadians()));
		setTurnRightRadians(Utils.normalRelativeAngle((getHeadingRadians() + e.getBearingRadians()) - getHeadingRadians()));
		setAhead(100);
		double firePower = Math.min(500 / e.getDistance(), 3);
		double headOnBearing = getHeadingRadians() + e.getBearingRadians();
        double linearBearing = headOnBearing + Math.asin(e.getVelocity() / Rules.getBulletSpeed(firePower) * Math.sin(e.getHeadingRadians() - headOnBearing));
		setTurnGunRightRadians(Utils.normalRelativeAngle(linearBearing - getGunHeadingRadians()));
		if(getGunHeat() == 0) { setFire(firePower); }
	}	
}