import java.io.IOException;
import java.util.Map;
import kml.KMLReader;
import pojo.Outlet;
import writer.CSVWriter;

public class KMLTest {

  public static void main(String[] args) throws IOException {

    KMLReader kmlReader = new KMLReader();

    // String path = "/home/zargham/work/projects/KFC/data/map_data/KFC_Outlet_2_WithCodes.kml";
    // String path =
    // "/home/zargham/work/projects/KFC/data/map_data/KFC_Outlet_2_WithCodes_RemoveMultiGeometry.kml";
    // String path = "/home/zargham/work/projects/KFC/data/map_data/KFC - Area Redesigning
    // (Revise).kml";

    String path =
        "/home/zargham/work/projects/KFC/data/map_data/KFC - Area Redesigning_3.kml";
    Map<String, Outlet> outletMap = kmlReader.read(path);

    CSVWriter writer = new CSVWriter();
    writer.write("KFC-Outlets_NonDelivery4.csv", outletMap);
  }
}
