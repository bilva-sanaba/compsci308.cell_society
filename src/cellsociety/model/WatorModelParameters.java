package cellsociety.model;
public class WatorModelParameters {
	private int energyMax;
	private int sharkBreedPeriod;
	private int fishBreedPeriod;
	private int fishEnergy;
    public static final int DEFAULT_FISH_BREED_PERIOD = 5;
    public static final int DEFAULT_FISH_ENERGY = 5;
    public static final int DEFAULT_SHARK_BREED_PERIOD = 25;
    public static final int DEFAULT_SHARK_ENERGY = 5;
    
	public WatorModelParameters() {
		this.energyMax = DEFAULT_SHARK_ENERGY ;
		this.sharkBreedPeriod = DEFAULT_SHARK_BREED_PERIOD;
		this.fishBreedPeriod = DEFAULT_FISH_BREED_PERIOD ;
		this.fishEnergy =  DEFAULT_FISH_ENERGY ;
	}
	public int getEnergyMax() {
		return energyMax;
	}
	public void setEnergyMax(int energyMax) {
		this.energyMax = energyMax;
	}
	public int getSharkBreedPeriod() {
		return sharkBreedPeriod;
	}
	public void setSharkBreedPeriod(int sharkBreedPeriod) {
		this.sharkBreedPeriod = sharkBreedPeriod;
	}
	public int getFishBreedPeriod() {
		return fishBreedPeriod;
	}
	public void setFishBreedPeriod(int fishBreedPeriod) {
		this.fishBreedPeriod = fishBreedPeriod;
	}
	public int getFishEnergy() {
		return fishEnergy;
	}
	public void setFishEnergy(int fishEnergy) {
		this.fishEnergy = fishEnergy;
	}
}