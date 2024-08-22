package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

/**
 * Code list for languages
 *
 * See https://cap.cloud.sap/docs/cds/common#entity-languages
 */
@CdsName("sap.common.Languages")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Languages_ extends StructuredType<Languages_> {
  String CDS_NAME = "sap.common.Languages";

  ElementRef<String> name();

  ElementRef<String> descr();

  ElementRef<String> code();

  LanguagesTexts_ texts();

  LanguagesTexts_ texts(Function<LanguagesTexts_, CqnPredicate> filter);

  LanguagesTexts_ localized();

  LanguagesTexts_ localized(Function<LanguagesTexts_, CqnPredicate> filter);
}
