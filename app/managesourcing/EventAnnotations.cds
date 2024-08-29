using Sourcing as service from '../../srv/SourcingService';

annotate service.Event with @(UI: {
ConnectedFields #ConnectedData   : {
        Label : 'Event Bidding End',
        Template: '{biddingEnd} - {biidingEndUnit}',
        Data: {
         biddingEnd:   {
                $Type             : 'UI.DataField',
                Value             : biddingEnd,
                ![@UI.Importance] : #High
            },
        biddingEndUnit: {
                $Type             : 'UI.DataField',
                Value             : biidingEndUnit,
                ![@UI.Importance] : #High
            }
        }
    },
    FieldGroup #BiddingRules: {
      Data : [
        { 
          $Type: 'UI.DataField', 
          Value: startBidOnPublish, 
          ![@Common.FieldControl] : #ReadOnly, 
          Label : 'Start bidding right after event is published' 
        } ,
        {
            $Type : 'UI.DataFieldForAnnotation',
            Target : '@UI.ConnectedFields#ConnectedData'
        },
        { 
          $Type: 'UI.DataField',
          Value: awardDate, 
          Label : 'Estimated Award Date'
        }
      ]
    },

  LineItem: [

        {
            $Type: 'UI.DataField',
            Value: description,
            Label : 'Description' 
        },
        {
            $Type: 'UI.DataField',
            Value: type_id,
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

Hierarchy.RecursiveHierarchy #ItemHierarchy: {
  $Type                 : 'Hierarchy.RecursiveHierarchyType',
  ExternalKey           : null,
  LimitedDescendantCount: LimitedDescendantCount,
  DistanceFromRoot      : DistanceFromRoot,
  DrillState            : DrillState,
  Matched               : Matched,
  MatchedDescendantCount: MatchedDescendantCount
},
Aggregation.RecursiveHierarchy #ItemHierarchy: {
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


  annotate service.Supplier with @(UI: {

  LineItem: [

        {
            $Type: 'UI.DataField',
            Value: orgName,
            Label : 'Organization Name' 
        },
        {
            $Type: 'UI.DataField',
            Value: contact,
            Label : 'Contact Name' 
        },
        {
            $Type: 'UI.DataField',
            Value: riskLevel,
            ![@Common.FieldControl] : #ReadOnly,
            Label : 'Risk Level' 
        },
        {
            $Type: 'UI.DataField',
            Value: incumbentSupplier,
            ![@Common.FieldControl] : #ReadOnly,
            Label : 'Is this an incumbent supplier?' 
        },
        {
            $Type: 'UI.DataField',
            Value: excludedSupplier,
            Label : 'Is this an excluded supplier?' 
        }       
    ]
    
});
