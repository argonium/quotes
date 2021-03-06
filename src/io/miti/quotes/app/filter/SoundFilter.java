package io.miti.quotes.app.filter;

/**
 * Provide a filter for search terms that only
 * accepts matches where the term passed to accept()
 * sounds like the term passed in the constructor.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class SoundFilter implements TermFilter
{
  /**
   * The soundex code for source term.
   */
  private String termCode = null;
  
  /**
   * Whether to ignore the case.
   */
  private boolean ignoreCase = false;
  
  
  /**
   * Default constructor.
   */
  private SoundFilter()
  {
    super();
  }
  
  
  /**
   * Initializes the filter with the source term and
   * whether to ignore case on searches.
   * 
   * @param word the source term
   * @param bIgnoreCase whether to ignore the case of string comparisons
   */
  public SoundFilter(final String word, final boolean bIgnoreCase)
  {
    termCode = getSoundexCode(word);
    ignoreCase = bIgnoreCase;
  }
  
  
  /**
   * Determines if the term matches the source term.
   * 
   * @param word the term to compare to the source term
   * @return whether the terms match
   */
  public boolean accept(final String word)
  {
    // Compute the soundex code
    final String wordCode = getSoundexCode(word);
    
    // Neither is null, so check how to compare the strings
    if (ignoreCase)
    {
      // Ignore the case
      return (wordCode.equalsIgnoreCase(termCode));
    }
    else
    {
      // Consider the case
      return (wordCode.equals(termCode));
    }
  }
  
  
  /**
   * Returns the integer value for a character.
   * 
   * @param ch the character to get the value for
   * @return the value for the specified character
   */
  private static int getIntValue(final char ch)
  {
    int n = 0;
    
    switch (ch)
    {
      case 'b':
      case 'f':
      case 'p':
      case 'v':
      {
        n = 1;
        break;
      }
    
      case 'c':
      case 'g':
      case 'j':
      case 'k':
      case 'q':
      case 's':
      case 'x':
      case 'z':
      {
        n = 2;
        break;
      }
    
      case 'd':
      case 't':
      {
        n = 3;
        break;
      }
    
      case 'l':
      {
        n = 4;
        break;
      }
    
      case 'm':
      case 'n':
      {
        n = 5;
        break;
      }
    
      case 'r':
      {
        n = 6;
        break;
      }
    
      default:
        n = 0;
    }
    
    return n;
  }
  
  
  /**
   * Calculates the Soundex code for a string.
   * 
   * @param sInput the input string
   * @return the Soundex code for the string
   */
  private static String getSoundexCode(final String sInput)
  {
    // Check the input
    if ((sInput == null) || (sInput.length() < 1))
    {
      return "";
    }
    
    // Declare our string variable to hold the soundex code
    StringBuilder buf = new StringBuilder(10);
    
    // The first character of the string is the start
    // of the soundex code
    buf.append(sInput.charAt(0));
    
    // Convert the string to lower case
    final String sWord = sInput.toLowerCase();
    
    // Save the value of the first character, to check
    // for duplicates later
    int nPrevValue = getIntValue(sWord.charAt(0));
    
    // Initialize this variable
    int nCurrValue = -1;
    
    // Save the length of the string
    final int nLen = sWord.length();
    
    // Iterate over each character in the word, until
    // we have enough to fill the soundex code (the
    // form is A999 - a character followed by 3 digits).
    for (int i = 1; (i < nLen) && (buf.length() < 4); ++i)
    {
      // Get the integer value for the current character
      nCurrValue = getIntValue(sWord.charAt(i));
      
      // Make sure the current value is not a duplicate of
      // the previous value, and the current value is non-zero
      if ((nCurrValue != nPrevValue) && (nCurrValue != 0))
      {
        buf.append(Integer.toString(nCurrValue));
      }
      
      // Save the current value as the previous value
      nPrevValue = nCurrValue;
    }
    
    // Check the length of the string
    int nSize = buf.length() - 4;
    if (nSize < 0)
    {
      // The string is too short, so append zeros
      while (nSize < 0)
      {
        buf.append("0");
        ++nSize;
      }
    }
    
    // Return the generated soundex code for the input string
    return buf.toString();
  }
}
