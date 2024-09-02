sap.ui.require(
    [
        'sap/fe/test/JourneyRunner',
        'nexus/supplier/test/integration/FirstJourney',
		'nexus/supplier/test/integration/pages/ProjectMain'
    ],
    function(JourneyRunner, opaJourney, ProjectMain) {
        'use strict';
        var JourneyRunner = new JourneyRunner({
            // start index.html in web folder
            launchUrl: sap.ui.require.toUrl('nexus/supplier') + '/index.html'
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