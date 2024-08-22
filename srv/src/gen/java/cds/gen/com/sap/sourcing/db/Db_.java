package cds.gen.com.sap.sourcing.db;

import com.sap.cds.ql.CdsName;
import java.lang.Class;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
@CdsName("com.sap.sourcing.db")
public interface Db_ {
  String CDS_NAME = "com.sap.sourcing.db";

  Class<Item_> ITEM = Item_.class;

  Class<Event_> EVENT = Event_.class;

  Class<SourcingProject_> SOURCING_PROJECT = SourcingProject_.class;

  Class<Region_> REGION = Region_.class;

  Class<MessageBoard_> MESSAGE_BOARD = MessageBoard_.class;

  Class<Task_> TASK = Task_.class;

  Class<States_> STATES = States_.class;

  Class<Terms_> TERMS = Terms_.class;

  Class<ItemTerms_> ITEM_TERMS = ItemTerms_.class;

  Class<Supplier_> SUPPLIER = Supplier_.class;
}
