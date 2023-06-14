package com.marvel.core.workflow;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WorkflowProcess.class,
        property = {"process.label=Practice Second Workflow Process" })
public class CustomWorkFlowStep2 implements WorkflowProcess{

    protected final Logger logger = LoggerFactory.getLogger(CustomWorkFlowStep2.class);

    public void execute(WorkItem workItem, WorkflowSession wfSession,
                        MetaDataMap metaDataMap) throws WorkflowException {
        String nickName = workItem.getWorkflow().getMetaDataMap().get("nickName", String.class);
        String interest = workItem.getWorkflow().getMetaDataMap().get("interest", String.class);

        logger.error("nickName >>>>>>> {}", nickName);
        logger.error("interest >>>>>>> {}", interest);
    }
}
