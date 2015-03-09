package io.miti.quotes.app.filter;

import java.util.regex.Pattern;

/**
 * Provide a filter for search terms that only
 * accepts matches where the term passed to accept()
 * matches the regular expression string passed in
 * the constructor.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class RegexFilter implements TermFilter
{
  /**
   * The pattern for the source term.
   */
  private Pattern pattern = null;
  
  
  /**
   * Default constructor.
   */
  private RegexFilter()
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
  public RegexFilter(final String word, final boolean bIgnoreCase)
  {
    // Check the input
    if (word == null)
    {
      pattern = null;
    }
    else
    {
      // Check if we're ignoring case
      if (bIgnoreCase)
      {
        pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
      }
      else
      {
        pattern = Pattern.compile(word);
      }
    }
  }
  
  
  /**
   * Determines if the term matches the source term.
   * 
   * @param word the term to compare to the source term
   * @return whether the terms match
   */
  public boolean accept(final String word)
  {
    // Check the two terms for nullness
    if ((word == null) && (pattern == null))
    {
      // They're both null
      return true;
    }
    else if ((word == null) || (pattern == null))
    {
      // One is null, the other is not
      return false;
    }
    
    // Use the regex package to compare words
    return (pattern.matcher(word).matches());
  }
}
