package com.sap.nexus.sourcing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.sap.cds.Result;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.draft.DraftCreateEventContext;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.sourcing.Item;
import cds.gen.sourcing.Item_;
import cds.gen.sourcing.Sourcing_;
import cds.gen.sourcing.Task;
import cds.gen.sourcing.Task_;

@Component
@RequestScope
@ServiceName(Sourcing_.CDS_NAME)
public class SourcingHandler implements EventHandler  {


// private static final String HIERARCHY_RANK = "hierarchy_rank";

TaskTreeUtil taskTreeUtil = new TaskTreeUtil();

ItemTreeUtil itemTreeUtil = new ItemTreeUtil();

private static Logger logger = LoggerFactory.getLogger(SourcingHandler.class);

@Before(event = CqnService.EVENT_READ, entity = Task_.CDS_NAME)
public void beforeTaskRead(CdsReadEventContext event) {
    CqnSelect cqn = event.getCqn();

    logger.info("Pratheek >> Original CQN {}", cqn.toString());
    if (!CqnAnalyzer.isCountQuery(cqn)) {
        taskTreeUtil.calcLimitedDescendantsCount = cqn.items().stream().flatMap(i -> i.ofValue())
                .anyMatch(v -> cds.gen.sourcing.Task.LIMITED_DESCENDANT_COUNT.equals(v.displayName()));
                
        TaskExpandExpressionVisitor visitor = new TaskExpandExpressionVisitor();
        event.getCqn().accept(visitor);
        boolean IsExpanded = visitor.expandIsAvailable();
        if(IsExpanded){
            logger.info("Pratheek >>> Expanding Terms");
            cqn = Select.copy(cqn).orderBy(List.of()).inlineCount();
        }else{
            cqn = Select.copy(cqn).columns(List.of()).orderBy(List.of()).inlineCount();
        } 
        event.setCqn(cqn);
    }
}

@After(event = CqnService.EVENT_READ, entity = Task_.CDS_NAME)
public void afterTaskRead(CdsReadEventContext event) {
    Result result = event.getResult();
    CqnSelect cqn = event.getCqn();

    taskTreeUtil.addDrillStateAndDistanceFromRoot(result.listOf(Task.class), cqn);
    event.setResult(result);
}

@Before(event = CqnService.EVENT_READ, entity = Item_.CDS_NAME)
public void beforeItemRead(CdsReadEventContext event) {
    CqnSelect cqn = event.getCqn();
    logger.info("Pratheek >> Original CQN {}", cqn.toString());
    if (!CqnAnalyzer.isCountQuery(cqn)) {
        itemTreeUtil.calcLimitedDescendantsCount = cqn.items().stream().flatMap(i -> i.ofValue())
                .anyMatch(v -> cds.gen.sourcing.Task.LIMITED_DESCENDANT_COUNT.equals(v.displayName()));

        ItemExpandExpressionVisitor visitor = new ItemExpandExpressionVisitor();
        event.getCqn().accept(visitor);
        boolean termIsExpanded = visitor.expandIsAvailable();
        if(termIsExpanded){
            logger.info("Pratheek >>> Expanding Terms");
            cqn = Select.copy(cqn).orderBy(List.of()).inlineCount();
        }else{
            cqn = Select.copy(cqn).columns(List.of()).orderBy(List.of()).inlineCount();
        }              
        
        logger.info("Pratheek >> New CQN {}", cqn.toString());
        event.setCqn(cqn);
    }
}

@After(event = CqnService.EVENT_READ, entity = Item_.CDS_NAME)
public void afterItemRead(CdsReadEventContext event) {
    Result result = event.getResult();
    CqnSelect cqn = event.getCqn();

    itemTreeUtil.addDrillStateAndDistanceFromRoot(result.listOf(Item.class), cqn);
    event.setResult(result);
}

@Before(event = DraftService.EVENT_DRAFT_CREATE, entity = Task_.CDS_NAME)
    public void beforeCreate(DraftCreateEventContext context) {
        CqnInsert insert = (CqnInsert) context.getCqn();
        insert.entries().forEach(entry -> {
            if (entry instanceof Map) {
                ((Map<String, Object>) entry).put(Task_.STATUS_ID, "NS");
            }
        });
    }


}
