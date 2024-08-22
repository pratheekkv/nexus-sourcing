package cds.gen.sourcing;

import com.sap.cds.ql.CdsName;
import java.lang.Class;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
@CdsName("Sourcing")
public interface Sourcing_ {
  String CDS_NAME = "Sourcing";

  Class<Item_> ITEM = Item_.class;

  Class<ItemTerms_> ITEM_TERMS = ItemTerms_.class;

  Class<DraftAdministrativeData_> DRAFT_ADMINISTRATIVE_DATA = DraftAdministrativeData_.class;

  Class<Event_> EVENT = Event_.class;

  Class<Supplier_> SUPPLIER = Supplier_.class;

  Class<Terms_> TERMS = Terms_.class;

  Class<Task_> TASK = Task_.class;

  Class<Project_> PROJECT = Project_.class;
}
