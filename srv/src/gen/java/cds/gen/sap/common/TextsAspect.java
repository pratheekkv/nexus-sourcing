package cds.gen.sap.common;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("sap.common.TextsAspect")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface TextsAspect extends CdsData {
  String LOCALE = "locale";

  /**
   * Type for a language code
   */
  String getLocale();

  /**
   * Type for a language code
   */
  void setLocale(String locale);

  static TextsAspect create() {
    return Struct.create(TextsAspect.class);
  }
}
