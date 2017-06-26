package jp.ac.kanazawait.ep.mmotoki.sample;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstDriver;
import jp.ac.kanazawait.ep.mmotoki.abst.AbstNavigator;
import jp.ac.kanazawait.ep.mmotoki.addon.SensorChecker;

/**
 * 左エッジ走行のためのNavigatorクラス
 * @author mmotoki
 *
 */
public class LeftEdgeTracer extends AbstNavigator {

	@Override
	public boolean decision(SensorChecker checker, AbstDriver driver) {
		int colorID = checker.getColorID();
		// white
		if(colorID == 6 || colorID == 8) {
			driver.turnLeft();
		}
		// black
		else if(colorID == 7) {
			driver.turnRight();
		}
		else if(colorID == 1 || colorID == 2){
			driver.turnLeftSlack();
			return true;
		}
		else {
			driver.goStraight();
		}
		return false;
	}

}