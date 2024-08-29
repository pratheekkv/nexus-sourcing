sap.ui.define(
    [
        'sap/fe/core/PageController'
    ],
    function(PageController) {
        'use strict';

        return PageController.extend('managesourcing.ext.main.Main', {
            /**
             * Called when a controller is instantiated and its View controls (if available) are already created.
             * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
             * @memberOf managesourcing.ext.main.Main
             */
             onInit: function () {
                PageController.prototype.onInit.apply(this);
                managesourcing.control.IconTab.IconTabHeader.prototype.validateTab = $.proxy(function(nextTabKey){
                   var isEditable =  this.getView().getModel("ui").getProperty("/isEditable");
                   if(isEditable){
                    return false;
                   }
                    return true;
                },this);
                this.getAppComponent().getRouter("ProjectMain").attachRoutePatternMatched(this._onRouteMatched, this);
             },

             _onRouteMatched: function (oEvent) {
                var name = oEvent.getParameter('name');
                if (name !== "Details" && name !== "ProjectMain") {
                    return;
                }
                var oModel = this.getView().getModel();
                var oMetaModelLoaded = oModel.getMetaModel().requestObject("/Project");
                oMetaModelLoaded.then(this._onMetadataLoaded.bind(this));
            },

            _onMetadataLoaded: async function () {
                try {
                    await this._createNewDocument();
                } catch (error) {
                    sap.m.MessageToast.show(error.toString());
                }
            },

            _createNewDocument: async function () {
                var aContexts = await this.getAppComponent().getModel().bindList("/Project").requestContexts();
                this.getExtensionAPI().routing.navigate(aContexts[0],{preserveHistory: false});
            },

            onModelContextChange : async function(){
                var oContext = this.getView()?.getBindingContext();
                var isActiveEntity = await oContext?.requestProperty("IsActiveEntity");
            
                if (isActiveEntity !== undefined) {
                  var uiModel = this.getView()?.getModel("ui");
                  uiModel.setProperty("/isEditable", !isActiveEntity);
            }
        },

        handleWizardSave : async function(){
            await this.editFlow.saveDocument(this.getView().getBindingContext());
        },

        handleWizardCancel : async function(oEvent) {
            await this.editFlow.cancelDocument(this.getView().getBindingContext(), { cancelButton: oEvent.getSource(), skipDiscardPopover: true });
        },

        onDialogNextButton: function (oEvent) {
            var oWizard = this.getView().byId('createProjectWizard');
            var oCurrentStep = this.getView().byId(oWizard.getCurrentStep());
			var iSelectedStepIndex = oWizard.getSteps().indexOf(oCurrentStep);
			var oNextStep = oWizard.getSteps()[iSelectedStepIndex + 1];

			if (oCurrentStep && !oCurrentStep.bLast) {
				oWizard.goToStep(oNextStep, true);
			} else {
				oWizard.nextStep();
			}

			// this.handleButtonsVisibility();
		},

        onDialogBackButton: function(oEvent){
            var oWizard = this.getView().byId('createProjectWizard');
            var oCurrentStep = this.getView().byId(oWizard.getCurrentStep());

			var iSelectedStepIndex = oWizard.getSteps().indexOf(oCurrentStep);
			var oPreviousStep = oWizard.getSteps()[iSelectedStepIndex - 1];

			if (oCurrentStep) {
				oWizard.goToStep(oPreviousStep, true);
			} else {
				oWizard.previousStep();
			}          
        },

         onEdit : async function(oEvent) {
            var oContext = this.getView().getBindingContext();
        
            await this.getExtensionAPI().getEditFlow().editDocument(oContext);
          },

          createTask: async function (oEvent) {

            var oListBindings = this.getView().byId('taskList').getModel().bindList(this.getView().byId('taskList').getBindingContext().getPath()+'/tasks');
            this.getView().getModel("ui").setProperty("/isBusy", true);

            var oSelectedContext = this.getView().byId('taskList').getSelectedContexts()[0];
            var taskType = null, parent_id = null;
            if(oSelectedContext){
                taskType = await oSelectedContext?.requestProperty("type_id");
                if(taskType === "Phase"){ 
                    parent_id = await oSelectedContext?.requestProperty("node_id");
                }
            }
            
            var data = {
                node_id: " ",
                type_id : "Task",
                parent_id : parent_id 
            };

            await this.editFlow.createDocument(oListBindings, {
                creationMode: "Inline",
                data: data
            });
            this.getView().byId('taskList').refresh();
            this.getView().getModel("ui").setProperty("/isBusy", false);
         },

         createPhase: async function (oEvent) {
            var oListBindings = this.getView().byId('taskList').getModel().bindList(this.getView().byId('taskList').getBindingContext().getPath()+'/tasks');
            this.getView().getModel("ui").setProperty("/isBusy", true);

            var oSelectedContext = this.getView().byId('taskList').getSelectedContexts()[0];
            var taskType = null, phaseId = null;
            if(oSelectedContext){
                taskType = await oSelectedContext?.requestProperty("type_id");
                if(taskType === "Phase"){ 
                    phaseId = await oSelectedContext?.requestProperty("node_id");
                }
            }
            
            var data = {
                node_id: " ",
                type_id : "Phase",
                parent_id : phaseId 
            };

            await this.editFlow.createDocument(oListBindings, {
                creationMode: "Inline",
                data: data
            });
            this.getView().byId('taskList').refresh();
            this.getView().getModel("ui").setProperty("/isBusy", false);
         },

         onTabSelect: function(oEvent){

         },

         onEventNav : function(oEvent){
            this.getExtensionAPI().routing.navigate(oEvent.getParameter('bindingContext'),{preserveHistory: false});
         }
        
        

            /**
             * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
             * (NOT before the first rendering! onInit() is used for that one!).
             * @memberOf managesourcing.ext.main.Main
             */
            //  onBeforeRendering: function() {
            //
            //  },

            /**
             * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
             * This hook is the same one that SAPUI5 controls get after being rendered.
             * @memberOf managesourcing.ext.main.Main
             */
            //  onAfterRendering: function() {
            //
            //  },

            /**
             * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
             * @memberOf managesourcing.ext.main.Main
             */
            //  onExit: function() {
            //
            //  }
        });
    }
);
