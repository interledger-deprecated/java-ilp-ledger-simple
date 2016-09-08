package org.interledger.ilp.ledger.api.impl;

import java.util.Date;

import org.interledger.cryptoconditions.Fulfillment;
import org.interledger.cryptoconditions.FulfillmentFactory;

/*
 * Common constants for the app.
 * 
 * This class also is used to avoid nulls for (not-yet-known) variables
 * providing sensible defaults and making code easier to read.
 */
public class Constants {
    public static final Date END_OF_TIME = new Date(-1); // TODO: Recheck. Date newer than any other date.
    public static final String EMPTY = "UN-INITIALIZED";
    public static final Fulfillment NOFULFILLED = FulfillmentFactory.getFulfillmentFromURI(EMPTY);

}
