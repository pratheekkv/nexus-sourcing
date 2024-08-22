package cds.gen.com.sap.sourcing.db;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect for entities with canonical universal IDs
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-cuid
 */
@CdsName("com.sap.sourcing.db.Item")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Item extends CdsData {
  String ID = "ID";

  String EVENT = "event";

  String EVENT_ID = "event_ID";

  String DESCRIPTION = "description";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  Event getEvent();

  void setEvent(Map<String, ?> event);

  @CdsName(EVENT_ID)
  String getEventId();

  @CdsName(EVENT_ID)
  void setEventId(String eventId);

  String getDescription();

  void setDescription(String description);

  Item_ ref();

  static Item create() {
    return Struct.create(Item.class);
  }

  static Item create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(Item.class);
  }
}
