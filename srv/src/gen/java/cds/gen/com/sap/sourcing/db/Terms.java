package cds.gen.com.sap.sourcing.db;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("com.sap.sourcing.db.Terms")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Terms extends CdsData {
  String EVENT = "Event";

  String EVENT_ID = "Event_ID";

  String ID = "id";

  String DESCRIPTION = "description";

  String DATATYPE = "datatype";

  @CdsName(EVENT)
  Event getEvent();

  @CdsName(EVENT)
  void setEvent(Map<String, ?> event);

  @CdsName(EVENT_ID)
  String getEventId();

  @CdsName(EVENT_ID)
  void setEventId(String eventId);

  String getId();

  void setId(String id);

  String getDescription();

  void setDescription(String description);

  String getDatatype();

  void setDatatype(String datatype);

  Terms_ ref();

  static Terms create() {
    return Struct.create(Terms.class);
  }

  static Terms create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(Terms.class);
  }
}
