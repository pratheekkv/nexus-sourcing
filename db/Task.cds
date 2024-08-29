namespace com.sap.sourcing.db;

using { managed, cuid } from '@sap/cds/common';

using { com.sap.sourcing.db.SourcingProject as SourcingProject } from './SourcingProject';
using { com.sap.sourcing.db.Event as Event } from './Event';

entity Task :cuid, managed {
  node_id : String;
  sourcingProject: Association to one SourcingProject;
  description: String;
  type: Association to TaskType;
  status: Association to TaskStatus;
  owner: String;
  dueDate: Date;
  event: Association to Event;
  approverReviewer: String;

  parent_id  : type of node_id;

  @Core.Computed: true
  LimitedDescendantCount : Integer64;

  @Core.Computed: true
  DistanceFromRoot       : Integer64;

  @Core.Computed: true
  DrillState             : String;

  @Core.Computed: true
  Matched                : Boolean;

  @Core.Computed: true
  MatchedDescendantCount : Integer64;

  
  parent : Association to one Task on parent.node_id = parent_id;
  events: Association to one Event;
}

@cds.odata.valuelist
entity TaskType {
  key id : String;
  description : String;  
}

@cds.odata.valuelist
entity TaskStatus {
  key id : String;
  description : String;  
}

