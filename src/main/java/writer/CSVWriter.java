package writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import pojo.Outlet;

public class CSVWriter {

  public void write(String fileName, Map<String, Outlet> outletMap) throws IOException {

    CSVFormat format =
        CSVFormat.newFormat(';')
            .withQuoteMode(QuoteMode.ALL)
            .withQuote('"')
            .withSkipHeaderRecord()
            .withRecordSeparator("\r\n");

    try (
    //
    BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
        CSVPrinter csvPrinter = new CSVPrinter(writer, format)
        //
        ) {

      for (Outlet outlet : outletMap.values()) {

        System.out.println(outlet.getName());
        if (outlet.getServiceAreas().size() > 0) {
          for (int i = 0; i < outlet.getServiceAreas().size(); i++) {
            csvPrinter.printRecord(
                outlet.getName(),
                outlet.getLocation().getLatitude(),
                outlet.getLocation().getLongitude(),
                outlet.getServiceAreas().get(i).toString());
          }

        } else {
          // in case branch does not have a service area
          csvPrinter.printRecord(
              outlet.getName(),
              outlet.getLocation().getLatitude(),
              outlet.getLocation().getLongitude(),
              "");
        }
      }

      csvPrinter.flush();
    }
  }
}
