package cds.gen.sourcing;

import cds.gen.com.sap.sourcing.db.MessageBoard;
import cds.gen.com.sap.sourcing.db.Region;
import cds.gen.com.sap.sourcing.db.States;
import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect to capture changes by user and name
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-managed
 */
@CdsName("Sourcing.Project")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Project extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String TITLE = "title";

  String DESCRIPTION = "description";

  String IS_QUICK_PROJECT = "isQuickProject";

  String STATE = "state";

  String IS_TEST = "isTest";

  String START_DATE = "startDate";

  String DUE_DATE = "dueDate";

  String TARGET_SAVING = "targetSaving";

  String REGION = "region";

  String EXTERNAL_SYSTEM = "externalSystem";

  String TASKS = "tasks";

  String EVENTS = "events";

  String STATE_LIST = "stateList";

  String REGION_LIST = "regionList";

  String MESSAGES = "messages";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA = "DraftAdministrativeData";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String SIBLING_ENTITY = "SiblingEntity";

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

  String getTitle();

  void setTitle(String title);

  String getDescription();

  void setDescription(String description);

  Boolean getIsQuickProject();

  void setIsQuickProject(Boolean isQuickProject);

  Integer getState();

  void setState(Integer state);

  Integer getIsTest();

  void setIsTest(Integer isTest);

  LocalDate getStartDate();

  void setStartDate(LocalDate startDate);

  LocalDate getDueDate();

  void setDueDate(LocalDate dueDate);

  String getTargetSaving();

  void setTargetSaving(String targetSaving);

  String getRegion();

  void setRegion(String region);

  String getExternalSystem();

  void setExternalSystem(String externalSystem);

  List<Task> getTasks();

  void setTasks(List<? extends Map<String, ?>> tasks);

  List<Event> getEvents();

  void setEvents(List<? extends Map<String, ?>> events);

  States getStateList();

  void setStateList(Map<String, ?> stateList);

  Region getRegionList();

  void setRegionList(Map<String, ?> regionList);

  List<MessageBoard> getMessages();

  void setMessages(List<? extends Map<String, ?>> messages);

  @CdsName(IS_ACTIVE_ENTITY)
  Boolean getIsActiveEntity();

  @CdsName(IS_ACTIVE_ENTITY)
  void setIsActiveEntity(Boolean isActiveEntity);

  @CdsName(HAS_ACTIVE_ENTITY)
  Boolean getHasActiveEntity();

  @CdsName(HAS_ACTIVE_ENTITY)
  void setHasActiveEntity(Boolean hasActiveEntity);

  @CdsName(HAS_DRAFT_ENTITY)
  Boolean getHasDraftEntity();

  @CdsName(HAS_DRAFT_ENTITY)
  void setHasDraftEntity(Boolean hasDraftEntity);

  @CdsName(DRAFT_ADMINISTRATIVE_DATA)
  DraftAdministrativeData getDraftAdministrativeData();

  @CdsName(DRAFT_ADMINISTRATIVE_DATA)
  void setDraftAdministrativeData(Map<String, ?> draftAdministrativeData);

  @CdsName(DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID)
  String getDraftAdministrativeDataDraftUUID();

  @CdsName(DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID)
  void setDraftAdministrativeDataDraftUUID(String draftAdministrativeDataDraftUUID);

  @CdsName(SIBLING_ENTITY)
  Project getSiblingEntity();

  @CdsName(SIBLING_ENTITY)
  void setSiblingEntity(Map<String, ?> siblingEntity);

  Project_ ref();

  static Project create() {
    return Struct.create(Project.class);
  }
}
