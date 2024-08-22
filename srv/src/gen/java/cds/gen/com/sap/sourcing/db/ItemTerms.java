package cds.gen.com.sap.sourcing.db;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("com.sap.sourcing.db.ItemTerms")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface ItemTerms extends CdsData {
  String ITEM = "Item";

  String ITEM_ID = "Item_ID";

  String ID = "id";

  String DATATYPE = "datatype";

  String VALUE = "value";

  @CdsName(ITEM)
  Item getItem();

  @CdsName(ITEM)
  void setItem(Map<String, ?> item);

  @CdsName(ITEM_ID)
  String getItemId();

  @CdsName(ITEM_ID)
  void setItemId(String itemId);

  String getId();

  void setId(String id);

  String getDatatype();

  void setDatatype(String datatype);

  String getValue();

  void setValue(String value);

  ItemTerms_ ref();

  static ItemTerms create() {
    return Struct.create(ItemTerms.class);
  }
}
