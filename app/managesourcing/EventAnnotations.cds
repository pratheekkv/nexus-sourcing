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