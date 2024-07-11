
import javax.swing.text.*; // Import for DocumentFilter, FilterBypass, AttributeSet, BadLocationException

import java.nio.charset.Charset; // Import for Charset

public class UTF8DocumentFilter extends DocumentFilter {

  @Override
  public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
    super.insertString(fb, offset, string, attr);
    try {
      // Check if the string is already in UTF-8
      if (!Charset.forName("UTF-8").newEncoder().canEncode(string)) {
        // Convert the string to UTF-8 if necessary
        String utf8String = new String(string.getBytes(Charset.defaultCharset()), "UTF-8");
        super.insertString(fb, offset, utf8String, attr);
      }
    } catch (Exception e) {
      // Handle exceptions more informatively
      System.err.println("Error encoding text to UTF-8: " + e.getMessage());
      // You can also display an error message to the user here
    }
  }
}
