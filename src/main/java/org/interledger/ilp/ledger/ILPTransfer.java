package org.interledger.ilp.ledger;


import java.util.Date;
import org.interledger.cryptoconditions.Condition;
import org.interledger.cryptoconditions.Fulfillment;
import org.interledger.ilp.core.LedgerTransferStatus;
import org.interledger.ilp.core.exceptions.LedgerTransferException;

/*
 * ILPTransfer entities are created for ILP aware transfer.
 *    local or non-ILP transfers don't use it.
 *    Is used to keep trace of the ILP transaction status.
 * TODO: Move (or create interface) to java-ilp-core
 */
public class ILPTransfer {

    private final String id;                           
    private final String ledger;    
    private final Condition condition; // When the fulfillment arrives compare with Condition
    private final Date expirationAt;
    private final Date proposedAt;

    private String extraInfo;
    private LedgerTransferStatus status;
    private Fulfillment executionFulfillment;
    private Fulfillment cancelationFulfillment;
    private Date preparedAt;
    private Date executedAt;
    private Date rejectedAt;

    ILPTransfer(String id, String ledger, Condition condition, Date expirationAt) {
        if (id == null || ledger == null || condition == null || expirationAt == null) {
            throw new LedgerTransferException(getClass().getSimpleName() + " constructor params can't be null");
        }
        this.id = id;        
        this.ledger = ledger;
        this.condition = condition;
        this.expirationAt = expirationAt;
        this.proposedAt = LedgerTimeProvider.getInstance().getTime();
    }

    public String getId() {
        return id;
    }


    public String getLedger() {
        return ledger;
    }

    public Condition getCondition() {
        return condition;
    }

    public Date getProposedAt() {
        return proposedAt;
    }

    public Date getPreparedAt() {
        return preparedAt;
    }

    public Date getExecutedAt() {
        return executedAt;
    }

    public Date getExpirationAt() {
        return expirationAt;
    }

    public Date getRejectedAt() {
        return rejectedAt;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setStatus(LedgerTransferStatus new_status) {
        // Note: This logic could be placed in a "higher" level class.
        // For now is enought. (Simple implementation)
        if (new_status == LedgerTransferStatus.PROPOSED) {
            throw new LedgerTransferException("new state " + status + " not allowed");
        }

        if (new_status == LedgerTransferStatus.PREPARED) {
            if (status != LedgerTransferStatus.PROPOSED) {
                throw new LedgerTransferException("new state " + status + " not allowed");
            }
            this.preparedAt = getCurrentTime();
        }
        if (new_status == LedgerTransferStatus.EXECUTED) {
            if (status != LedgerTransferStatus.PREPARED) {
                throw new LedgerTransferException("new state " + status + " not allowed");
            }
            this.executedAt = getCurrentTime();
        }
        if (new_status == LedgerTransferStatus.REJECTED) {
            if (status == LedgerTransferStatus.EXECUTED) {
                throw new LedgerTransferException("new state " + status + " not allowed");
            }
            this.rejectedAt = getCurrentTime();
        }

        this.status = new_status;
    }

    public LedgerTransferStatus getStatus() {
        return status;
    }

    public Fulfillment getExecutionFulfillment() {
        return executionFulfillment;
    }

    public void setExecutionFulfillment(Fulfillment executionFulfillment) {
        this.executionFulfillment = executionFulfillment;
    }

    public Fulfillment getCancelationFulfillment() {
        return cancelationFulfillment;
    }

    public void setCancelationFulfillment(Fulfillment cancelationFulfillment) {
        this.cancelationFulfillment = cancelationFulfillment;
    }

    private Date getCurrentTime() {
        return LedgerTimeProvider.getInstance().getTime();
    }

}
