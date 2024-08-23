
DROP VIEW IF EXISTS Sourcing_DraftAdministrativeData;
DROP VIEW IF EXISTS Sourcing_ItemTerms;
DROP VIEW IF EXISTS Sourcing_Terms;
DROP VIEW IF EXISTS Sourcing_Supplier;
DROP VIEW IF EXISTS Sourcing_Item;
DROP VIEW IF EXISTS Sourcing_Event;
DROP VIEW IF EXISTS Sourcing_Task;
DROP VIEW IF EXISTS Sourcing_Project;
DROP TABLE IF EXISTS Sourcing_Supplier_drafts;
DROP TABLE IF EXISTS Sourcing_Item_drafts;
DROP TABLE IF EXISTS Sourcing_Event_drafts;
DROP TABLE IF EXISTS Sourcing_Task_drafts;
DROP TABLE IF EXISTS Sourcing_Project_drafts;
DROP TABLE IF EXISTS DRAFT_DraftAdministrativeData;
DROP TABLE IF EXISTS com_sap_sourcing_db_Task;
DROP TABLE IF EXISTS com_sap_sourcing_db_Terms;
DROP TABLE IF EXISTS com_sap_sourcing_db_Supplier;
DROP TABLE IF EXISTS com_sap_sourcing_db_ItemTerms;
DROP TABLE IF EXISTS com_sap_sourcing_db_Item;
DROP TABLE IF EXISTS com_sap_sourcing_db_Event;
DROP TABLE IF EXISTS com_sap_sourcing_db_MessageBoard;
DROP TABLE IF EXISTS com_sap_sourcing_db_Region;
DROP TABLE IF EXISTS com_sap_sourcing_db_States;
DROP TABLE IF EXISTS com_sap_sourcing_db_SourcingProject;

CREATE TABLE com_sap_sourcing_db_SourcingProject (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  modifiedAt TIMESTAMP(7),
  modifiedBy NVARCHAR(255),
  title NVARCHAR(255),
  description NVARCHAR(255),
  isQuickProject BOOLEAN,
  state INTEGER,
  isTest INTEGER,
  startDate DATE,
  dueDate DATE,
  targetSaving NVARCHAR(3),
  region NVARCHAR(4),
  externalSystem NVARCHAR(255),
  PRIMARY KEY(ID)
); 

CREATE TABLE com_sap_sourcing_db_States (
  ID INTEGER NOT NULL,
  Text NVARCHAR(255),
  PRIMARY KEY(ID)
); 

CREATE TABLE com_sap_sourcing_db_Region (
  regionCode NVARCHAR(4) NOT NULL,
  Text NVARCHAR(255),
  PRIMARY KEY(regionCode)
); 

CREATE TABLE com_sap_sourcing_db_MessageBoard (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  modifiedAt TIMESTAMP(7),
  modifiedBy NVARCHAR(255),
  title NVARCHAR(255),
  labels NVARCHAR(255),
  replies SMALLINT,
  project_ID NVARCHAR(36),
  PRIMARY KEY(ID)
); 

CREATE TABLE com_sap_sourcing_db_Event (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  modifiedAt TIMESTAMP(7),
  modifiedBy NVARCHAR(255),
  sourcingProject_ID NVARCHAR(36),
  description NVARCHAR(255),
  type NVARCHAR(255),
  version NVARCHAR(255),
  status NVARCHAR(255),
  owner NVARCHAR(255),
  eventBiddingEnds INTEGER,
  eventDurationUOM NVARCHAR(255),
  awardDate DATE,
  PRIMARY KEY(ID)
); 

CREATE TABLE com_sap_sourcing_db_Item (
  ID NVARCHAR(36) NOT NULL,
  event_ID NVARCHAR(36),
  description NVARCHAR(255),
  PRIMARY KEY(ID)
); 

CREATE TABLE com_sap_sourcing_db_ItemTerms (
  Item_ID NVARCHAR(36) NOT NULL,
  id NVARCHAR(255) NOT NULL,
  datatype NVARCHAR(255),
  "VALUE" NVARCHAR(255),
  PRIMARY KEY(Item_ID, id)
); 

CREATE TABLE com_sap_sourcing_db_Supplier (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  modifiedAt TIMESTAMP(7),
  modifiedBy NVARCHAR(255),
  event_ID NVARCHAR(36),
  orgName NVARCHAR(255),
  contact NVARCHAR(255),
  riskLevel NVARCHAR(255),
  incumbentSupplier BOOLEAN,
  excludedSupplier BOOLEAN,
  PRIMARY KEY(ID)
); 

CREATE TABLE com_sap_sourcing_db_Terms (
  Event_ID NVARCHAR(36),
  id NVARCHAR(255) NOT NULL,
  description NVARCHAR(255),
  datatype NVARCHAR(255),
  PRIMARY KEY(id)
); 

CREATE TABLE com_sap_sourcing_db_Task (
  createdAt TIMESTAMP(7),
  createdBy NVARCHAR(255),
  modifiedAt TIMESTAMP(7),
  modifiedBy NVARCHAR(255),
  NODE_ID NVARCHAR(255),
  sourcingProject_ID NVARCHAR(36),
  description NVARCHAR(255),
  type NVARCHAR(255),
  status NVARCHAR(255),
  owner NVARCHAR(255),
  dueDate DATE,
  approverReviewer NVARCHAR(255),
  PARENT_ID NVARCHAR(255),
  LimitedDescendantCount BIGINT,
  DistanceFromRoot BIGINT,
  DrillState NVARCHAR(255),
  Matched BOOLEAN,
  MatchedDescendantCount BIGINT,
  events_ID NVARCHAR(36)
); 

CREATE TABLE DRAFT_DraftAdministrativeData (
  DraftUUID NVARCHAR(36) NOT NULL,
  CreationDateTime TIMESTAMP(7),
  CreatedByUser NVARCHAR(256),
  DraftIsCreatedByMe BOOLEAN,
  LastChangeDateTime TIMESTAMP(7),
  LastChangedByUser NVARCHAR(256),
  InProcessByUser NVARCHAR(256),
  DraftIsProcessedByMe BOOLEAN,
  PRIMARY KEY(DraftUUID)
); 

CREATE TABLE Sourcing_Project_drafts (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  modifiedAt TIMESTAMP(7) NULL,
  modifiedBy NVARCHAR(255) NULL,
  title NVARCHAR(255) NULL,
  description NVARCHAR(255) NULL,
  isQuickProject BOOLEAN NULL,
  state INTEGER NULL,
  isTest INTEGER NULL,
  startDate DATE NULL,
  dueDate DATE NULL,
  targetSaving NVARCHAR(3) NULL,
  region NVARCHAR(4) NULL,
  externalSystem NVARCHAR(255) NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
); 

CREATE TABLE Sourcing_Task_drafts (
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  modifiedAt TIMESTAMP(7) NULL,
  modifiedBy NVARCHAR(255) NULL,
  NODE_ID NVARCHAR(255) NULL,
  sourcingProject_ID NVARCHAR(36) NULL,
  description NVARCHAR(255) NULL,
  type NVARCHAR(255) NULL,
  status NVARCHAR(255) NULL,
  owner NVARCHAR(255) NULL,
  dueDate DATE NULL,
  approverReviewer NVARCHAR(255) NULL,
  PARENT_ID NVARCHAR(255) NULL,
  LimitedDescendantCount BIGINT NULL,
  DistanceFromRoot BIGINT NULL,
  DrillState NVARCHAR(255) NULL,
  Matched BOOLEAN NULL,
  MatchedDescendantCount BIGINT NULL,
  events_ID NVARCHAR(36) NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL
); 

CREATE TABLE Sourcing_Event_drafts (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  modifiedAt TIMESTAMP(7) NULL,
  modifiedBy NVARCHAR(255) NULL,
  sourcingProject_ID NVARCHAR(36) NULL,
  description NVARCHAR(255) NULL,
  type NVARCHAR(255) NULL,
  version NVARCHAR(255) NULL,
  status NVARCHAR(255) NULL,
  owner NVARCHAR(255) NULL,
  eventBiddingEnds INTEGER NULL,
  eventDurationUOM NVARCHAR(255) NULL,
  awardDate DATE NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
); 

CREATE TABLE Sourcing_Item_drafts (
  ID NVARCHAR(36) NOT NULL,
  event_ID NVARCHAR(36) NULL,
  description NVARCHAR(255) NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
); 

CREATE TABLE Sourcing_Supplier_drafts (
  ID NVARCHAR(36) NOT NULL,
  createdAt TIMESTAMP(7) NULL,
  createdBy NVARCHAR(255) NULL,
  modifiedAt TIMESTAMP(7) NULL,
  modifiedBy NVARCHAR(255) NULL,
  event_ID NVARCHAR(36) NULL,
  orgName NVARCHAR(255) NULL,
  contact NVARCHAR(255) NULL,
  riskLevel NVARCHAR(255) NULL,
  incumbentSupplier BOOLEAN NULL,
  excludedSupplier BOOLEAN NULL,
  IsActiveEntity BOOLEAN,
  HasActiveEntity BOOLEAN,
  HasDraftEntity BOOLEAN,
  DraftAdministrativeData_DraftUUID NVARCHAR(36) NOT NULL,
  PRIMARY KEY(ID)
); 

CREATE VIEW Sourcing_Project AS SELECT
  SourcingProject_0.ID,
  SourcingProject_0.createdAt,
  SourcingProject_0.createdBy,
  SourcingProject_0.modifiedAt,
  SourcingProject_0.modifiedBy,
  SourcingProject_0.title,
  SourcingProject_0.description,
  SourcingProject_0.isQuickProject,
  SourcingProject_0.state,
  SourcingProject_0.isTest,
  SourcingProject_0.startDate,
  SourcingProject_0.dueDate,
  SourcingProject_0.targetSaving,
  SourcingProject_0.region,
  SourcingProject_0.externalSystem
FROM com_sap_sourcing_db_SourcingProject AS SourcingProject_0; 

CREATE VIEW Sourcing_Task AS SELECT
  Task_0.createdAt,
  Task_0.createdBy,
  Task_0.modifiedAt,
  Task_0.modifiedBy,
  Task_0.NODE_ID,
  Task_0.sourcingProject_ID,
  Task_0.description,
  Task_0.type,
  Task_0.status,
  Task_0.owner,
  Task_0.dueDate,
  Task_0.approverReviewer,
  Task_0.PARENT_ID,
  Task_0.LimitedDescendantCount,
  Task_0.DistanceFromRoot,
  Task_0.DrillState,
  Task_0.Matched,
  Task_0.MatchedDescendantCount,
  Task_0.events_ID
FROM com_sap_sourcing_db_Task AS Task_0; 

CREATE VIEW Sourcing_Event AS SELECT
  Event_0.ID,
  Event_0.createdAt,
  Event_0.createdBy,
  Event_0.modifiedAt,
  Event_0.modifiedBy,
  Event_0.sourcingProject_ID,
  Event_0.description,
  Event_0.type,
  Event_0.version,
  Event_0.status,
  Event_0.owner,
  Event_0.eventBiddingEnds,
  Event_0.eventDurationUOM,
  Event_0.awardDate
FROM com_sap_sourcing_db_Event AS Event_0; 

CREATE VIEW Sourcing_Item AS SELECT
  Item_0.ID,
  Item_0.event_ID,
  Item_0.description
FROM com_sap_sourcing_db_Item AS Item_0; 

CREATE VIEW Sourcing_Supplier AS SELECT
  Supplier_0.ID,
  Supplier_0.createdAt,
  Supplier_0.createdBy,
  Supplier_0.modifiedAt,
  Supplier_0.modifiedBy,
  Supplier_0.event_ID,
  Supplier_0.orgName,
  Supplier_0.contact,
  Supplier_0.riskLevel,
  Supplier_0.incumbentSupplier,
  Supplier_0.excludedSupplier
FROM com_sap_sourcing_db_Supplier AS Supplier_0; 

CREATE VIEW Sourcing_Terms AS SELECT
  Terms_0.Event_ID,
  Terms_0.id,
  Terms_0.description,
  Terms_0.datatype
FROM com_sap_sourcing_db_Terms AS Terms_0; 

CREATE VIEW Sourcing_ItemTerms AS SELECT
  ItemTerms_0.Item_ID,
  ItemTerms_0.id,
  ItemTerms_0.datatype,
  ItemTerms_0."VALUE"
FROM com_sap_sourcing_db_ItemTerms AS ItemTerms_0; 

CREATE VIEW Sourcing_DraftAdministrativeData AS SELECT
  DraftAdministrativeData.DraftUUID,
  DraftAdministrativeData.CreationDateTime,
  DraftAdministrativeData.CreatedByUser,
  DraftAdministrativeData.DraftIsCreatedByMe,
  DraftAdministrativeData.LastChangeDateTime,
  DraftAdministrativeData.LastChangedByUser,
  DraftAdministrativeData.InProcessByUser,
  DraftAdministrativeData.DraftIsProcessedByMe
FROM DRAFT_DraftAdministrativeData AS DraftAdministrativeData; 

