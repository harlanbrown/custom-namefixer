package org.nuxeo.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nuxeo.ecm.core.api.event.CoreEventConstants;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventListener;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;
import org.nuxeo.ecm.core.api.DocumentExistsException;
import org.nuxeo.runtime.transaction.TransactionHelper;

/**
 * @author Stephane Lacoin at Nuxeo (aka matic)
 */
public class CustomDuplicatedNameFixer implements EventListener {
    private static final Logger log = LogManager.getLogger(CustomDuplicatedNameFixer.class);
    @Override
    public void handleEvent(Event event) {
        log.info("HI");
        DocumentEventContext context = (DocumentEventContext) event.getContext();
        Boolean destinationExists = (Boolean) context.getProperty(CoreEventConstants.DESTINATION_EXISTS);
        if (!destinationExists) {
            return;
        } else {
            log.info("nope");
            TransactionHelper.setTransactionRollbackOnly();
            throw new DocumentExistsException("nope");
        } 

//        String name = (String) context.getProperty(CoreEventConstants.DESTINATION_NAME);
//        name += '.' + String.valueOf(System.currentTimeMillis());
//        context.setProperty(CoreEventConstants.DESTINATION_NAME, name);
    }

}
