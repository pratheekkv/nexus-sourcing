sap.ui.define(
    [
        'sap/fe/core/PageController'
    ],
    function(PageController) {
        'use strict';

        return PageController.extend('managesourcing.ext.event.SourcingEvent', {

            onInit: function () {
               PageController.prototype.onInit.apply(this);
               this.getAppComponent().getRouter("Event").attachRoutePatternMatched(this._onRouteMatched, this);
             },

             _onRouteMatched: function (oEvent) {
                var oModel = this.getView().getModel();
                debugger;
                // var oMetaModelLoaded = oModel.getMetaModel().requestObject("/Project");
                // oMetaModelLoaded.then(this._onMetadataLoaded.bind(this));
            },  
            
            onModelContextChange: function(oEvent){
                debugger;
            }

        });
    }
);
