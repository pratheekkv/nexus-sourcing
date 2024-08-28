namespace com.sap.sourcing.db;

using { managed, cuid } from '@sap/cds/common';

using { com.sap.sourcing.db.SourcingProject as SourcingProject } from './SourcingProject';
using { com.sap.sourcing.db.Task as Task } from './Task';

entity Event : cuid, managed {
  sourcingProject: Association to one SourcingProject;
  tasks: Association to Task;
  description: String;    
  type: String;
  version: String;
  status: String;
  owner: String;
  eventBiddingEnds: Integer;
  eventDurationUOM: String;
  awardDate: Date;
  items: Composition of many Item on items.event = $self;
  suppliers: Composition of many Supplier on suppliers.event = $self;
  terms: Composition of many Terms on terms.Event = $self;
}


entity Item : cuid {
  node_id: String;

  parent_id  : type of node_id;

  virtual LimitedDescendantCount : Integer64;

  virtual DistanceFromRoot       : Integer64;

  virtual DrillState             : String;

  virtual Matched                : Boolean;

  virtual MatchedDescendantCount : Integer64;

  terms: Composition of many ItemTerms on terms.Item = $self;
  event: Association to one Event;
  parent : Association to one Item on parent.node_id = parent_id;

}

entity ItemTerms {
  key id : String;
  key Item: Association to one Item;
  datatype : String;
  value : String;
}



entity Supplier: cuid, managed {
  event: Association to one Event;
  orgName: String;  
  contact: String;
  riskLevel: String;  
  incumbentSupplier: Boolean;  
  excludedSupplier: Boolean;
}

entity Terms {
  key id : String;
  key Event: Association to one Event;
  description : String;  
  datatype : String;
}