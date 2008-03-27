/*
Copyright (c) 2008 Health Market Science, Inc.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
USA

You can contact Health Market Science at info@healthmarketscience.com
or at the following address:

Health Market Science
2700 Horizon Drive
Suite 200
King of Prussia, PA 19406
*/

package com.healthmarketscience.sqlbuilder.dbspec.basic;

import com.healthmarketscience.sqlbuilder.dbspec.Column;
import com.healthmarketscience.sqlbuilder.dbspec.Table;

/**
 * Describe class DbColumn here.
 *
 *
 * @author James Ahlborn
 */
public class DbColumn extends DbObject<DbTable>
  implements Column
{
  private final String _typeName;
  private final Integer _typeLength;

  public DbColumn(DbTable parent, String name,
                  String typeName, Integer typeLength) {
    super(parent, name);
    _typeName = typeName;
    _typeLength = typeLength;
  }

  public Table getTable() {
    return getParent();
  }
    
  public String getColumnNameSQL() {
    return getName();
  }
    
  public String getTypeNameSQL() {
    return _typeName;
  }
    
  public Integer getTypeLength() {
    return _typeLength;
  }
    
}
