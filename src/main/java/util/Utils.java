package util;

public class Utils {

  public static String removeUnwanted(String text) {
    // trim and replace new line
    return text.trim()
        // strips off all non-ASCII characters
        .replaceAll("[^\\x00-\\x7F]", "")
        // erases all the ASCII control characters
        .replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "")
        // removes non-printable characters from Unicode
        .replaceAll("\\p{C}", "");
  }
}
