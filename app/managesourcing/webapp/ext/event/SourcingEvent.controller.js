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
            },  
            
            onModelContextChange: async function(oEvent){
                var oContext = this.getView()?.getBindingContext();
                var isActiveEntity = await oContext?.requestProperty("IsActiveEntity");

                if (isActiveEntity !== undefined) {
                    var uiModel = this.getView()?.getModel("ui");
                    uiModel.setProperty("/isEditable", !isActiveEntity);
              }
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
            },

            onEdit : async function(oEvent) {
                var oContext = this.getView().getBindingContext();
            
                await this.getExtensionAPI().getEditFlow().editDocument(oContext);
            }

        });
    }
);
