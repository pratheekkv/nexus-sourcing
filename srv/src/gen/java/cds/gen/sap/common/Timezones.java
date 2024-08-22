package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Code list for time zones
 *
 * See https://cap.cloud.sap/docs/cds/common#entity-timezones
 */
@CdsName("sap.common.Timezones")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Timezones extends CdsData {
  String NAME = "name";

  String DESCR = "descr";

  String CODE = "code";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  String getName();

  void setName(String name);

  String getDescr();

  void setDescr(String descr);

  String getCode();

  void setCode(String code);

  List<TimezonesTexts> getTexts();

  void setTexts(List<? extends Map<String, ?>> texts);

  TimezonesTexts getLocalized();

  void setLocalized(Map<String, ?> localized);

  Timezones_ ref();

  static Timezones create() {
    return Struct.create(Timezones.class);
  }

  static Timezones create(String code) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(CODE, code);
    return Struct.access(keys).as(Timezones.class);
  }
}
