package logic;

import java.io.Serializable;
import java.util.Random;

/**
 * Modelliert ein Schiff.
 */
public class Ship implements Serializable {
	private final String[] names = {"U-Boot", "Zerstörer", "Kreuzer", "Schlachtschiff"};
	private int xPos;
	private int yPos;
	private Direction direction;
	private final int size;
	private int hits;
	
	/**
	 * @param xPos x-Koordinate des Schiffs.
	 * @param yPos y-Koordinate des Schiffs.
	 * @param direction Richtung in die das Schiff zeigt.
	 * @param size Größe des Schiffs.
	 */
	public Ship(int xPos, int yPos, Direction direction, int size) {
		hits = 0;
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
		this.size = size;
	}
	
	/**
	 * Erzeugt ein Standard-{@link Ship}.
	 *
	 * @param x x-Koordinate des Schiffs.
	 * @param y y-Koordinate des Schiffs.
	 * @return Ein bereits versenktes Schiff.
	 */
	public static Ship defaultShip(int x, int y) {
		return new Ship(x, y, Direction.north, 2);
	}
	
	/**
	 * Erzeugt ein {@link Ship} welches bereitzs versenkt wurde.
	 *
	 * @param x x-Koordinate des Schiffs.
	 * @param y y-Koordinate des Schiffs.
	 * @return Ein bereits versenktes Schiff.
	 */
	public static Ship defaultSunkenShip(int x, int y) {
		Ship ship = defaultShip(x, y);
		ship.hits = 2;
		return ship;
	}
	
	/**
	 * Copy-Konstruktor.
	 *
	 * @param ship Das zu kopierende Schiff.
	 */
	public Ship(Ship ship) {
		this(ship.xPos, ship.yPos, ship.direction, ship.size);
	}
	
	/**
	 * Gibt zurück, ob das Schiff bereits versenkt wurde.
	 *
	 * @return {@code true}, falls das Schiff noch nicht versenkt wurde. Sonst {@code false}.
	 */
	public boolean isAlive() {
		return hits < size;
	}
	
	/**
	 * Gibt den Namen des Schiffs zurück. Aus {@link #names}.
	 *
	 * @return Der Name des Schiffs.
	 */
	public String getName() {
		return names[size - 2];
	}
	
	/**
	 * @return Die x-Koordinate des Schiffs. (Koordinaten beziehen sich immer auf den Kopf des Schiffs)
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * @return Die y-Koordinate des Schiffs. (Koordinaten beziehen sich immer auf den Kopf des Schiffs)
	 */
	public int getYPos() {
		return yPos;
	}
	
	/**
	 * @return Die richtung in die das Schiff zeigt.
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * @return Die Größe des Schiffs.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Wird aufgerufen, wenn das Schiff getroffen wird.
	 */
	public void hit() {
		hits += 1;
	}
	
	/**
	 * Hilfsmethode für {@link LocalPlayer#randomShipPlacment()}. Randomisiert alle Attribute des Schiffs.
	 *
	 * @param mapSize Die größe des Spielfelds.
	 */
	public void randomize(int mapSize) {
		Random rnd = new Random();
		xPos = rnd.nextInt(mapSize);
		yPos = rnd.nextInt(mapSize);
		direction = Direction.values()[rnd.nextInt(Direction.values().length)];
	}
	
	/**
	 * Vergleicht ein Schiff mit einem anderen Objekt.
	 *
	 * @param obj Das zu vergleichende Objekt.
	 * @return {@code true}, falls {@code obj} ein Schiff ist und es diesem Schiff entspricht. Sonst {@code false}.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Ship)) return false;
		Ship s = (Ship) obj;
		return (s.xPos == xPos && s.yPos == yPos && s.direction == direction && s.size == size && s.hits == hits);
	}
	
	/**
	 * Hasht das Schiff.
	 *
	 * @return Der Hash des Schiffs.
	 */
	@Override
	public int hashCode() {
		return ((xPos + size + yPos) * direction.hashCode());
	}
	
	/**
	 * Gibt eine String-Repräsentation des Schiffs zurück.
	 *
	 * @return Die String -Repräsentation des Schiffs.
	 */
	@Override
	public String toString() {
		return getName();
	}
}

