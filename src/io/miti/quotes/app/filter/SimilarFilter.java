package io.miti.quotes.app.filter;

/**
 * Provide a filter for search terms that only
 * accepts matches where the term passed to accept()
 * is similar to the string passed in the
 * constructor.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class SimilarFilter implements TermFilter
{
  /**
   * The source term.
   */
  private String term = null;
  
  /**
   * Whether to ignore the case.
   */
  private boolean ignoreCase = false;
  
  /**
   * The maximum distance to allow matches.
   */
  private int maxScore = 0;
  
  
  /**
   * Default constructor.
   */
  private SimilarFilter()
  {
    super();
  }
  
  
  /**
   * Initializes the filter with the source term and
   * whether to ignore case on searches.
   * 
   * @param word the source term
   * @param bIgnoreCase whether to ignore the case of string comparisons
   * @param maxDistance the maximum distance between the two terms
   */
  public SimilarFilter(final String word,
                       final boolean bIgnoreCase,
                       final int maxDistance)
  {
    term = word;
    ignoreCase = bIgnoreCase;
    maxScore = maxDistance;
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
    if ((word == null) && (term == null))
    {
      // They're both null
      return true;
    }
    else if ((word == null) || (term == null))
    {
      // One is null, the other is not
      return false;
    }
    
    // Neither is null, so check how to compare the strings
    int score = 0;
    if (ignoreCase)
    {
      // Ignore the case
      score = getScore(term.toUpperCase(), word.toUpperCase());
    }
    else
    {
      // Consider the case
      score = getScore(term, word);
    }
    
    // Return whether the computed score is at or below the threshold
    return (score <= maxScore);
  }
  
  
  /**
   * Returns the minimum of three integers.
   * 
   * @param a the first number
   * @param b the second number
   * @param c the third number
   * @return the minimum of three numbers
   */
  private static int minimum(final int a, final int b, final int c)
  {
    return (Math.min(a, Math.min(b, c)));
  }
  
  
  /**
   * Computes and returns the Levenshtein score to indicate
   * how similar the two strings are.
   * 
   * @param s First string to compare
   * @param t Second string to compare
   * @return the Levenshtein rating
   */
  private int getScore(final String s, final String t)
  {
    // Return the Levenshtein rating
    int[][] d; // matrix
    int n; // length of s
    int m; // length of t
    int i; // iterates through s
    int j; // iterates through t
    char s_i; // ith character of s
    char t_j; // jth character of t
    int cost; // cost
    
    // Step 1
    n = s.length();
    m = t.length();
    if (n == 0)
    {
      return m;
    }
    if (m == 0)
    {
      return n;
    }
    d = new int[n+1][m+1];
    
    // Step 2
    for (i = 0; i <= n; i++)
    {
      d[i][0] = i;
    }
    
    for (j = 0; j <= m; j++)
    {
      d[0][j] = j;
    }
    
    // Step 3
    for (i = 1; i <= n; i++)
    {
      s_i = s.charAt(i - 1);
      
      // Step 4
      for (j = 1; j <= m; j++)
      {
        t_j = t.charAt(j - 1);
        
        // Step 5
        if (s_i == t_j)
        {
          cost = 0;
        }
        else
        {
          cost = 1;
        }
        
        // Step 6
        d[i][j] = minimum(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);
      }
    }
    
    // Step 7
    return d[n][m];
  }
}
