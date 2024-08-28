using Sourcing as service from '../../srv/SourcingService';

annotate service.Event with @(UI: {

  LineItem: [

        {
            $Type: 'UI.DataField',
            Value: description,
            Label : 'Description' 
        },
        {
            $Type: 'UI.DataField',
            Value: type,
            Label : 'Event Type' 
        },
        {
            $Type: 'UI.DataField',
            Value: createdBy,
            ![@Common.FieldControl] : #ReadOnly,
            Label : 'Created By' 
        },
        {
            $Type: 'UI.DataField',
            Value: owner,
            ![@Common.FieldControl] : #ReadOnly,
            Label : 'Owner' 
        },
        {
            $Type: 'UI.DataField',
            Value: status,
            Label : 'Status' 
        }       
    ]
    
});

annotate service.Item with @(

Hierarchy.RecursiveHierarchy #PhaseHierarchy: {
  $Type                 : 'Hierarchy.RecursiveHierarchyType',
  ExternalKey           : null,
  LimitedDescendantCount: LimitedDescendantCount,
  DistanceFromRoot      : DistanceFromRoot,
  DrillState            : DrillState,
  Matched               : Matched,
  MatchedDescendantCount: MatchedDescendantCount
},
Aggregation.RecursiveHierarchy #PhaseHierarchy: {
    NodeProperty            : node_id,
    ParentNavigationProperty: parent
},
Capabilities.FilterRestrictions : {
    NonFilterableProperties: [
      LimitedDescendantCount,
      DistanceFromRoot,
      DrillState,
      Matched,
      MatchedDescendantCount
  ]},
Capabilities.SortRestrictions : {
    NonSortableProperties: [
      LimitedDescendantCount,
      DistanceFromRoot,
      DrillState,
      Matched,
      MatchedDescendantCount
  ]});
