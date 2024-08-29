using { com.sap.sourcing.db as db } from '../db/index';
@odata.apply.transformations
service Sourcing {
    entity Project      as projection on db.SourcingProject;
    entity Task         as projection on db.Task actions{
        action setStatus() returns Task;
    };
    entity Event        as projection on db.Event;
    entity Item         as projection on db.Item;
    entity Supplier     as projection on db.Supplier;
    entity Terms        as projection on db.Terms;
    entity ItemTerms    as projection on db.ItemTerms;
    entity MessageBoard as projection on db.MessageBoard;
    entity TaskType     as projection on db.TaskType;
    entity TaskStatus   as projection on db.TaskStatus;

}