package day7;

public interface Navigatable {
    public Navigatable navigateTo(String directoryName);

    public void createMap(MapCreator MapCreator);
}
