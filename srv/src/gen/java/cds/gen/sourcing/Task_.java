package cds.gen.sourcing;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import java.time.Instant;
import java.time.LocalDate;
import java.util.function.Function;
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
public interface Task_ extends StructuredType<Task_> {
  String ID = "ID";

  String SOURCING_PROJECT_ID = "sourcingProject_ID";

  String PARENT_ID = "ParentID";

  String LIMITED_DESCENDANT_COUNT = "LimitedDescendantCount";

  String DISTANCE_FROM_ROOT = "DistanceFromRoot";

  String DRILL_STATE = "DrillState";

  String MATCHED = "Matched";

  String MATCHED_DESCENDANT_COUNT = "MatchedDescendantCount";

  String EVENTS_ID = "events_ID";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String CDS_NAME = "Sourcing.Task";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  Project_ sourcingProject();

  Project_ sourcingProject(Function<Project_, CqnPredicate> filter);

  @CdsName(SOURCING_PROJECT_ID)
  ElementRef<String> sourcingProject_ID();

  ElementRef<String> description();

  ElementRef<String> type();

  ElementRef<String> status();

  ElementRef<String> owner();

  ElementRef<LocalDate> dueDate();

  ElementRef<String> approverReviewer();

  @CdsName(PARENT_ID)
  ElementRef<String> ParentID();

  @CdsName(LIMITED_DESCENDANT_COUNT)
  ElementRef<Long> LimitedDescendantCount();

  @CdsName(DISTANCE_FROM_ROOT)
  ElementRef<Long> DistanceFromRoot();

  @CdsName(DRILL_STATE)
  ElementRef<String> DrillState();

  @CdsName(MATCHED)
  ElementRef<Boolean> Matched();

  @CdsName(MATCHED_DESCENDANT_COUNT)
  ElementRef<Long> MatchedDescendantCount();

  Task_ Parent();

  Task_ Parent(Function<Task_, CqnPredicate> filter);

  Event_ events();

  Event_ events(Function<Event_, CqnPredicate> filter);

  @CdsName(EVENTS_ID)
  ElementRef<String> events_ID();

  @CdsName(IS_ACTIVE_ENTITY)
  ElementRef<Boolean> IsActiveEntity();

  @CdsName(HAS_ACTIVE_ENTITY)
  ElementRef<Boolean> HasActiveEntity();

  @CdsName(HAS_DRAFT_ENTITY)
  ElementRef<Boolean> HasDraftEntity();

  DraftAdministrativeData_ DraftAdministrativeData();

  DraftAdministrativeData_ DraftAdministrativeData(
      Function<DraftAdministrativeData_, CqnPredicate> filter);

  @CdsName(DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID)
  ElementRef<String> DraftAdministrativeData_DraftUUID();

  Task_ SiblingEntity();

  Task_ SiblingEntity(Function<Task_, CqnPredicate> filter);
}
