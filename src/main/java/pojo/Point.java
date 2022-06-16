package pojo;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import java.util.List;

public class Point {

  private double latitude;
  private double longitude;

  public static Point getPoint(List<Coordinate> coordinates) {

    return Point.getPoint(coordinates.get(0));
  }

  public static Point getPoint(Coordinate coordinate) {

    return new Point(coordinate.getLatitude(), coordinate.getLongitude());
  }

  public Point(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return "{x:" + latitude + ",y:" + longitude + "}";
  }
}
