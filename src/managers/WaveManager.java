package managers;

import java.util.ArrayList;
import java.util.Arrays;

import events.Wave;
import scenes.Playing;

public class WaveManager {

	private Playing playing;
	private ArrayList<Wave> waves = new ArrayList<>();
	private int bloonSpawnTickLimit = (int) (60 * 0.575f);
	private int bloonSpawnTick = bloonSpawnTickLimit;
	private int bloonIndex, waveIndex;
	private int waveTickLimit = 60 * 5;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;
	
	public WaveManager(Playing playing) {
		this.playing = playing;
		createWaves();
	}
	
	public void update() {
		if(bloonSpawnTick < bloonSpawnTickLimit)
			bloonSpawnTick++;
		
		if(waveStartTimer) {
			waveTick++;
			if(waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}
	}
	
	public void increaseWaveIndex() {
		waveIndex++;
		waveTick = 0;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}
	
	public boolean isWaveTimerOver() {
		
		return waveTickTimerOver;
	}
	
	public void startWaveTimer() {
		waveStartTimer = true;
	}
	
	public int getNextBloon() {
		bloonSpawnTick = 0;
		return waves.get(waveIndex).getBloonList().get(bloonIndex++);
	}
	
	private void createWaves() {
		
//		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,2,2,2,2,2,2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2,2,2,2,2,2,2,2,2,2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,3,2,3,2,3,2,3,2,3,2,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3))));
	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public boolean isTimeForNewBloon() {
		return bloonSpawnTick >= bloonSpawnTickLimit;
	}
	
	public boolean isThereMoreBloonsInWave() {
		return bloonIndex < waves.get(waveIndex).getBloonList().size();
	}

	public boolean areThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}

	public void resetBloonIndex() {
		bloonIndex = 0;
		
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}
	
	public void reset() {
		waves.clear();
		createWaves();
		bloonIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		bloonSpawnTick = bloonSpawnTickLimit;
	}
}
