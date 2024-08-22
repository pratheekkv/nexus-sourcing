package cds.gen.sourcing;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Boolean;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftEdit")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface ProjectDraftEditContext extends EventContext {
  String PRESERVE_CHANGES = "PreserveChanges";

  String CDS_NAME = "draftEdit";

  @CdsName(PRESERVE_CHANGES)
  Boolean getPreserveChanges();

  @CdsName(PRESERVE_CHANGES)
  void setPreserveChanges(Boolean preserveChanges);

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static ProjectDraftEditContext create() {
    return EventContext.create(ProjectDraftEditContext.class, "Sourcing.Project");
  }

  void setResult(Project result);

  Project getResult();

  static ProjectDraftEditContext create(String entityName) {
    return EventContext.create(ProjectDraftEditContext.class, entityName);
  }
}
