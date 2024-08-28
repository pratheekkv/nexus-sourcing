namespace com.sap.sourcing.db;

using { managed, cuid } from '@sap/cds/common';

using { com.sap.sourcing.db.Task as Task } from './Task';
using { com.sap.sourcing.db.Event as Event } from './Event';

entity SourcingProject : cuid, managed {
  title: String;
  description: String;
  isQuickProject: Boolean;
  state: Integer;
  isTest: Boolean;
  startDate : Date;
  dueDate : Date;
  targetSaving: String(3);
  region : String(4);
  externalSystem: String;
  tasks: Composition of many Task on tasks.sourcingProject = $self;
  events: Composition of many Event on events.sourcingProject = $self;
  stateList: association to one States on stateList.ID = $self.state;
  regionList: association to one Region on regionList.regionCode = $self.region;
  messages: association to many MessageBoard on messages.project = $self;

}

 entity States{
  key ID: Integer;
  Text: String;
 }

  entity Region{
  key regionCode: String(4);
  Text: String;
 }


 entity MessageBoard : cuid, managed{
  title : String;
  labels: String;
  replies : Int16;
  project: Association to one SourcingProject;
 }