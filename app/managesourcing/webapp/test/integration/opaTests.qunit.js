sap.ui.require(
    [
        'sap/fe/test/JourneyRunner',
        'managesourcing/test/integration/FirstJourney',
		'managesourcing/test/integration/pages/ProjectMain'
    ],
    function(JourneyRunner, opaJourney, ProjectMain) {
        'use strict';
        var JourneyRunner = new JourneyRunner({
            // start index.html in web folder
            launchUrl: sap.ui.require.toUrl('managesourcing') + '/index.html'
        });

       
        JourneyRunner.run(
            {
                pages: { 
					onTheProjectMain: ProjectMain
                }
            },
            opaJourney.run
        );
    }
);