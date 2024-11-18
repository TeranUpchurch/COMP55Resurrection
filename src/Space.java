import acm.graphics.GImage;

public class Space {
    private int row;
    private int col;
    private Unit unit; // The unit in this space, if any

    public Space(int row, int col) {
        this.row = row;
        this.col = col;
        this.unit = null;
    }

    public void placeUnit(Unit unit, int x, int y) {
        this.unit = unit;
        unit.setImagePos(x, y); // Sets a unit's graphical position
    }

    public void removeUnit() {
        if (unit != null) {
            unit.getImageFromUnit().setVisible(false); // Removes a graphical image
            this.unit = null;
        }
    }

    public boolean isOccupied() {
        return unit != null;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return (unit != null) ? "U" : " ";  // Simplified
    }
}