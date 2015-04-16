/*
Copyright (c) 2008 Health Market Science, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.healthmarketscience.sqlbuilder.dbspec;

import java.util.ArrayList;

import java.util.List;

/**
 * Utility class for using a table multiple times in the same query with
 * different aliases.  All Columns returned by this class will also use the
 * new alias.  All other methods return information from the original table.
 *
 * @author James Ahlborn
 */
public class RejoinTable implements Table
{
  /** the original table */
  private Table _table;
  /** the new alias to use for this table */
  private String _alias;
  /** the wrapped columns from the original table */
  private List<RejoinColumn> _columns;

  
  public RejoinTable(Table table, String alias) {
    _table = table;
    _alias = alias;
    _columns = new ArrayList<RejoinColumn>(_table.getColumns().size());
    for(Column column : _table.getColumns()) {
      _columns.add(new RejoinColumn(column));
    }
  }

  public Table getOriginalTable() { return _table; }
  
  public String getAlias() { return _alias; }
  
  public String getTableNameSQL() { return _table.getTableNameSQL(); }

  public List<RejoinColumn> getColumns() { return _columns; }

  public List<? extends Constraint> getConstraints() { return _table.getConstraints(); }

  public RejoinColumn findColumnByName(String name) {
    for(RejoinColumn col : getColumns()) {
      if((name == col.getColumnNameSQL()) ||
         ((name != null) && name.equals(col.getColumnNameSQL()))) {
        return col;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return "Rejoin: " + getOriginalTable().toString() + "(" + getAlias() + ")";
  }
  
  /**
   * Utility class which wraps a Column and returns a reference to the
   * RejoinTable instead of the original table.  All other methods return the
   * information from the original column.
   */
  @SuppressWarnings("deprecation")
  public class RejoinColumn implements Column
  {
    /** the original column object */
    private Column _column;

    private RejoinColumn(Column column) {
      _column = column;
    }

    public Column getOriginalColumn() { return _column; }
    
    public RejoinTable getTable() { return RejoinTable.this; }
  
    public String getColumnNameSQL() { return _column.getColumnNameSQL(); }

    public String getTypeNameSQL() { return _column.getTypeNameSQL(); }

    public Integer getTypeLength() { return _column.getTypeLength(); }

    public List<?> getTypeQualifiers() { return _column.getTypeQualifiers(); }

    public List<? extends Constraint> getConstraints() { return _column.getConstraints(); }

    public Object getDefaultValue() { return _column.getDefaultValue(); }

    @Override
    public String toString() {
      return "Rejoin: " + getOriginalColumn().toString() +
        "(" + getTable() + ")";
    }
  }
  
}
