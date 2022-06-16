package kml;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.Polygon;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import pojo.Outlet;
import util.Utils;

public class KMLReader {

  HashMap<String, Outlet> outletMap = new HashMap<>();
  Multimap<String, Polygon> serviceAreaMap = ArrayListMultimap.create();

  public Map<String, Outlet> read(String fileName) {

    // initilize the service area and outlet map
    readInternal(fileName);

    System.out.println("Matching Outlet with service area");

    try {
      // travers through service areas and find a suitable outlet
      for (String serviceareaName : serviceAreaMap.keySet()) {
        // an outlet can have multiple service areas so try matching
        if (outletMap.containsKey(serviceareaName)) {
          Outlet tempOutlet = outletMap.get(serviceareaName);
          Collection<Polygon> serviceAreas = serviceAreaMap.get(serviceareaName);
          for (Polygon p : serviceAreas) {
            tempOutlet.addServiceArea(p.getOuterBoundaryIs().getLinearRing().getCoordinates());
          }
        }
      }

    } catch (Exception ex) {

    }

    // System.out.println(outletMap);
    return outletMap;
  }

  private void readInternal(String fileName) {
    Kml kml = Kml.unmarshal(new File(fileName));
    Document document = (Document) kml.getFeature();

    // read all the outlets (points) and service areas (polygons)
    for (Feature folderFeature : document.getFeature()) {
      Folder folder = (Folder) folderFeature;
      System.out.println("FOLDER NAME #####: " + folder.getName());

      // Placemarks
      for (Feature placemarkFeature : folder.getFeature()) {
        Placemark placemark = (Placemark) placemarkFeature;

        Outlet outlet = new Outlet(Utils.removeUnwanted(placemark.getName()));

        Point point = null;

        System.out.println(
            "Place Marker Name : "
                + placemark.getName()
                + "   Class:   "
                + placemark.getGeometry().getClass());

        if (placemark.getGeometry() instanceof Point) point = (Point) placemark.getGeometry();
        if (point != null) {
          //  System.out.print(" ###### Lat/Long =>> " + point.getCoordinates());
          outlet.setLocation(point.getCoordinates());
          outletMap.put(Utils.removeUnwanted(outlet.getName()).toLowerCase(), outlet);

        } else if (placemark.getGeometry() instanceof Polygon
            || placemark.getGeometry() instanceof MultiGeometry) {

          Polygon serviceArea = null;

          System.out.println("POLYGON -- " + placemark.getName());
          if (placemark.getGeometry() instanceof Polygon) {
            serviceArea = (Polygon) placemark.getGeometry();
          } else if (placemark.getGeometry() instanceof MultiGeometry) {
            MultiGeometry mg = (MultiGeometry) placemark.getGeometry();

            //    System.out.println(
            //     placemark.getName() + "   ===============   MG --- " + mg.getGeometry().size());
            serviceArea = (Polygon) mg.getGeometry().get(0);
          }

          serviceAreaMap.put(Utils.removeUnwanted(placemark.getName()).toLowerCase(), serviceArea);
        }
      }
    }
  }
}
