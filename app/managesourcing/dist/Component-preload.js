//@ui5-bundle managesourcing/Component-preload.js
sap.ui.require.preload({
	"managesourcing/Component.js":function(){
sap.ui.define(["sap/fe/core/AppComponent"],function(e){"use strict";return e.extend("managesourcing.Component",{metadata:{manifest:"json"}})});
},
	"managesourcing/ext/main/Main.controller.js":function(){
sap.ui.define(["sap/fe/core/PageController"],function(t){"use strict";return t.extend("managesourcing.ext.main.Main",{onInit:function(){t.prototype.onInit.apply(this);this.getAppComponent().getRouter("ProjectMain").attachRoutePatternMatched(this._onRouteMatched,this)},_onRouteMatched:function(t){var e=this.getView().getModel();var i=e.getMetaModel().requestObject("/Project");i.then(this._onMetadataLoaded.bind(this))},_onMetadataLoaded:async function(){if(this._createDone){return}this._createDone=true;var t=this.getView()?.getBindingContext();if(t){return}try{await this._createNewDocument()}catch(t){sap.m.MessageToast.show(t.toString())}finally{this.getView().getModel("ui").setProperty("/isEditable",true)}},_createNewDocument:async function(){const t=this.getAppComponent().getModel().bindList("/Project");var e=await this.editFlow.createDocument(t,{creationMode:"NewPage",data:{}})},onModelContextChange:async function(){var t=this.getView()?.getBindingContext();var e=await(t?.requestProperty("IsActiveEntity"));if(e!==undefined){var i=this.getView()?.getModel("ui");i.setProperty("/isEditable",!e)}},handleWizardSave:async function(){await this.editFlow.saveDocument(this.getView().getBindingContext())},handleWizardCancel:async function(t){await this.editFlow.cancelDocument(this.getView().getBindingContext(),{cancelButton:t.getSource(),skipDiscardPopover:true})},onDialogNextButton:function(t){var e=this.getView().byId("createProjectWizard");var i=this.getView().byId(e.getCurrentStep());var a=e.getSteps().indexOf(i);var n=e.getSteps()[a+1];if(i&&!i.bLast){e.goToStep(n,true)}else{e.nextStep()}},onDialogBackButton:function(t){var e=this.getView().byId("createProjectWizard");var i=this.getView().byId(e.getCurrentStep());var a=e.getSteps().indexOf(i);var n=e.getSteps()[a-1];if(i){e.goToStep(n,true)}else{e.previousStep()}},onEdit:async function(t){var e=this.getView().getBindingContext();await this.getExtensionAPI().getEditFlow().editDocument(e)},createTask:async function(t){var e=this.getView().byId("taskList").getModel().bindList(this.getView().byId("taskList").getBindingContext().getPath()+"/tasks");this.getView().getModel("ui").setProperty("/isBusy",true);await this.editFlow.createDocument(e,{creationMode:"Inline",data:{type:"Task"}});this.getView().byId("taskList").refresh();this.getView().getModel("ui").setProperty("/isBusy",false)},createPhase:async function(t){var e=this.getView().byId("taskList").getModel().bindList(this.getView().byId("taskList").getBindingContext().getPath()+"/tasks");this.getView().getModel("ui").setProperty("/isBusy",true);var i=this.getView().byId("taskList").getSelectedContexts()[0];var a=null,n=null;if(i){a=await(i?.requestProperty("type"));if(a==="phase"){n=await(i?.requestProperty("ID"))}}await this.editFlow.createDocument(e,{creationMode:"Inline",data:{type:"Phase",ParentID:n}});this.getView().byId("taskList").refresh();this.getView().getModel("ui").setProperty("/isBusy",false)}})});
},
	"managesourcing/ext/main/Main.view.xml":'<mvc:View xmlns:form="sap.ui.layout.form"\n\t\txmlns:core="sap.ui.core"\n\t\txmlns:u="sap.ui.unified"\n\t\txmlns:mvc="sap.ui.core.mvc"\n        xmlns:macros="sap.fe.macros"\n\t\txmlns:macrosTable="sap.fe.macros.table"\n\t\txmlns="sap.m" controllerName="managesourcing.ext.main.Main"\n        modelContextChange="onModelContextChange"><NavContainer id="wizardNavContainer"><pages><Page\n\t\t\t\tid="wizardContentPage"\n\t\t\t\tshowHeader="false"><content><Wizard id="createProjectWizard" class="sapUiResponsivePadding--header sapUiResponsivePadding--content"\n\t\t\t\t\t\t\t\tshowNextButton="false" renderMode="Page"><WizardStep id="overviewStep"\n\t\t\t\t\t\t\t\t\t\ttitle="Project Summary"\n\t\t\t\t\t\t\t\t\t\ticon="sap-icon://cart"><HBox width="100%" justifyContent="End"><Button text="Edit" press="onEdit" /></HBox><macros:Form metaPath="@UI.FieldGroup#GeneralInformation" id="overview"><macros:layout type="ResponsiveGridLayout" columnsM="1" singleContainerFullSize="true" labelSpanL="1" labelSpanM="1" labelSpanS="1" emptySpanL="7" /></macros:Form><macros:Form metaPath="@UI.FieldGroup#ExternalSystemIntegration" id="externalSystemIntegration"><macros:layout type="ResponsiveGridLayout" columnsM="1" singleContainerFullSize="true" labelSpanL="1" labelSpanM="1" labelSpanS="1" emptySpanL="7" /></macros:Form></WizardStep><WizardStep id="taskStep"\n\t\t\t\t\t\t\t\t\t\ttitle="Tasks"\n\t\t\t\t\t\t\t\t\t\ticon="sap-icon://cart"><HBox width="100%" justifyContent="End"><Button text="Edit" press="onEdit" /></HBox><macros:TreeTable metaPath="tasks/@com.sap.vocabularies.UI.v1.LineItem" id="taskList" headerVisible="false" \n\t\t\t\t\t\t\t\t\tselectionMode="Single" busy="{ui>/isBusy}" hierarchyQualifier="PhaseHierarchy"><creationMode name="Inline" inlineCreationRowsHiddenInEditMode="true"   /><macros:actions><macrosTable:ActionGroup text="Create" key="createActionGroup"><macrosTable:Action text="Create Phase" press=".createPhase" key="phase" /><macrosTable:Action text="Create Task" press=".createTask"  key="task" /></macrosTable:ActionGroup></macros:actions></macros:TreeTable></WizardStep><WizardStep id="eventsStep"\n\t\t\t\t\t\t\t\t\t\ttitle="Events and Documents"\n\t\t\t\t\t\t\t\t\t\ticon="sap-icon://cart"></WizardStep><WizardStep id="messageBoardStep"\n\t\t\t\t\t\t\t\t\t\ttitle="Message Board"\n\t\t\t\t\t\t\t\t\t\ticon="sap-icon://cart"></WizardStep></Wizard></content><footer><OverflowToolbar><ToolbarSpacer/><Button text="Previous Step" visible="{= !${ui>/isEditable} }" press="onDialogBackButton" /><Button text="Next Step" type="Emphasized" visible="{= !${ui>/isEditable} }" press="onDialogNextButton" /><Button text="Save" type="Emphasized" visible="{ui>/isEditable}" press="handleWizardSave" /><Button\ttext="Cancel" type="Transparent" visible="{ui>/isEditable}" press="handleWizardCancel"/></OverflowToolbar></footer></Page></pages></NavContainer></mvc:View>',
	"managesourcing/i18n/i18n.properties":'# This is the resource bundle for managesourcing\n\n#Texts for manifest.json\n\n#XTIT: Application name\nappTitle=Manage Sourcing Project\n\n#YDES: Application description\nappDescription=An SAP Fiori application.\n#XTIT: Custom view title\nMainTitle=Main',
	"managesourcing/manifest.json":'{"_version":"1.59.0","sap.app":{"id":"managesourcing","type":"application","i18n":"i18n/i18n.properties","applicationVersion":{"version":"0.0.1"},"title":"{{appTitle}}","description":"{{appDescription}}","resources":"resources.json","sourceTemplate":{"id":"@sap/generator-fiori:fpm","version":"1.14.3","toolsId":"3bb3de2d-0315-4cbc-b2e8-ef2b763d747c"},"crossNavigation":{"inbounds":{"SourcingProject-manage":{"signature":{"parameters":{},"additionalParameters":"allowed"},"semanticObject":"SourcingProject","action":"manage"}}},"dataSources":{"mainService":{"uri":"odata/v4/Sourcing/","type":"OData","settings":{"annotations":[],"odataVersion":"4.0"}}}},"sap.ui":{"technology":"UI5","icons":{"icon":"","favIcon":"","phone":"","phone@2":"","tablet":"","tablet@2":""},"deviceTypes":{"desktop":true,"tablet":true,"phone":true}},"sap.ui5":{"flexEnabled":false,"dependencies":{"minUI5Version":"1.127.1","libs":{"sap.m":{},"sap.ui.core":{},"sap.ushell":{},"sap.fe.templates":{}}},"contentDensities":{"compact":true,"cozy":true},"models":{"i18n":{"type":"sap.ui.model.resource.ResourceModel","settings":{"bundleName":"managesourcing.i18n.i18n"}},"":{"dataSource":"mainService","preload":true,"settings":{"operationMode":"Server","autoExpandSelect":true,"earlyRequests":true}},"@i18n":{"type":"sap.ui.model.resource.ResourceModel","uri":"i18n/i18n.properties"}},"resources":{"css":[]},"routing":{"config":{},"routes":[{"name":"ProjectMain","pattern":":?query:","target":"ProjectMain"},{"name":"Details","pattern":"Project({key})","target":"Details"}],"targets":{"ProjectMain":{"type":"Component","id":"ProjectMain","name":"sap.fe.core.fpm","options":{"settings":{"viewName":"managesourcing.ext.main.Main","contextPath":"/Project"}}},"Details":{"type":"Component","id":"ProjectMain","name":"sap.fe.core.fpm","options":{"settings":{"viewName":"managesourcing.ext.main.Main","contextPath":"/Project"}}}}}},"sap.cloud":{"public":true,"service":"nexus.sourcing"}}'
});
//# sourceMappingURL=Component-preload.js.map
