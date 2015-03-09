package io.miti.quotes.app;

import java.io.Serializable;

/**
 * Java class to encapsulate the QuotationTbl table.
 *
 * @version 1.0
 */
public final class QuotationNode implements Serializable
{
  /**
   * Default serial version ID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The table column FirstName.
   */
  private String firstName;
  
  /**
   * The table column LastName.
   */
  private String lastName;
  
  /**
   * The table column Bio.
   */
  private String bio;
  
  /**
   * The table column Source.
   */
  private String source;
  
  /**
   * The table column Topic.
   */
  private String topic;
  
  /**
   * The table column Quotation.
   */
  private String quotation;
  
  
  /**
   * Default constructor.
   */
  public QuotationNode()
  {
    super();
  }
  
  
  /**
   * Get the value for firstName.
   *
   * @return the firstName
   */
  public String getFirstName()
  {
    return firstName;
  }
  
  
  /**
   * Update the value for firstName.
   *
   * @param pFirstName the new value for firstName
   */
  public void setFirstName(final String pFirstName)
  {
    firstName = pFirstName;
  }
  
  
  /**
   * Get the value for lastName.
   *
   * @return the lastName
   */
  public String getLastName()
  {
    return lastName;
  }
  
  
  /**
   * Update the value for lastName.
   *
   * @param pLastName the new value for lastName
   */
  public void setLastName(final String pLastName)
  {
    lastName = pLastName;
  }
  
  
  /**
   * Return the name as a single string.
   * 
   * @return the name
   */
  public String getName()
  {
    // This will hold the return value
    StringBuilder sb = new StringBuilder(100);
    
    // Default to no first name
    boolean nameFound = false;
    
    // Check the first name
    if ((firstName != null) && (firstName.length() > 0))
    {
      // First name exists, so append it
      sb.append(firstName);
      
      // Record that we have a first name
      nameFound = true;
    }
    
    // Check the last name
    if ((lastName != null) && (lastName.length() > 0))
    {
      // See if there was a first name
      if (nameFound)
      {
        // There was, so add a space
        sb.append(' ');
      }
      
      // Add the last name
      sb.append(lastName);
    }
    
    // Check the length
    if (sb.length() < 1)
    {
      return "Anonymous";
    }
    
    // Return the generated name
    return sb.toString();
  }
  
  
  /**
   * Get the value for bio.
   *
   * @return the bio
   */
  public String getBio()
  {
    return bio;
  }
  
  
  /**
   * Update the value for bio.
   *
   * @param pBio the new value for bio
   */
  public void setBio(final String pBio)
  {
    bio = pBio;
  }
  
  
  /**
   * Get the value for source.
   *
   * @return the source
   */
  public String getSource()
  {
    return source;
  }
  
  
  /**
   * Update the value for source.
   *
   * @param pSource the new value for source
   */
  public void setSource(final String pSource)
  {
    source = pSource;
  }
  
  
  /**
   * Get the value for topic.
   *
   * @return the topic
   */
  public String getTopic()
  {
    return topic;
  }
  
  
  /**
   * Update the value for topic.
   *
   * @param pTopic the new value for topic
   */
  public void setTopic(final String pTopic)
  {
    topic = pTopic;
  }
  
  
  /**
   * Get the value for quotation.
   *
   * @return the quotation
   */
  public String getQuotation()
  {
    return quotation;
  }
  
  
  /**
   * Update the value for quotation.
   *
   * @param pQuotation the new value for quotation
   */
  public void setQuotation(final String pQuotation)
  {
    quotation = pQuotation;
  }
  
  
  /**
   * Return this object as a string.
   * 
   * @return this object as a string
   */
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder(200);
    sb.append("Name = " + getName());
    sb.append('\n');
    sb.append("Bio = " + bio);
    sb.append('\n');
    sb.append("Source = " + source);
    sb.append('\n');
    sb.append("Topic = " + topic);
    sb.append('\n');
    sb.append("Quotation = " + quotation);
    sb.append('\n');
    return sb.toString();
  }
}
