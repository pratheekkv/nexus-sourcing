sap.ui.define([
    "sap/ui/mdc/TableDelegate",
    "sap/ui/mdc/Field",
    "sap/ui/model/json/JSONModel",
    "sap/ui/model/Filter",
    "sap/ui/model/FilterOperator"
], function (TableDelegate,  FieldBase, JSONModel, Filter, FilterOperator) {
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
                            path: "",
                            dataType: "sap.ui.model.type." + datatype
                        })
        
                        oTable.addColumn(_addColumn(oTable,oTable.getId() + "---col-" + id, id, datatype));
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
       var _addColumn = function (oTable,sId, sPropertyKey, datatype) {
 
        var field = new FieldBase({ value: { 
                                                path : 'terms', 
                                                formatter: function (aTerms)  {
                                                    if(!aTerms){
                                                        return "X";
                                                    }
                                                    var result = aTerms.filter(term => {
                                                        return term.id === this.key
                                                      });
                                                      if(result.length < 1){
                                                        return;
                                                      }
                                                      if(datatype === "Integer")
                                                        return parseInt(result[0].value);
                                                      else
                                                        return result[0].value;
                                                 }.bind({key : sPropertyKey, datatype : datatype})
                                            },
                                    editMode: "{=${ui>/isEditable}=== true ? 'Editable' : 'ReadOnly'}"
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
            var IsActiveEntity = await oTable.getBindingContext().requestProperty("IsActiveEntity");
            const filters = [new Filter("id", FilterOperator.Eq, sPropertyKey)];
            var termBinding = await oTable.getModel().getKeepAliveContext("/Term(id='" +eventId+"',IsActiveEntity="+IsActiveEntity+")" );
            var dataType = await termBinding.requestProperty("datatype");
            return await _addColumn(oTable,sId, sPropertyKey,dataType);
        };

        MyTableDelegate.updateBindingInfo = function (oTable, oBindingInfo) {
 
            TableDelegate.updateBindingInfo.call(MyTableDelegate, oTable, oBindingInfo);
            if(!oTable.getBindingContext()){
                return;
            }
            oBindingInfo.path = oTable.getBindingContext().getPath() + oTable.getPayload().bindingPath;
            oBindingInfo.parameters.$expand = 'terms($select=id,value)';
        };

        return MyTableDelegate;
});
