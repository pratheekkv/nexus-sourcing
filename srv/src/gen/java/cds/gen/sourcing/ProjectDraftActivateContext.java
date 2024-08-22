package cds.gen.sourcing;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftActivate")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface ProjectDraftActivateContext extends EventContext {
  String CDS_NAME = "draftActivate";

  CqnSelect getCqn();

  void setCqn(CqnSelect select);

  static ProjectDraftActivateContext create() {
    return EventContext.create(ProjectDraftActivateContext.class, "Sourcing.Project");
  }

  void setResult(Project result);

  Project getResult();

  static ProjectDraftActivateContext create(String entityName) {
    return EventContext.create(ProjectDraftActivateContext.class, entityName);
  }
}
