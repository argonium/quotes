package io.miti.quotes.app;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * The model for drawing the results table.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class ResultsTableModel extends AbstractTableModel
{
  /**
   * Set up the version number.
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * The names of the columns.
   */
  private static final String[] columnNames = {"Name", "Topic"};
  
  /**
   * The data stored in each row.
   */
  private List<QuotationNode> rowData = null;
  
  /**
   * The current row count.
   */
  private int nRowCount = 0;
  
  
  /**
   * Returns the number of rows.
   * 
   * @return the number of rows
   */
  public int getRowCount()
  {
    return nRowCount;
  }
  
  
  /**
   * Returns the number of columns.
   * 
   * @return the number of columns
   */
  public int getColumnCount()
  {
    return 2;
  }
  
  
  /**
   * Returns the name of the column.
   * 
   * @param col the column to get the name for
   * @return the name of the specified column
   */
  public String getColumnName(final int col)
  {
    return columnNames[col];
  }
  
  
  /**
   * Retrieves a value from a row/column.
   * 
   * @param rowIndex the row index
   * @param columnIndex the column index
   * @return the value at the specified row/column
   */
  public Object getValueAt(final int rowIndex,
                           final int columnIndex)
  {
    QuotationNode quote = rowData.get(rowIndex);
    switch (columnIndex)
    {
      case 0:
        return quote.getName();
      
      case 1:
        return Utility.putInTitleCase(quote.getTopic());
      
      default:
        return "x";
    }
  }
  
  
  /**
   * Set the row data.
   * 
   * @param listData the data to fill in a row
   */
  public void setRowData(final List<QuotationNode> listData)
  {
    // Empty the previous data
    rowData = null;
    
    if (listData == null)
    {
      nRowCount = 0;
    }
    else
    {
      nRowCount = listData.size();
      if (nRowCount > 0)
      {
        rowData = listData;
      }
    }
  }
}
