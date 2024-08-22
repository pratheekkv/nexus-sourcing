package cds.gen.com.sap.sourcing.db;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Short;
import java.lang.String;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

/**
 * Aspect to capture changes by user and name
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-managed
 */
@CdsName("com.sap.sourcing.db.MessageBoard")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface MessageBoard_ extends StructuredType<MessageBoard_> {
  String ID = "ID";

  String PROJECT_ID = "project_ID";

  String CDS_NAME = "com.sap.sourcing.db.MessageBoard";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  ElementRef<String> title();

  ElementRef<String> labels();

  ElementRef<Short> replies();

  SourcingProject_ project();

  SourcingProject_ project(Function<SourcingProject_, CqnPredicate> filter);

  @CdsName(PROJECT_ID)
  ElementRef<String> project_ID();
}
