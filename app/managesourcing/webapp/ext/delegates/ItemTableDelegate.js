sap.ui.define([
    "sap/ui/mdc/TableDelegate",
    "sap/ui/mdc/field/FieldBase"
], function (TableDelegate, FieldBase) {
    "use strict";

        var MyTableDelegate = Object.assign({}, TableDelegate);


        MyTableDelegate.fetchProperties = function (oTable) {
            var aProperties = [];
            var oModel = oTable.getModel("yourJSONModel");
            var oData = oModel.getData();

            // Assuming the JSON model has a 'columns' array with definitions
            if (oData && oData.columns) {
                oData.columns.forEach(function (oColumnDef) {
                    aProperties.push({
                        name: oColumnDef.name,
                        path: oColumnDef.path,
                        label: oColumnDef.label,
                        type: oColumnDef.type // You can handle specific types as needed
                    });
                });
            }

            return Promise.resolve(aProperties);
        };

        /**
         * Bind the columns to the table.
         */
        MyTableDelegate.addColumn = function (oTable, oColumnInfo) {
            var oColumn = new sap.ui.mdc.table.Column({
                header: oColumnInfo.label,
                dataProperty: oColumnInfo.path,
                template: new FieldBase({
                    value: "{" + oColumnInfo.path + "}"
                })
            });

            oTable.addColumn(oColumn);
        };

        return MyTableDelegate;
});
