package jp.ac.kanazawait.ep.mmotoki.sample;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstCar;
import jp.ac.kanazawait.ep.mmotoki.abst.AbstDriver;
import jp.ac.kanazawait.ep.mmotoki.abst.AbstNavigator;
import jp.ac.kanazawait.ep.mmotoki.addon.Logger;
import lejos.nxt.Button;
import lejos.nxt.Sound;

/**
 * Enterボタンを押す毎に,
 * 右エッジ・左エッジを切り替えてトレースするCar具象クラス
 * @author mmotoki
 *
 */
public class SampleCar extends AbstCar {
	private AbstNavigator navL = new LeftEdgeTracer();
	private AbstNavigator navR = new RightEdgeTracer();
	private AbstDriver driver = new SimpleDriver();
	private boolean direction = true;


	@Override
	public void run() {
		// 初期化処理
		start();

		// 20160509追加
		// ログ記録時のみ必要
		Logger logger = Logger.getInstance();
		logger.start();

		while(Button.ENTER.isDown());
		while (checker.getColorID()!=0 && !Button.ESCAPE.isDown()) {
			show();
			if(direction)changeDirection(navL.decision(checker, driver));
			if(!direction)changeDirection(navR.decision(checker, driver));
		}

		// 停止処理
		stop(driver);


		// 20170424 追加
		System.out.println("Press ENTER to send logs");
		System.out.println("Press the others to finish");
		int pressedButton = Button.waitForAnyPress();
		// ENTERが押された時だけログ送信
		if(pressedButton == Button.ID_ENTER){
			logger.stopThread();
			logger.SendLog();
		}
	}

	private void changeDirection(boolean ret){
		if(ret || Button.ENTER.isDown()){
			direction = !direction;
			Sound.beep();
			while(Button.ENTER.isDown());
		}
	}
	private void show() {

		/*
		LCD.clear();
		if(direction)LCD.drawString("turn = left", 0, 0);
		else LCD.drawString("turn = right", 0, 0);

		Color color = checker.getColor();
		LCD.clear();

		LCD.drawString("Color ID = ", 0, 2); // カラーIDを表示
		int id = color.getColor();
		LCD.drawInt(id, 11, 2);
		LCD.drawString(colorNames[id], 5, 3);

		LCD.drawString("R", 0, 4); // 赤成分を取得
		LCD.drawInt(color.getRed(), 1, 5);

		LCD.drawString("G", 4, 4); // 緑成分を取得
		LCD.drawInt(color.getGreen(), 5, 5);

		LCD.drawString("B", 8, 4); // 青成分を取得
		LCD.drawInt(color.getBlue(), 9, 5);

		*/
	}

	static String[] colorNames = { "Red", "Green", "Blue", "Yellow", "Magenta", "Orange", "White", "Black", "Pink",
			"Gray", "Light gray", "Dark gray", "Cyan" };
}