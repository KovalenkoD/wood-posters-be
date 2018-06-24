package com.woodposters.entity.quote;

/**
 *@author Dmitry Kovalenko
 */
public class SalesOrderIdAndStatus {
    private long salesOrderId;
    private short status;

    public long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
