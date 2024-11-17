
public enum UnitType {
	SOLDIER ("soldier", "media/soldier.png", 50, 50),
	MACHINE_GUN ("machineGun", "media/machineGun.png", 100, 100),
	GRENADE ("grenade", "media/grenade.png", 25, 25),
	ROCK ("rock", "media/rock.png", 50, 50);
	
	private final String name;
	private final String imagePath;
	private final int cost;
	private final int upgradeCost;
	
	UnitType(String name, String imagePath, int cost, int upgradeCost) {
		this.name = name;
		this.imagePath = imagePath;
		this.cost = cost;
		this.upgradeCost = upgradeCost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public int getUpgradeCost() {
		return this.upgradeCost;
	}
}
