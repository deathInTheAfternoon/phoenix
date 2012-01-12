package com.aimia.endeavour.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.drools.WorkingMemory;
import org.drools.event.AfterActivationFiredEvent;
import org.drools.event.DefaultAgendaEventListener;
import org.drools.rule.Rule;
import org.drools.spi.Activation;

public class AgendaEventListener extends DefaultAgendaEventListener {
	final Logger logger = LoggerFactory.getLogger(AgendaEventListener.class);

	@Override
	public void afterActivationFired(AfterActivationFiredEvent event, WorkingMemory workingMemory) {
		Activation a = event.getActivation();
		Rule rule = a.getRule();
		logger.debug("Firing Rule: " + rule.getName());
		super.afterActivationFired(event, workingMemory);
	}

}