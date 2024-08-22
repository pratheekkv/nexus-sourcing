using Sourcing as service from '../../srv/SourcingService';

annotate service.Task with @(UI: {

  CreateHidden : true,

  LineItem: [
  
          {
            $Type: 'UI.DataField',
            Value: externalId,
            Label : 'Task/Phase' 
        },
        {
            $Type: 'UI.DataField',
            Value: description,
            Label : 'Description' 
        },
        {
            $Type: 'UI.DataField',
            Value: type,
            ![@Common.FieldControl] : #ReadOnly
        },
        {
            $Type: 'UI.DataField',
            Value: status
        },
        {
            $Type: 'UI.DataField',
            Value: owner
        },
        {
            $Type: 'UI.DataField',
            Value: dueDate
        }        
    ]
    
}, 
Hierarchy.RecursiveHierarchy #PhaseHierarchy: {
  $Type                 : 'Hierarchy.RecursiveHierarchyType',
  ExternalKey           : externalId,
  LimitedDescendantCount: LimitedDescendantCount,
  DistanceFromRoot      : DistanceFromRoot,
  DrillState            : DrillState,
  Matched               : Matched,
  MatchedDescendantCount: MatchedDescendantCount
},
Aggregation.RecursiveHierarchy #PhaseHierarchy: {
    NodeProperty            : externalId,
    ParentNavigationProperty: Parent
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