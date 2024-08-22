package cds.gen.com.sap.sourcing.db;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("com.sap.sourcing.db.Region")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Region extends CdsData {
  String REGION_CODE = "regionCode";

  String TEXT = "Text";

  String getRegionCode();

  void setRegionCode(String regionCode);

  @CdsName(TEXT)
  String getText();

  @CdsName(TEXT)
  void setText(String text);

  Region_ ref();

  static Region create() {
    return Struct.create(Region.class);
  }

  static Region create(String regionCode) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(REGION_CODE, regionCode);
    return Struct.access(keys).as(Region.class);
  }
}
