package org.thakurn.phoenix.experiments.components.scope;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/**
 * @author Josh Long
 * @since 5,3
 */

public class Delegate2 implements JavaDelegate {

	private Logger log = Logger.getLogger( getClass().getName());

	@Autowired private StatefulObject statefulObject;

	public void execute(DelegateExecution execution) throws Exception {

		this.statefulObject.increment();

		Assert.assertNotNull( "the 'scopedCustomer' reference can't be null", this.statefulObject);
		Assert.assertNotNull( "the 'scopedCustomer.name' property should be non-null, since it was set in a previous delegate bound to this very thread", this.statefulObject.getName() );
		log.info( "the 'uuid' value retrieved from the ScopedCustomer#name property is '" +  this.statefulObject.getName()+ "' in "+getClass().getName());
	}
}
