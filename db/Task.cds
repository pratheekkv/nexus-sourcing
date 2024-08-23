namespace com.sap.sourcing.db;

using { managed, cuid } from '@sap/cds/common';

using { com.sap.sourcing.db.SourcingProject as SourcingProject } from './SourcingProject';
using { com.sap.sourcing.db.Event as Event } from './Event';

entity Task : managed {
  NODE_ID : String;
  sourcingProject: Association to one SourcingProject;
  description: String;
  type: String enum { phase = 'Phase'; task = 'Task'; };
  status: String;
  owner: String;
  dueDate: Date;
  approverReviewer: String;
  PARENT_ID  : type of NODE_ID;

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

  
  Parent : Association to one Task on Parent.NODE_ID = PARENT_ID;
  events: Association to one Event;
}