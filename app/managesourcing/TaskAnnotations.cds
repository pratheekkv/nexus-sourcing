using Sourcing as service from '../../srv/SourcingService';

annotate service.Task with @(UI: {

  CreateHidden : true,

  LineItem: [


        {
            $Type: 'UI.DataField',
            Value: NODE_ID,
            Label : 'Description' 
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
  ExternalKey           : NODE_ID,
  LimitedDescendantCount: LimitedDescendantCount,
  DistanceFromRoot      : DistanceFromRoot,
  DrillState            : DrillState,
  Matched               : Matched,
  MatchedDescendantCount: MatchedDescendantCount
},
Aggregation.RecursiveHierarchy #PhaseHierarchy: {
    NodeProperty            : NODE_ID,
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