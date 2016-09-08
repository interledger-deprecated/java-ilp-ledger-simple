package org.interledger.ilp.ledger.api;

// TODO:(1) Move to java-ilp-ledger-api 

public enum TransferStatus { // FIXME
    PROPOSED(0),
    PREPARED(1),
    EXECUTED(2),
    REJECTED(3);

//    private final int status;

    TransferStatus(int status) {
//        this.status = status;
    }

    public static TransferStatus valueOf(int status) {
        switch (status) {
            case 0:
                return TransferStatus.PROPOSED;
            case 1:
                return TransferStatus.PREPARED;
            case 2:
                return TransferStatus.EXECUTED;
            case 3:
                return TransferStatus.REJECTED;
            default:
                throw new IllegalArgumentException("Invalid TransferStatus status " + status);
        }
    }

}
