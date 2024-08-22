package cds.gen.com.sap.sourcing.db;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Integer;
import java.lang.String;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect to capture changes by user and name
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-managed
 */
@CdsName("com.sap.sourcing.db.Event")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Event extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String SOURCING_PROJECT = "sourcingProject";

  String SOURCING_PROJECT_ID = "sourcingProject_ID";

  String TASKS = "tasks";

  String TASKS_ID = "tasks_ID";

  String DESCRIPTION = "description";

  String TYPE = "type";

  String VERSION = "version";

  String STATUS = "status";

  String OWNER = "owner";

  String EVENT_BIDDING_ENDS = "eventBiddingEnds";

  String EVENT_DURATION_UOM = "eventDurationUOM";

  String AWARD_DATE = "awardDate";

  String ITEMS = "items";

  String SUPPLIERS = "suppliers";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  Instant getCreatedAt();

  void setCreatedAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String getCreatedBy();

  /**
   * Canonical user ID
   */
  void setCreatedBy(String createdBy);

  Instant getModifiedAt();

  void setModifiedAt(Instant modifiedAt);

  /**
   * Canonical user ID
   */
  String getModifiedBy();

  /**
   * Canonical user ID
   */
  void setModifiedBy(String modifiedBy);

  SourcingProject getSourcingProject();

  void setSourcingProject(Map<String, ?> sourcingProject);

  @CdsName(SOURCING_PROJECT_ID)
  String getSourcingProjectId();

  @CdsName(SOURCING_PROJECT_ID)
  void setSourcingProjectId(String sourcingProjectId);

  Task getTasks();

  void setTasks(Map<String, ?> tasks);

  @CdsName(TASKS_ID)
  String getTasksId();

  @CdsName(TASKS_ID)
  void setTasksId(String tasksId);

  String getDescription();

  void setDescription(String description);

  String getType();

  void setType(String type);

  String getVersion();

  void setVersion(String version);

  String getStatus();

  void setStatus(String status);

  String getOwner();

  void setOwner(String owner);

  Integer getEventBiddingEnds();

  void setEventBiddingEnds(Integer eventBiddingEnds);

  String getEventDurationUOM();

  void setEventDurationUOM(String eventDurationUOM);

  LocalDate getAwardDate();

  void setAwardDate(LocalDate awardDate);

  List<Item> getItems();

  void setItems(List<? extends Map<String, ?>> items);

  List<Supplier> getSuppliers();

  void setSuppliers(List<? extends Map<String, ?>> suppliers);

  Event_ ref();

  static Event create() {
    return Struct.create(Event.class);
  }

  static Event create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(Event.class);
  }
}
