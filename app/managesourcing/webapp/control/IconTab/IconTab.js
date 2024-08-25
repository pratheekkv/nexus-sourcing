sap.ui.define([ 
    "sap/m/IconTabBar",
    "managesourcing/control/IconTab/IconTabHeader"], 
function(IconTabBar, IconTabHeader) {

    return IconTabBar.extend("managesourcing.control.IconTab.IconTab", {

        metadata:{
            aggregations: {
                _header: {
                    type: "managesourcing.control.IconTab.IconTabHeader",
                    multiple: false,
                    visibility: 'hidden'
                }
            }
        },

        _getIconTabHeader: function(){
           var c = this.getAggregation('_header');
           if(!c){
            c = new IconTabHeader(this.getId() + '--header', {});
            this.setAggregation('_header',c,true);
           }
           return c;
        },

        renderer: {}
    });

});