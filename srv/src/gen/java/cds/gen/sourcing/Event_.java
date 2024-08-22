package cds.gen.sourcing;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Boolean;
import java.lang.Integer;
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
@CdsName("Sourcing.Event")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Event_ extends StructuredType<Event_> {
  String ID = "ID";

  String SOURCING_PROJECT_ID = "sourcingProject_ID";

  String TASKS_ID = "tasks_ID";

  String IS_ACTIVE_ENTITY = "IsActiveEntity";

  String HAS_ACTIVE_ENTITY = "HasActiveEntity";

  String HAS_DRAFT_ENTITY = "HasDraftEntity";

  String DRAFT_ADMINISTRATIVE_DATA_DRAFT_UUID = "DraftAdministrativeData_DraftUUID";

  String CDS_NAME = "Sourcing.Event";

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

  Task_ tasks();

  Task_ tasks(Function<Task_, CqnPredicate> filter);

  @CdsName(TASKS_ID)
  ElementRef<String> tasks_ID();

  ElementRef<String> description();

  ElementRef<String> type();

  ElementRef<String> version();

  ElementRef<String> status();

  ElementRef<String> owner();

  ElementRef<Integer> eventBiddingEnds();

  ElementRef<String> eventDurationUOM();

  ElementRef<LocalDate> awardDate();

  Item_ items();

  Item_ items(Function<Item_, CqnPredicate> filter);

  Supplier_ suppliers();

  Supplier_ suppliers(Function<Supplier_, CqnPredicate> filter);

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

  Event_ SiblingEntity();

  Event_ SiblingEntity(Function<Event_, CqnPredicate> filter);
}
