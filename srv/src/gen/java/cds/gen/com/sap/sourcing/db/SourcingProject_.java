package cds.gen.com.sap.sourcing.db;

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
@CdsName("com.sap.sourcing.db.SourcingProject")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface SourcingProject_ extends StructuredType<SourcingProject_> {
  String ID = "ID";

  String CDS_NAME = "com.sap.sourcing.db.SourcingProject";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  ElementRef<String> title();

  ElementRef<String> description();

  ElementRef<Boolean> isQuickProject();

  ElementRef<Integer> state();

  ElementRef<Integer> isTest();

  ElementRef<LocalDate> startDate();

  ElementRef<LocalDate> dueDate();

  ElementRef<String> targetSaving();

  ElementRef<String> region();

  ElementRef<String> externalSystem();

  Task_ tasks();

  Task_ tasks(Function<Task_, CqnPredicate> filter);

  Event_ events();

  Event_ events(Function<Event_, CqnPredicate> filter);

  States_ stateList();

  States_ stateList(Function<States_, CqnPredicate> filter);

  Region_ regionList();

  Region_ regionList(Function<Region_, CqnPredicate> filter);

  MessageBoard_ messages();

  MessageBoard_ messages(Function<MessageBoard_, CqnPredicate> filter);
}
