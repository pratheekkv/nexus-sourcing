package cds.gen.sourcing;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect to capture changes by user and name
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-managed
 */
@CdsName("Sourcing.Task")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Task extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String SOURCING_PROJECT = "sourcingProject";

  String SOURCING_PROJECT_ID = "sourcingProject_ID";

  String DESCRIPTION = "description";

  String TYPE = "type";

  String STATUS = "status";

  String OWNER = "owner";

  String DUE_DATE = "dueDate";

  String APPROVER_REVIEWER = "approverReviewer";

  String PARENT_ID = "ParentID";

  String LIMITED_DESCENDANT_COUNT = "LimitedDescendantCount";

  String DISTANCE_FROM_ROOT = "DistanceFromRoot";

  String DRILL_STATE = "DrillState";

  String MATCHED = "Matched";

  String MATCHED_DESCENDANT_COUNT = "MatchedDescendantCount";

  String PARENT = "Parent";

  String EVENTS = "events";

  String EVENTS_ID = "events_ID";

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

  Project getSourcingProject();

  void setSourcingProject(Map<String, ?> sourcingProject);

  @CdsName(SOURCING_PROJECT_ID)
  String getSourcingProjectId();

  @CdsName(SOURCING_PROJECT_ID)
  void setSourcingProjectId(String sourcingProjectId);

  String getDescription();

  void setDescription(String description);

  String getType();

  void setType(String type);

  String getStatus();

  void setStatus(String status);

  String getOwner();

  void setOwner(String owner);

  LocalDate getDueDate();

  void setDueDate(LocalDate dueDate);

  String getApproverReviewer();

  void setApproverReviewer(String approverReviewer);

  @CdsName(PARENT_ID)
  String getParentID();

  @CdsName(PARENT_ID)
  void setParentID(String parentID);

  @CdsName(LIMITED_DESCENDANT_COUNT)
  Long getLimitedDescendantCount();

  @CdsName(LIMITED_DESCENDANT_COUNT)
  void setLimitedDescendantCount(Long limitedDescendantCount);

  @CdsName(DISTANCE_FROM_ROOT)
  Long getDistanceFromRoot();

  @CdsName(DISTANCE_FROM_ROOT)
  void setDistanceFromRoot(Long distanceFromRoot);

  @CdsName(DRILL_STATE)
  String getDrillState();

  @CdsName(DRILL_STATE)
  void setDrillState(String drillState);

  @CdsName(MATCHED)
  Boolean getMatched();

  @CdsName(MATCHED)
  void setMatched(Boolean matched);

  @CdsName(MATCHED_DESCENDANT_COUNT)
  Long getMatchedDescendantCount();

  @CdsName(MATCHED_DESCENDANT_COUNT)
  void setMatchedDescendantCount(Long matchedDescendantCount);

  @CdsName(PARENT)
  Task getParent();

  @CdsName(PARENT)
  void setParent(Map<String, ?> parent);

  Event getEvents();

  void setEvents(Map<String, ?> events);

  @CdsName(EVENTS_ID)
  String getEventsId();

  @CdsName(EVENTS_ID)
  void setEventsId(String eventsId);

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
  Task getSiblingEntity();

  @CdsName(SIBLING_ENTITY)
  void setSiblingEntity(Map<String, ?> siblingEntity);

  Task_ ref();

  static Task create() {
    return Struct.create(Task.class);
  }
}
