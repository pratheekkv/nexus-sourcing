package cds.gen.sourcing;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("Sourcing.ItemTerms")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface ItemTerms_ extends StructuredType<ItemTerms_> {
  String ITEM_ID = "Item_ID";

  String CDS_NAME = "Sourcing.ItemTerms";

  Item_ Item();

  Item_ Item(Function<Item_, CqnPredicate> filter);

  @CdsName(ITEM_ID)
  ElementRef<String> Item_ID();

  ElementRef<String> id();

  ElementRef<String> datatype();

  ElementRef<String> value();
}
