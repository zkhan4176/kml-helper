package pojo;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class Outlet {

  private String name;
  private Point location;

  private List<ServiceArea> serviceAreas = new ArrayList<>();

  public Outlet(String name) {

    this.name = name;
  }

  public Point getLocation() {
    return location;
  }

  public void setLocation(Point location) {
    this.location = location;
  }

  public List<ServiceArea> getServiceAreas() {
    return serviceAreas;
  }

  public void addServiceArea(List<Coordinate> polygonCoordinates) {

    serviceAreas.add(ServiceArea.create(polygonCoordinates));

    System.out.println("Name: " + name + " service areas: " + serviceAreas.size());
  }

  public void setLocation(List<Coordinate> coordinates) {

    this.setLocation(Point.getPoint(coordinates));
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Outlet{"
        + "name='"
        + name
        + '\''
        + ", location="
        + location
        + ", polygon="
        + serviceAreas
        + '}';
  }
}
