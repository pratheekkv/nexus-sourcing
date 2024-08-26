sap.ui.define([
    "sap/ui/mdc/TableDelegate",
    "sap/ui/mdc/Field",
    "sap/ui/model/json/JSONModel",
    'managesourcing/formatter/TermFormatter'
], function (TableDelegate,  FieldBase, JSONModel) {
    "use strict";

    var MyTableDelegate = Object.assign({}, TableDelegate);

        var fetchModel = async function(oControl) {
            return new Promise((resolve) => {
                const sModelName = oControl.getDelegate().payload && oControl.getDelegate().payload.modelName,
                    oContext = { modelName: sModelName, control: oControl, resolve: resolve };
                _retrieveModel.call(oContext);
            });
        };

        var _retrieveModel = async function () {
            this.control.detachModelContextChange(_retrieveModel, this);
            const sModelName = this.modelName,
                oModel = this.control.getModel(sModelName);
                var oContext = this.control.getBindingContext();
        
            if (oModel && oContext) {
                this.resolve(oModel);
            } else {
                this.control.attachModelContextChange(_retrieveModel, this);
            }
        };

        MyTableDelegate.formItermTerms = function (a,b,c) {
            debugger;
		};
        MyTableDelegate.fetchProperties = async function (oTable) {

            return fetchModel(oTable)
			.then(async (model) => {
                var aProperties = [];
                try{
                    var oContext = oTable.getBindingContext();
                    var aTerms = await oTable.getModel().bindList(oContext.getPath() + '/terms').requestContexts();
                    for (const term of aTerms) { 
                        var id = await term.requestProperty("id");
                        var description = await term.requestProperty("description");
                        var datatype = await term.requestProperty("datatype");
                        
                        aProperties.push({
                            key: id,
                            label: description,
                            path: "{path:'terms', formatter: '.formItermTerms'}",
                            dataType: "sap.ui.model.type." + datatype
                        })
        
                        oTable.addColumn(_addColumn(oTable,oTable.getId() + "---col-" + id, id));
                      }
                        oTable.rebind();
                        return aProperties;
                }catch(error){
                    console.log(error);
                }

			});


        };

        /**
         * Bind the columns to the table.
         */
       var _addColumn = function (oTable,sId, sPropertyKey) {
 
        var field = new FieldBase({ value: "{ parts: [ {path: 'id'}, {path: 'terms'}, {path: '{termModel>/id}'} ], formatter: '.formItermTerms'}" },  
            { formItermTerms : function (a,b,c) {
                debugger;
            }
            });

        var oColumn = new sap.ui.mdc.table.Column(sId,{
                header: sPropertyKey,
                dataProperty: sPropertyKey,
                template: field
            });

            var oData = {
                id: sPropertyKey
            };

            var oModel = new JSONModel(oData);
            oColumn.setModel(oModel,"termModel");
            return oColumn;

        };

        MyTableDelegate.addItem = async (oTable, sPropertyKey) => {
            // const oPropertyInfo = JSONPropertyInfo.find((oPI) => oPI.key === sPropertyKey);
            const sId = oTable.getId() + "---col-" + sPropertyKey;
            return await _addColumn(oTable,sId, sPropertyKey);
        };

        MyTableDelegate.updateBindingInfo = function (oTable, oBindingInfo) {
 
            TableDelegate.updateBindingInfo.call(MyTableDelegate, oTable, oBindingInfo);
            if(!oTable.getBindingContext()){
                return;
            }
            oBindingInfo.path = oTable.getBindingContext().getPath() + oTable.getPayload().bindingPath;
            oBindingInfo.parameters.expand = 'terms';
        };

        return MyTableDelegate;
});
