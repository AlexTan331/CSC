package edu.cuny.csi.csc330.lab4;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import edu.cuny.csi.csc330.util.Randomizer;

public class AirConditioner {

	protected static final String[] MODE = {"Fan mode", "Cool mode", "Warm mode", "Dry mode"};
	protected static final String[] SPEED_PRESETS = {"slow", "medium", "fast"};
	protected static final String[] DIRECTION_PRESETS = {"up-down", "left-right"};
	protected static final int MIN_TEMPERATURE = 72;
	protected static final int MAX_TEMPERATURE = 84;
	protected final static int MAX_SELECTIONS = MAX_TEMPERATURE - MIN_TEMPERATURE + 1;
	protected static final int DEFAULT_TEMPERATURE = 75;
	protected static final String DEFAULT_MODE = "Cool mode";
	protected static final String DEFAULT_SPEED = "medium";
	protected static final String DEFAULT_DIRECTION = "up-down";
	protected static final boolean DEFAULT_SHIFT = true;
	protected static final int DEFAULT_TIMER = 0;
	protected static final char DEGREE_SIGN = '\u00B0';
	
	private boolean powerState;
	private String selectedMode;
	private int selectedTemperature;
	private int [] temperaturePresets;
	private String selectedSpeed;
	private String selectedDirection;
	private Date firstTimeOn;
	private Date lastTimeOn;
	private String productNumber;
	private int selectedTimer;
	private boolean isShift;

	public AirConditioner() {
		init();
	}

	private void init() {
		// product number
		Integer rand = Randomizer.generateInt(111111, 999999);
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'A');		//generate a random capital letter
		this.productNumber = "ACP" + rand.toString() + c;
		
		temperaturePresets = new int[MAX_SELECTIONS];
		int temp = MIN_TEMPERATURE;
		for(int i = 0 ; i  < MAX_SELECTIONS; i++ ) {
			temperaturePresets[i] = temp;
			temp++;
		}
	}

	public void on() {
		Date now = new Date();

		//do the following when the first time turn on the instance
		if (firstTimeOn == null) {
			
			firstTimeOn = now;
			selectedMode = DEFAULT_MODE;
			selectedTemperature = DEFAULT_TEMPERATURE;
			selectedSpeed = DEFAULT_SPEED;
			selectedDirection = DEFAULT_DIRECTION;
			isShift = DEFAULT_SHIFT;
		}
		
		selectedTimer = DEFAULT_TIMER;
		powerState = true;
		lastTimeOn = now;

	}

	public void off() {
		powerState = false;
		selectedTimer = 0;
	}

	public boolean isOn() {
		return powerState == true;
	}
	
	public int getTemperature() {
		return selectedTemperature;
	}

	public void setTemperature(int temperature) {
		if (temperature <= MAX_TEMPERATURE && temperature >= MIN_TEMPERATURE)
			this.selectedTemperature = temperature;
		else 
			System.err.println("Temperature out of range!");
	}
	
	public void increaseTemperature() {
		if (this.selectedTemperature < MAX_TEMPERATURE)
			this.selectedTemperature += 1;
	}

	public void decreaseTemperature() {
		if (this.selectedTemperature > MIN_TEMPERATURE)
			this.selectedTemperature -= 1;
	}
	
	public String getMode() {
		return selectedMode;
	}

	public void setMode(String mode) {
		switch (mode) {
		case "fan":
			this.selectedMode = MODE[0];		//Fan mode
			break;
		case "cool":
			this.selectedMode = MODE[1];		//Cool mode
			break;
		case "warm":
			this.selectedMode = MODE[2];		//Warm mode
			break;
		case "dry":
			this.selectedMode = MODE[3];		//dry mode
			break;
		default:
			System.err.println("mode does not exists!");
		}
	}
	
	public String getSpeed() {
		return selectedSpeed;
	}

	public void setSpeed(String speed) {
		this.selectedSpeed = speed;
	}
	
	public String getDirection() {
		return selectedDirection;
	}

	public void setDirection(String direction) {
		if (isShift != true) {
			isShift = true;
		}
		this.selectedDirection = direction;

	}
	
	public int getTimer() {
		return selectedTimer;
	}

	public void setTimer(int timer) {
		this.selectedTimer = timer;
		for (int i = timer; i > 0; i--) {
			try {
				Thread.sleep(500 * timer);		//simulating timer, half a sec equals one hours in real life
			} catch (InterruptedException e) {
				;
			}
			System.out.println("Air conditioner will be turned off after " + i + " hour(s) ...\n");
		}

		powerState = false;
	}
	
	
	public void toggleShifting() {
		isShift = (isShift == true) ? false:true;	
		if (isShift == false) selectedDirection = null;
	}
	
	

	@Override
	public String toString() {
		return "AirConditioner [powerState=" + powerState + ", productNumber=" + productNumber + ", selectedMode="
				+ selectedMode + ", isShift=" + isShift + ", selectedTemperature=" + selectedTemperature
				+ ", \ntemperaturePresets(" + DEGREE_SIGN + "F)="+ Arrays.toString(temperaturePresets) + ", selectedSpeed=" + selectedSpeed
				+ ", selectedDirection=" + selectedDirection + ", \nfirstTimeOn=" + firstTimeOn + ", lastTimeOn="
				+ lastTimeOn + ", selectedTimer(h)=" + selectedTimer + "]";
	}

	public static void main(String[] args) {
		AirConditioner AC = new AirConditioner();
		System.out.println("New Instance\n" + AC + "\n");
		
		// turn it on
		AC.on(); 
		System.out.println("Turned On\n" + AC + "\n");
		
		
		AC.setMode("fan");
		AC.toggleShifting();
		AC.setTimer(3);
		System.out.println("Changed Mode, turned off after 3 hours\n" + AC + "\n");

		
		// turn it on
		AC.on(); 
		AC.setMode("warm");
		AC.increaseTemperature();
		AC.setDirection("left-right");
		AC.setSpeed("fast");
		System.out.println("Changed Mode\n" + AC + "\n");
		
		AC.off();
		System.out.println("Turned Off\n" + AC + "\n");
	}

}
