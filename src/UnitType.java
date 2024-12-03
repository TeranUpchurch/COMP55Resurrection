
public enum UnitType {
	SOLDIER ("soldier", "media/unit_soldier.png", "media/unitBar_soldier.png", 50, 50, 20, 100, 10),
	MACHINE_GUN ("machineGun", "media/unit_machineGun.png", "media/unitBar_machineGun.png", 100, 100, 10, 150, 20),
	GRENADE ("grenade", "media/unit_grenade.png", "media/unitBar_grenade.png", 25, 25, 10, 100, 20),
	ROCK ("rock", "media/unit_rock.png", "media/unitBar_rock.png", 50, 50, 10, 300, 10);
	
	private final String name;
	private final String imagePath;
	private final String unitBarImagePath;
	private final int cost;
	private final int upgradeCost;
	private final int frequency;
	private final int health;
	private final int cooldown;
	
	UnitType(String name, String imagePath, String unitBarImagePath, int cost, int upgradeCost, int frequency, int health, int cooldown) {
		this.name = name;
		this.imagePath = imagePath;
		this.unitBarImagePath = unitBarImagePath;
		this.cost = cost;
		this.upgradeCost = upgradeCost;
		this.frequency = frequency;
		this.health = health;
		this.cooldown = cooldown;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public String getUnitBarImagePath()
	{
		return this.unitBarImagePath;
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
	
	public int getCooldown()
	{
		return this.cooldown;
	}
}
