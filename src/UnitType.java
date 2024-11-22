
public enum UnitType {
	SOLDIER ("soldier", "media/soldier.png", 50, 50, 20, 100),
	MACHINE_GUN ("machineGun", "media/machineGun.png", 100, 100, 10, 150),
	GRENADE ("grenade", "media/grenade.png", 25, 25, 10, 1),
	ROCK ("rock", "media/rock.png", 50, 50, 10, 300);
	
	private final String name;
	private final String imagePath;
	private final int cost;
	private final int upgradeCost;
	private final int frequency;
	private final int health;
	
	UnitType(String name, String imagePath, int cost, int upgradeCost, int frequency, int health) {
		this.name = name;
		this.imagePath = imagePath;
		this.cost = cost;
		this.upgradeCost = upgradeCost;
		this.frequency = frequency;
		this.health = health;
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
	
	public int getFrequency() {
		return this.frequency;
	}
	
	public int getHealth() {
		return this.health;
	}
}
