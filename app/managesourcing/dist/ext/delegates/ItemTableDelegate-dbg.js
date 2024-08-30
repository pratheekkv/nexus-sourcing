sap.ui.define([
    "sap/ui/mdc/TableDelegate",
    "sap/ui/mdc/Field",
    "sap/ui/model/json/JSONModel",
    "sap/ui/model/Filter",
    "sap/ui/model/FilterOperator",
    "sap/ui/mdc/ValueHelp",
    "sap/ui/mdc/valuehelp/content/FixedList",
    "sap/ui/mdc/valuehelp/content/FixedListItem",
    "sap/ui/mdc/valuehelp/Popover"
], function (TableDelegate,  FieldBase, JSONModel, Filter, FilterOperator,FieldValueHelp,FixedList,FixedListItem,Popover) {
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
                debugger;
                var aProperties = [];
                try{
                    var oContext = oTable.getBindingContext();
                    var aTerms = await oTable.getModel().bindList('terms',oContext,[],[],{$expand:'ranges($select=keyid,description)'}).requestContexts();
                    for (const term of aTerms) { 
                        var range = await term.requestObject('ranges');
                        var id = await term.requestProperty("id");
                        var description = await term.requestProperty("description");
                        var datatype = await term.requestProperty("datatype");
                        
                        aProperties.push({
                            key: id,
                            label: description,
                            path: "",
                            dataType: "sap.ui.model.type." + datatype
                        })
        
                        oTable.addColumn(_addColumn(oTable,oTable.getId() + "---col-" + id, id, datatype, range, term));
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
       var _addColumn = function (oTable,sId, sPropertyKey, datatype, range, term) {
        var field = null;

        var oData = {
            selectedValue: [],
            countries: [
                { key: "US", name: "United States" },
                { key: "DE", name: "Germany" },
                { key: "IN", name: "India" }
            ]
        };

        var oModel = new JSONModel(oData);

        if(range && range.length > 0){
           field = _createDropdown(oTable,sId, sPropertyKey, datatype, range, term);
        }else{
            field =  _createNormalField(oTable,sId, sPropertyKey, datatype, range, term);  
        }
            

        var oColumn = new sap.ui.mdc.table.Column(sId,{
                header: sPropertyKey,
                dataProperty: sPropertyKey,
                template: field
            });

            var oData = {items:[]};

            for (const r of range) { 

                oData.items.push({keyid: r.keyid, description : r.description});
            }

            var oModel = new JSONModel(oData);
            oColumn.setModel(oModel,"valueHelp");
            return oColumn;

        };

        var _createDropdown = function( oTable,sId, sPropertyKey, datatype, range, term){
            var oData = {items:[]};
            for (const r of range) { 

                oData.items.push({keyid: r.keyid, description : r.description});
            }

            var oModel = new JSONModel(oData);
            
                // Create FixedList for Value Help
                var oFixedList = new FixedList({
                    filterList: false,
                    useFirstMatch: false,
                    items: {
                        path: "valueHelp>/items",
                        template: new FixedListItem({
                            key: "{valueHelp>keyid}",
                            text: "{valueHelp>description}",
                            additionalText: "{valueHelp>keyid}"
                        })
                    }
                });

                oFixedList.setModel(oModel,"valueHelp");

                // Create Popover for Value Help
                var oPopover = new Popover({
                    content: oFixedList
                });

                oPopover.setModel(oModel,"valueHelp");

                  // Create FieldValueHelp
            var oFieldValueHelp = new FieldValueHelp({
                delegate: {
                    name: "managesourcing/ext/delegates/ValueHelpDelegate",
                    payload: {
                        searchKeys: ["key", "name"],
                        shouldOpenOnClick: true
                    }
                },
                typeahead: oPopover
            });

            oFieldValueHelp.setModel(oModel,"valueHelp");

              // Create MDC Field with Value Help
              var oField = new FieldBase(sId + '--Field--' + sPropertyKey,{
                value: {path : 'terms', 
                    formatter: function (aTerms)  {
                        
                     }.bind({key : sPropertyKey, datatype : datatype})},
                editMode: "{=${ui>/isEditable}=== true ? 'Editable' : 'ReadOnly'}",
                display: "Description",
                valueHelp: oFieldValueHelp
            });

            oField.setModel(oModel,"valueHelp");

            return oField;
        };

        var _createNormalField = function(oTable,sId, sPropertyKey, datatype, range, term){
           return  new FieldBase(sId + '--Field--' + sPropertyKey, { value: { 
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
                 }.bind({key : sPropertyKey, datatype : datatype}),
                 type: 'sap.ui.model.type.' + datatype
            },
                editMode: "{=${ui>/isEditable}=== true ? 'Editable' : 'ReadOnly'}"
}           ); 
        };

        MyTableDelegate.addItem = async (oTable, sPropertyKey) => {
            // const oPropertyInfo = JSONPropertyInfo.find((oPI) => oPI.key === sPropertyKey);
            const sId = oTable.getId() + "---col-" + sPropertyKey;
            var IsActiveEntity = await oTable.getBindingContext().requestProperty("IsActiveEntity");
            const filters = [new Filter("id", FilterOperator.Eq, sPropertyKey)];
            var termBinding = await oTable.getModel().getKeepAliveContext("/Term(id='" +eventId+"',IsActiveEntity="+IsActiveEntity+")" );
            var dataType = await termBinding.requestProperty("datatype");
            return await _addColumn(oTable,sId, sPropertyKey,dataType,[]);
        };

        MyTableDelegate.updateBindingInfo = function (oTable, oBindingInfo) {
            debugger;
            TableDelegate.updateBindingInfo.call(MyTableDelegate, oTable, oBindingInfo);
            if(!oTable.getBindingContext()){
                return;
            }
            oBindingInfo.path = oTable.getBindingContext().getPath() + oTable.getPayload().bindingPath;
            oBindingInfo.parameters.$expand = 'terms($select=id,value)';
            oBindingInfo.parameters.$count = true;
            oBindingInfo.parameters.$$aggregation = {
                hierarchyQualifier: "ItemHierarchy"
            };
        };

        return MyTableDelegate;
});
