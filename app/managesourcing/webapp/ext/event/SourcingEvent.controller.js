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
            },

            onSubSectionEntered: function (oEvent) {
                var sSubSectionId = oEvent.getParameter("id");
                
                if (sSubSectionId === this.getView().byId("treeTableSubSection").getId()) {
                    // Perform lazy loading, e.g., binding the table model, etc.
                    var oTable = this.byId("treeTable");
                    if (!oTable.getBinding("rows")) {
                        
                        oTable.bindRows({
                            path: this.getView().getBindingContext().getPath + '/items',
                            parameters: {
                                $expand: "terms" 
                            }
                        });
                    }
                }
            }

        });
    }
);
