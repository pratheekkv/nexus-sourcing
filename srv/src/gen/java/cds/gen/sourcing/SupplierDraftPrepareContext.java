package cds.gen.sourcing;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftPrepare")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface SupplierDraftPrepareContext extends EventContext {
  String SIDE_EFFECTS_QUALIFIER = "SideEffectsQualifier";

  String CDS_NAME = "draftPrepare";

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  String getSideEffectsQualifier();

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  void setSideEffectsQualifier(String sideEffectsQualifier);

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static SupplierDraftPrepareContext create() {
    return EventContext.create(SupplierDraftPrepareContext.class, "Sourcing.Supplier");
  }

  void setResult(Supplier result);

  Supplier getResult();

  static SupplierDraftPrepareContext create(String entityName) {
    return EventContext.create(SupplierDraftPrepareContext.class, entityName);
  }
}
