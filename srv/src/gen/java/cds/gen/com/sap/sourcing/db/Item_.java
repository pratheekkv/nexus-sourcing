package cds.gen.com.sap.sourcing.db;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
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
public interface Item_ extends StructuredType<Item_> {
  String ID = "ID";

  String EVENT_ID = "event_ID";

  String CDS_NAME = "com.sap.sourcing.db.Item";

  @CdsName(ID)
  ElementRef<String> ID();

  Event_ event();

  Event_ event(Function<Event_, CqnPredicate> filter);

  @CdsName(EVENT_ID)
  ElementRef<String> event_ID();

  ElementRef<String> description();
}
