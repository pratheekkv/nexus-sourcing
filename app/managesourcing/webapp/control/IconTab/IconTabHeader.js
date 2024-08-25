/* global history */
sap.ui.define([ 
    "sap/m/IconTabHeader"], 
function(IconTabHeader) {
    "use strict";

    return IconTabHeader.extend("managesourcing.control.IconTab.IconTabHeader", {

        init: function(){
            if(sap.m.IconTabHeader.prototype.init) {
                sap.m.IconTabHeader.prototype.init.apply(this,arguments);
            }
        },

        setSelectedItem: function(oItem,bAPIChange){
            if(this.getSelectedKey() === oItem.mProperties.key){
                return;
            }

            if(!this.validateTab(oItem.mProperties.key)) {
                return;
            }

            if(sap.m.IconTabHeader.prototype.setSelectedItem){
                sap.m.IconTabHeader.prototype.setSelectedItem.apply(this,arguments);
            }
        },
        renderer: {}
    });

});