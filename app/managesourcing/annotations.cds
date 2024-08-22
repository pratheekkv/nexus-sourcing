using Sourcing as service from '../../srv/SourcingService';


annotate service.Project with @odata.draft.enabled;

annotate service.Project with @(UI: {
 FieldGroup #GeneralInformation: {
      Data : [
        { $Type: 'UI.DataField', Value: ID, ![@Common.FieldControl] : #ReadOnly, Label : 'ID' } ,
        { $Type: 'UI.DataField', Value: title, Label : 'Title'},
        { $Type: 'UI.DataField',Value: description, Label : 'Description'},
        { $Type: 'UI.DataField',Value: isQuickProject, Label : 'Quick project?'},
        { $Type: 'UI.DataField',Value: state, Label : 'Project State'},
        { $Type: 'UI.DataField',Value: isTest, Label : 'Is Test Project?'},
        { $Type: 'UI.DataField',Value: startDate , Label : 'Start Date'},
        { $Type: 'UI.DataField', Value: dueDate, Label : 'Due Date'},
        {$Type: 'UI.DataField',Value: targetSaving, Label : 'Target Savings %'},
        {$Type: 'UI.DataField',Value: region, Label : 'Region'}
      ]
    },

 FieldGroup #ExternalSystemIntegration: {
      Data : [
        { $Type: 'UI.DataField', Value: externalSystem, Label : 'External System' } 
      ]
    }    
});


annotate service.Task with @(UI: {

  LineItem: [
        {
            $Type: 'UI.DataField',
            Value: description
        },
        {
            $Type: 'UI.DataField',
            Value: type
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
  ExternalKey           : null,
  LimitedDescendantCount: LimitedDescendantCount,
  DistanceFromRoot      : DistanceFromRoot,
  DrillState            : DrillState,
  Matched               : Matched,
  MatchedDescendantCount: MatchedDescendantCount
},
Aggregation.RecursiveHierarchy #PhaseHierarchy: {
    NodeProperty            : ID,
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