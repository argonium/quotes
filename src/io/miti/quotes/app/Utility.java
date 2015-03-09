package io.miti.quotes.app;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class has various utility methods used by the other classes.
 * 
 * @author Mike Wallace
 * @version 1.0
 */
public final class Utility
{
  /**
   * The list of special XML characters that need to be replaced
   * by XML entities.  This list and saXmlEntities must have the
   * same number of items.
   */
  private static char[] caXmlChars =
    new char[] {'<', '>', '&', '"', '\''};
  
  /**
   * The list of XML entities that replace special XML characters.
   * This list and caXmlChars must have the same number of items.
   */
  private static String[] saXmlEntities =
    new String[] {"&lt;", "&gt;", "&amp;", "&quot;", "&apos;"};
  
  
  /**
   * Default constructor.  Make it private so the class cannot
   * be instantiated.
   */
  private Utility()
  {
    super();
  }
  
  
  /**
   * Converts the special characters in a string into XML entities.
   * 
   * @param inputString the input string to parse
   * @return the input string with special XML characters replaced by entities
   */
  public static String convertToXml(final String inputString)
  {
    // Check for a null or empty string
    if ((inputString == null) || (inputString.length() < 1))
    {
      return "";
    }
    
    // Save the number of items in the two arrays
    final int nNumXmlChars = caXmlChars.length;
    final int nNumXmlEntities = saXmlEntities.length;
    
    // Verify the two lists have the same size
    if (nNumXmlChars != nNumXmlEntities)
    {
      throw new RuntimeException("The two XML arrays have different sizes.");
    }
    
    // See if they have any items
    if (nNumXmlChars == 0)
    {
      // Nothing to do
      return inputString;
    }
    
    // Iterate through the items
    boolean bNeedReplacing = false;
    for (int i = 0; (i < nNumXmlChars) && (!bNeedReplacing); ++i)
    {
      // Check for the existence of the current character
      bNeedReplacing = (inputString.indexOf(caXmlChars[i]) >= 0);
    }
    
    // See if we need to replace any characters
    if (!bNeedReplacing)
    {
      // None of the special characters were found
      return inputString;
    }
    
    // At least one of the special characters was found, so we
    // need to look at each character in the input string
    final int nLength = inputString.length();
    
    // Allocate our temporary buffer
    StringBuffer buf = new StringBuffer(Math.max(100, (nLength + 20)));
    
    // Loop through the string
    char ch;
    for (int i = 0; i < nLength; ++i)
    {
      // Save the current character
      ch = inputString.charAt(i);
      
      // Check for each special character
      boolean bMatchFound = false;
      for (int j = 0; (j < nNumXmlChars) && (!bMatchFound); ++j)
      {
        // Compare the current character against each special character
        if (ch == caXmlChars[j])
        {
          // We found a match, so append the entity corresponding
          // to this special character
          buf.append(saXmlEntities[j]);
          bMatchFound = true;
        }
      }
      
      // If no match was found, append the original character
      if (!bMatchFound)
      {
        buf.append(ch);
      }
    }
    
    // Return the buffer
    return buf.toString();
  }
  
  
  /**
   * Convert the specified string into an integer.
   * 
   * @param str the string to convert
   * @return the integer value for the string
   */
  private static int convertStringToInt(final String str)
  {
    int val = -1;
    
    try
    {
      val = Integer.parseInt(str);
    }
    catch (NumberFormatException nfe)
    {
      val = -1;
    }
    
    return val;
  }
  
  
  /**
   * Get the specified date as a String.
   * 
   * @param year the year (four digits)
   * @param month the month (two digits)
   * @param day the day (two digits)
   * @return the generated string
   */
  public static String getDateAsString(final String year,
                                       final String month,
                                       final String day)
  {
    // Get the numeric values
    final int nYear = convertStringToInt(year);
    final int nMonth = convertStringToInt(month);
    final int nDay = convertStringToInt(day);
    
    // Check that the input values are right
    if ((nYear < 0) || (nMonth < 0) || (nDay < 0))
    {
      return null;
    }
    
    // Construct a calendar
    Calendar cal = new GregorianCalendar(nYear, nMonth - 1, nDay);
    
    // Create our formatter
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
    
    // Apply the format to the date and return it
    return formatter.format(cal.getTime());
  }
  
  
  /**
   * Get the specified date as a String.
   * 
   * @param lTime the time to generate a String for
   * @return the generated string
   */
  public static String getDateAsString(final long lTime)
  {
    // Construct a calendar
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(lTime);
    
    // Create our formatter
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
    
    // Apply the format to the date and return it
    return formatter.format(cal.getTime());
  }
  
  
  /**
   * Update non-Western characters with the closest version.
   * 
   * @param line the string to check and modify
   * @return the (possibly modified) string
   */
  public static String updateBytes(final String line)
  {
    // Check the input parameter
    if (line == null)
    {
      return line;
    }
    
    // Get the line as a series of bytes
    byte[] data = line.getBytes();
    
    // Record whether any international characters were found
    boolean bInternationalCharsFound = false;

    // Iterate through the bytes to update the binary characters
    final int nSize = data.length;
    for (int nIndex = 0; nIndex < nSize; ++nIndex)
    {
      // Convert the byte to an integer
      final int i = (int) data[nIndex];

      // If it's not binary, skip to the next byte
      if ((i >= 0) && (i <= 127))
      {
        continue;
      }
      
      // We hit one
      bInternationalCharsFound = true;

      // Check the character
      switch (i)
      {
        case -28:    // a w/umlaut
        case -32:    // a w/`
        case -31:    // a w/' (forward accent)
          data[nIndex] = 'a';
          break;

        case -64:    // A w/accent
          data[nIndex] = 'A';
          break;

        case -23:    // e w/'
        case -22:    // e w/^
        case -21:    // e w/umlaut
        case -24:    // e w/`
          data[nIndex] = 'e';
          break;

        case -55:    // E w/'
          data[nIndex] = 'E';
          break;

        case -17:    // i w/umlaut
        case -20:    // i w/`
        case -19:    // i w/'
          data[nIndex] = 'i';
          break;

        case -50:    // I w/^
          data[nIndex] = 'I';
          break;

        case -79:    // +/-
        case -15:    // n w/tilda
          data[nIndex] = 'n';
          break;

        case -10:    // o w/umlaut
        case -13:    // o w/'
          data[nIndex] = 'o';
          break;

        case -42:    // O w/umlaut
          data[nIndex] = 'O';
          break;

        case -4:    // u w/umlaut
          data[nIndex] = 'u';
          break;
        
        case -76:    // '
          data[nIndex] = '\'';
          break;
        
        // Currently not handling -123 (ellipses)
        default:     // it's some other binary character
          break;
      }
    }
    
    // Check for international characters
    if (!bInternationalCharsFound)
    {
      // None found, so return the original string
      return line;
    }
    
    // Return a string from the set of bytes
    return (new String(data));
  }
  
  
  /**
   * Build the string for this object.
   *
   * @param value the tag value
   * @return the generated HTML string
   */
  private static String buildHtmlSnippet(final String value)
  {
    // Check the input
    if ((value == null) || (value.length() < 1))
    {
      return "";
    }
    
    // Declare our string builder
    StringBuilder sb = new StringBuilder(60);
    
    // Build the string
    sb.append(" &nbsp; &nbsp; ").append(Utility.putInTitleCase(value))
      .append("<br>\n");
    
    // Return the generated String
    return (sb.toString());
  }
  
  
  /**
   * Builds an HTML string representation of this object.
   * 
   * @param quote the object to generate as an HTML string
   * @return an HTML string representation of this object
   */
  public static String toHtmlString(final QuotationNode quote)
  {
    // Declare the string builder, used to build the output string
    StringBuilder sb = new StringBuilder(1000);
    
    // Build the output string
    sb.append("<html><body>");
    sb.append(quote.getQuotation()).append("<br>\n&nbsp; -")
      .append(quote.getName()).append("<br>\n");
    
    // Show the bio, if there is one
    sb.append(buildHtmlSnippet(quote.getBio()));
    
    // Show the source, if there is one
    sb.append(buildHtmlSnippet(quote.getSource()));
    
    sb.append("</body></html>");
    
    return sb.toString();
  }
  
  
  /**
   * Make sure the first character is upper-case.
   * 
   * @param str the input string
   * @return the modified string
   */
  public static String putInTitleCase(final String str)
  {
    // Check the input
    if ((str == null) || (str.length() < 1))
    {
      return "";
    }
    
    // Check if the first character is a non-letter or already uppercase
    char ch = str.charAt(0);
    if (!Character.isLetter(ch))
    {
      return str;
    }
    else if (Character.isUpperCase(ch))
    {
      return str;
    }
    
    // Make the first letter uppercase and then add the
    // rest of the string
    StringBuilder sb = new StringBuilder(str.length());
    sb.append(Character.toUpperCase(ch));
    if (str.length() > 1)
    {
      sb.append(str.substring(1));
    }
    
    return sb.toString();
  }
}
