package ns5d;

public class Stat {
	private long minTime = Long.MAX_VALUE;
	private long maxTime = 0;	
	private long sumTime = 0;
	private String maxID;
	private int count = 0;
	
	Stat(String ID, long time) {
		addStat(ID, time);
	}
	
	/**
	 * Добавить статистику по методу
	 * @param ID - номер метода
	 * @param time - время выполнения метода
	 */
	public void addStat(String ID, long time) {
		// minimal time
		if (time < this.minTime)
			this.minTime = time;
		
		// maximal time
		if (time > this.maxTime) {
			this.maxTime = time;
			this.maxID = ID;
		}		
		
		// total time
		this.sumTime += time;
		
		// method count
		this.count++;
	}
	
	public long getMinTime() {
		return this.minTime;
	}
	
	public long getMaxTime() {
		return this.maxTime;
	}
	
	public double getAvgTime() {
		return (this.count == 0) ? 0 : (this.sumTime / this.count);
	}
	
	public String getMaxID() {
		return this.maxID;
	}
	
	public String toString() {
		// milliseconds to seconds
		return String.format("min %.0f, max %.0f, avg %.0f, max id %s, count %d",
				(float)(this.minTime / 1000), (float)(this.maxTime / 1000), 
				this.getAvgTime() / 1000, this.maxID, this.count);
		
	}
}
