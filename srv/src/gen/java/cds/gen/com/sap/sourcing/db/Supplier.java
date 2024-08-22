package cds.gen.com.sap.sourcing.db;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Boolean;
import java.lang.String;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

/**
 * Aspect to capture changes by user and name
 *
 * See https://cap.cloud.sap/docs/cds/common#aspect-managed
 */
@CdsName("com.sap.sourcing.db.Supplier")
@Generated(
    value = "cds-maven-plugin",
    date = "2024-08-22T08:15:29.040504Z",
    comments = "com.sap.cds:cds-maven-plugin:3.2.0-m2434 / com.sap.cds:cds4j-api:3.2.0-m20240820-150529"
)
public interface Supplier extends CdsData {
  String ID = "ID";

  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String MODIFIED_AT = "modifiedAt";

  String MODIFIED_BY = "modifiedBy";

  String EVENT = "event";

  String EVENT_ID = "event_ID";

  String ORG_NAME = "orgName";

  String CONTACT = "contact";

  String RISK_LEVEL = "riskLevel";

  String INCUMBENT_SUPPLIER = "incumbentSupplier";

  String EXCLUDED_SUPPLIER = "excludedSupplier";

  @CdsName(ID)
  String getId();

  @CdsName(ID)
  void setId(String id);

  Instant getCreatedAt();

  void setCreatedAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String getCreatedBy();

  /**
   * Canonical user ID
   */
  void setCreatedBy(String createdBy);

  Instant getModifiedAt();

  void setModifiedAt(Instant modifiedAt);

  /**
   * Canonical user ID
   */
  String getModifiedBy();

  /**
   * Canonical user ID
   */
  void setModifiedBy(String modifiedBy);

  Event getEvent();

  void setEvent(Map<String, ?> event);

  @CdsName(EVENT_ID)
  String getEventId();

  @CdsName(EVENT_ID)
  void setEventId(String eventId);

  String getOrgName();

  void setOrgName(String orgName);

  String getContact();

  void setContact(String contact);

  String getRiskLevel();

  void setRiskLevel(String riskLevel);

  Boolean getIncumbentSupplier();

  void setIncumbentSupplier(Boolean incumbentSupplier);

  Boolean getExcludedSupplier();

  void setExcludedSupplier(Boolean excludedSupplier);

  Supplier_ ref();

  static Supplier create() {
    return Struct.create(Supplier.class);
  }

  static Supplier create(String id) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(ID, id);
    return Struct.access(keys).as(Supplier.class);
  }
}
