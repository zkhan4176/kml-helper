package pojo;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class ServiceArea {

  private ArrayList<Point> polygon = new ArrayList<>();

  public static ServiceArea create(List<Coordinate> polygonCoordinates) {

    ServiceArea sa = new ServiceArea();

    for (Coordinate coordinate : polygonCoordinates) {
      sa.addPoint(Point.getPoint(coordinate));
    }
    return sa;
  }

  private void addPoint(Point point) {
    polygon.add(point);
  }

  public ArrayList<Point> getPolygon() {
    return polygon;
  }

  public void setPolygon(ArrayList<Point> polygon) {
    this.polygon = polygon;
  }

  @Override
  public String toString() {
    return "[" + polygon + "]";
  }
}
