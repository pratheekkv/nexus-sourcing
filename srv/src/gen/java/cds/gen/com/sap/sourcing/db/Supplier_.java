package cds.gen.com.sap.sourcing.db;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.Boolean;
import java.lang.String;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

/**
 * Aspect to capture changes by user and name
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-managed
 */
@CdsName("com.sap.sourcing.db.Supplier")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Supplier_ extends StructuredType<Supplier_> {
  String ID = "ID";

  String EVENT_ID = "event_ID";

  String CDS_NAME = "com.sap.sourcing.db.Supplier";

  @CdsName(ID)
  ElementRef<String> ID();

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  ElementRef<Instant> modifiedAt();

  ElementRef<String> modifiedBy();

  Event_ event();

  Event_ event(Function<Event_, CqnPredicate> filter);

  @CdsName(EVENT_ID)
  ElementRef<String> event_ID();

  ElementRef<String> orgName();

  ElementRef<String> contact();

  ElementRef<String> riskLevel();

  ElementRef<Boolean> incumbentSupplier();

  ElementRef<Boolean> excludedSupplier();
}
