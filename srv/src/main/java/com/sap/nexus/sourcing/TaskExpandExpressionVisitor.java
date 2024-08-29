package com.sap.nexus.sourcing;

import com.sap.cds.ql.cqn.CqnExpand;
import com.sap.cds.ql.cqn.CqnVisitor;

public class TaskExpandExpressionVisitor implements CqnVisitor {

    private boolean valid;

    private static final  String REQUIRED_EXPAND_NAME = "status";
    private static final  String REQUIRED_EXPAND_NAME1 = "type";

    public TaskExpandExpressionVisitor(){
        this.valid = false;
    }


    public boolean expandIsAvailable(){
        return valid;
    }

    @Override
    public void visit(CqnExpand expand){
        CqnVisitor.super.visit(expand);
        String displayName = expand.displayName();

        if(displayName.equals(REQUIRED_EXPAND_NAME) || displayName.equals(REQUIRED_EXPAND_NAME1)) this.valid = true;
    }

}