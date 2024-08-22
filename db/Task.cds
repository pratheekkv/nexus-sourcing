namespace com.sap.sourcing.db;

using { managed, cuid } from '@sap/cds/common';

using { com.sap.sourcing.db.SourcingProject as SourcingProject } from './SourcingProject';
using { com.sap.sourcing.db.Event as Event } from './Event';

entity Task : cuid, managed {
  sourcingProject: Association to one SourcingProject;
  description: String;
  type: String enum { phase = 'phase'; task = 'task'; };
  status: String;
  owner: String;
  dueDate: Date;
  approverReviewer: String;
  ParentID  : type of ID;

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

  
  Parent : Association to one Task on Parent.ID = ParentID;
  events: Association to one Event;
}