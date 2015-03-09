package io.miti.quotes.app.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide a filter for search terms that only
 * accepts matches where the parameter to accept()
 * contains at least one of the phrases in the
 * string used to initialize this class, where
 * a phrase is either a word delimited by spaces
 * or one or more words surrounded by quotes.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class ContainsSomeFilter implements TermFilter
{
  /**
   * Whether to ignore the case.
   */
  private final boolean ignoreCase;
  
  /**
   * The list of terms.
   */
  private final List<String> terms;
  
  
  /**
   * Default constructor.
   */
  private ContainsSomeFilter()
  {
    super();
    ignoreCase = false;
    terms = new ArrayList<String>(0);
  }
  
  
  /**
   * Initializes the filter with the source term and
   * whether to ignore case on searches.
   * 
   * @param word the source term
   * @param bIgnoreCase whether to ignore the case of string comparisons
   */
  public ContainsSomeFilter(final String word,
                            final boolean bIgnoreCase)
  {
    super();
    
    // Save whether to ignore the case
    ignoreCase = bIgnoreCase;
    
    // Save the String parameter, after processing
    terms = FilterUtility.parseIntoPhrases(word);
  }
  
  
  /**
   * Determines if the term matches the source term.
   * 
   * @param word the term to compare to the source term
   * @return whether the terms match
   */
  @Override
  public boolean accept(final String word)
  {
    // Check the input
    if (word == null)
    {
      return false;
    }
    
    // See if we need to ignore case
    final String target = ((ignoreCase) ? word.toLowerCase() : word);
    
    // Iterate over the list of phrases to see if target
    // contains any of them
    boolean found = false;
    final int size = terms.size();
    for (int i = 0; (i < size) && (!found); ++i)
    {
      // See if the current array element contains target
      if (target.indexOf(terms.get(i)) >= 0)
      {
        found = true;
      }
    }
    
    // Return whether we found one of the strings
    return found;
  }
}
