package io.miti.quotes.app.filter;

/**
 * This defines the TermFilter interface, used to provide a
 * filter for terms returned by the search.
 * 
 * @author mwallace
 * @version 1.0
 */
public interface TermFilter
{
  /**
   * Determines if the term matches the source term.
   * 
   * @param word the term to compare to the source term
   * @return whether the terms match
   */
  boolean accept(String word);
}
