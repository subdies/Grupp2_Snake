// Klasser som ärver från GameObject
class Point extends GameObject {
    public Point(int x, int y) {
        super(x, y);
    }

    public double distance(Point other) {
        int dx = this.x - other.getX();
        int dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}