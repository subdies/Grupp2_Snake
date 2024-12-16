public class GameObjectFactory {
    public static GameObject createObject(String type, int x, int y) {
        switch (type) {
            case "Point":
                return new Point(x, y);
            case "Apple":
                return new Apple(x, y);
            default:
                return null;
        }
    }
}