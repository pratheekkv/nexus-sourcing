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
  terms: Composition of many ItemTerms on terms.Item = $self;
}


entity Item : cuid {
  event: Association to one Event;
  description: String;
}

entity ItemTerms {
  key Item: Association to one Item;
  key id : String;
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
  Event: Association to one Event;
  key id : String;
  description : String;  
  datatype : String;
}