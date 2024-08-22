package cds.gen.com.sap.sourcing.db;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("com.sap.sourcing.db.Terms")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Terms_ extends StructuredType<Terms_> {
  String EVENT_ID = "Event_ID";

  String CDS_NAME = "com.sap.sourcing.db.Terms";

  Event_ Event();

  Event_ Event(Function<Event_, CqnPredicate> filter);

  @CdsName(EVENT_ID)
  ElementRef<String> Event_ID();

  ElementRef<String> id();

  ElementRef<String> description();

  ElementRef<String> datatype();
}
