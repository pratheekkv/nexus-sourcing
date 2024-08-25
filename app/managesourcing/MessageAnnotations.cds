using Sourcing as service from '../../srv/SourcingService';

annotate service.MessageBoard with @(UI: {

  CreateHidden : true,

  LineItem: [

        {
            $Type: 'UI.DataField',
            Value: title,
            Label : 'Title' 
        },
        {
            $Type: 'UI.DataField',
            Value: labels,
            Label : 'Labels' 
        },
        {
            $Type: 'UI.DataField',
            Value: replies,
            Label : 'No. of replies' 
        }        
    ]
    
});