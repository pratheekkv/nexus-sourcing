using Sourcing as service from '../../srv/SourcingService';

annotate service.Task with @(UI: {

  CreateHidden : true,

 PresentationVariant #Default: {
        $Type                : 'UI.PresentationVariantType',
        Visualizations       : ['@UI.LineItem'],
        InitialExpansionLevel: 4
    },

  LineItem: [

        {
            $Type: 'UI.DataField',
            Value: node_id,
            Label : 'ID' 
        },
        {
            $Type: 'UI.DataField',
            Value: description,
            Label : 'Description' 
        },
        {
            $Type: 'UI.DataField',
            Value: type.description,
            ![@Common.FieldControl] : #ReadOnly,
            Label : 'Type' 
        },
        {
            $Type: 'UI.DataField',
            Value:  status.description,
            ![@Common.FieldControl] : #ReadOnly,
            Label : 'Status' 
        },
        {
            $Type: 'UI.DataField',
            Value: owner,
            Label : 'Owner' 
        },
        {
            $Type: 'UI.DataField',
            Value: dueDate,
            Label : 'Due Date' 
        },

        {
            $Type: 'UI.DataFieldForAction',
            Action: 'Sourcing.setStatus',
            Inline: true,
            ![@UI.Hidden]: {$edmJson: {$Or: [
                                                {$Ne:[{$Path: 'type_id'}, 'Task']},
                                                {$Ne:[{$Path: 'status_id'}, 'NS']}
            ]}},

            Label : 'Set to In-Progress'
        }          

    ],


  LineItem #EventTasks: [

        {
            $Type: 'UI.DataField',
            Value: node_id,
            Label : 'ID' 
        },
        {
            $Type: 'UI.DataField',
            Value: description,
            Label : 'Description' 
        },
        {
            $Type: 'UI.DataField',
            Value: type.description,
            ![@Common.FieldControl] : #ReadOnly,
            Label : 'Type' 
        },
        {
            $Type: 'UI.DataField',
            Value:  status.description,
            ![@Common.FieldControl] : #ReadOnly,
            Label : 'Status' 
        },
        {
            $Type: 'UI.DataField',
            Value: owner,
            Label : 'Owner' 
        },
        {
            $Type: 'UI.DataField',
            Value: dueDate,
            Label : 'Due Date' 
        }        

    ]    



    
}, 

Hierarchy.RecursiveHierarchy #PhaseHierarchy: {
  $Type                 : 'Hierarchy.RecursiveHierarchyType',
  ExternalKey           : null,
  LimitedDescendantCount: LimitedDescendantCount,
  DistanceFromRoot      : DistanceFromRoot,
  DrillState            : DrillState,
  Matched               : Matched,
  MatchedDescendantCount: MatchedDescendantCount,

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