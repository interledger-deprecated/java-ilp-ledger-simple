package org.interledger.ilp.ledger.api;

import java.util.Date;


import org.interledger.cryptoconditions.Condition;
import org.interledger.cryptoconditions.Fulfillment;
import org.interledger.ilp.ledger.api.impl.Constants;

/*
 * ILPTransfer entities are created for ILP aware transfer.
 *    local or non-ILP transfers don't use it.
 *    Is used to keep trace of the ILP transaction status.
 * TODO: Move (or create interface) to java-ilp-ledger-api
 */
public class ILPTransfer {
    // Final fields. They MUST not change once the Transfer is created.
    private final String     id;                       // FIXME: Use ILPTranferID class
    private final String     uuid;   /*char(36)*/      // FIXME: Use UUID class
    private final String     ledger; /*varchar(1024)*/ // FIXME: Use LedgerID Class ? 
    private final Condition  condition; // When the fulfillment arrives compare with Condition
    private final Date       expiration_DTTM;
    private final Date       proposed_DTTM;

    private String extraInfo               = Constants.EMPTY;
    private TransferStatus   status        = TransferStatus.PROPOSED;
    private Fulfillment      executionFF   = Constants.NOFULFILLED;
    private Fulfillment      cancelationFF = Constants.NOFULFILLED;
    private Date             prepared_DTTM = Constants.END_OF_TIME;
    private Date             executed_DTTM = Constants.END_OF_TIME;
    private Date             rejected_DTTM = Constants.END_OF_TIME;

    ILPTransfer(String id, String uuid, String ledger, Condition condition, Date expiration_DTTM) {
        if (id == null || uuid == null || ledger == null || condition == null || expiration_DTTM == null  ) {
            throw new RuntimeException("ILPTransfer constructor params can't be null");
        }
        this.id = id;
        this.uuid = uuid;
        this.ledger = ledger;
        this.condition = condition;
        this.expiration_DTTM = expiration_DTTM;
        this.proposed_DTTM = DTTMClock.getCurrentTime();
    }

    public String getId() { return id; }

    public String getUuid() { return uuid; }

    public String getLedger() { return ledger; }

    public Condition getCondition() { return condition; }

    public Date getExpiration_DTTM() { return expiration_DTTM; }

    public Date getProposed_DTTM() { return proposed_DTTM; }

    public void setExtraInfo(String extraInfo) { this.extraInfo = extraInfo; }
    public String getExtraInfo() { return extraInfo; }

    public void setStatus(TransferStatus new_status) {
        // Note: This logic could be placed in a "higher" level class.
        // For now is enought. (Simple implementation)
        if (new_status == TransferStatus.PROPOSED) {
            throw new RuntimeException("new state "+status+" not allowed");
        }

        if (new_status == TransferStatus.PREPARED ) {
            if (status != TransferStatus.PROPOSED) {
                throw new RuntimeException("new state "+status+" not allowed");
            }
            this.prepared_DTTM = DTTMClock.getCurrentTime();
        }
        if (new_status == TransferStatus.EXECUTED ) {
            if (status != TransferStatus.PREPARED) {
                throw new RuntimeException("new state "+status+" not allowed");
            }
            this.executed_DTTM = DTTMClock.getCurrentTime();
        }
        if (new_status == TransferStatus.REJECTED ) {
            if (status != TransferStatus.PREPARED) {
                throw new RuntimeException("new state "+status+" not allowed");
            }
            this.rejected_DTTM = DTTMClock.getCurrentTime();
        }

        this.status = new_status;
    }
    public TransferStatus getStatus() { return status; }


    public void setExecutionFF(Fulfillment executionFF) { this.executionFF = executionFF; }
    public Fulfillment getExecutionFF() { return executionFF; }


    public void setCancelationFF(Fulfillment cancelationFF) { this.cancelationFF = cancelationFF; }
    public Fulfillment getCancelationFF() { return cancelationFF; }

    public Date getPrepared_DTTM() { return prepared_DTTM; }

    public Date getExecuted_DTTM() { return executed_DTTM; }

    public Date getRejected_DTTM() { return rejected_DTTM; }

}
