
public enum UnitType {
	SOLDIER ("soldier", "media/soldier.png", 50),
	MACHINE_GUN ("machineGun", "media/machineGun.png", 100),
	GRENADE ("grenade", "media/grenade.png", 25),
	ROCK ("rock", "media/rock.png", 50);
	
	private final String name;
	private final String imagePath;
	private final int cost;
	
	UnitType(String name, String imagePath, int cost) {
		this.name = name;
		this.imagePath = imagePath;
		this.cost = cost;
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
}
