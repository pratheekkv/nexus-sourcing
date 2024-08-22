package cds.gen.com.sap.sourcing.db;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import java.lang.Integer;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("com.sap.sourcing.db.States")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface States_ extends StructuredType<States_> {
  String ID = "ID";

  String TEXT = "Text";

  String CDS_NAME = "com.sap.sourcing.db.States";

  @CdsName(ID)
  ElementRef<Integer> ID();

  @CdsName(TEXT)
  ElementRef<String> Text();
}
