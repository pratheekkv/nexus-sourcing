sap.ui.define([
    "sap/ui/mdc/TableDelegate",
    "sap/ui/mdc/field/FieldBase"
], function (TableDelegate,  FieldBase) {
    "use strict";

        var MyTableDelegate = Object.assign({}, TableDelegate);


        MyTableDelegate.fetchProperties = async function (oTable) {
            var aProperties = [];
            var oContext = oTable.getBindingContext();
            try{
              var aTerms =  await oTable.getModel().bindList(oContext.getPath() + '/terms').requestContexts();

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
              }

            } catch(err){
                
            }

            return Promise.resolve(aProperties);
        };

        /**
         * Bind the columns to the table.
         */
       var _addColumn = function (oTable,sId, sPropertyKey) {
            var oColumn = new sap.ui.mdc.table.Column({
                header: sPropertyKey,
                propertyKey: 'terms',
                template: new FieldBase({
                    value: "{ parts: [ {path: 'id'}, {path: 'terms'}, {value: '" + sPropertyKey + "'} ], formatter: '.formatProperties'}",
                })
            });

            oTable.addColumn(oColumn);
        };

        MyTableDelegate.addItem = async (oTable, sPropertyKey) => {
            // const oPropertyInfo = JSONPropertyInfo.find((oPI) => oPI.key === sPropertyKey);
            const sId = oTable.getId() + "---col-" + sPropertyKey;
            return await _addColumn(oTable,sId, sPropertyKey);
        };

        MyTableDelegate.updateBindingInfo = function (oTable, oBindingInfo) {
 
            TableDelegate.updateBindingInfo.call(MyTableDelegate, oTable, oBindingInfo);
            oBindingInfo.path = oTable.getBindingContext().getPath() + oTable.getPayload().bindingPath;
            oBindingInfo.parameters.expand = 'terms';
        };

        return MyTableDelegate;
});
