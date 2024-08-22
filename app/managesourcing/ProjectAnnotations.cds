using Sourcing as service from '../../srv/SourcingService';


annotate service.Project with @odata.draft.enabled;

annotate service.Project with @(UI: {
 FieldGroup #GeneralInformation: {
      Data : [
        { 
          $Type: 'UI.DataField', 
          Value: ID, 
          ![@Common.FieldControl] : #ReadOnly, 
          Label : 'ID' 
        } ,
        { 
          $Type: 'UI.DataField', 
          Value: title, 
          Label : 'Title'
        },
        { 
          $Type: 'UI.DataField',
          Value: description, 
          Label : 'Description'
        },
        { 
          $Type: 'UI.DataField',
          Value: isQuickProject, 
          Label : 'Quick project?'
        },
        { 
          $Type: 'UI.DataField',
          Value: state, 
          Label : 'Project State'
        },
        { 
          $Type: 'UI.DataField',
          Value: isTest, 
          Label : 'Is Test Project?'
        },
        { 
          $Type: 'UI.DataField',
          Value: startDate , 
          Label : 'Start Date'
        },
        { 
          $Type: 'UI.DataField', 
          Value: dueDate, 
          Label : 'Due Date'
        },
        {
          $Type: 'UI.DataField',
          Value: targetSaving, 
          Label : 'Target Savings %'
        },
        {
          $Type: 'UI.DataField',
          Value: region, 
          Label : 'Region'
        }
      ]
    },

 FieldGroup #ExternalSystemIntegration: {
      Data : [
        { 
          $Type: 'UI.DataField', 
          Value: externalSystem, 
          Label : 'External System' 
        } 
      ]
    }    
});
