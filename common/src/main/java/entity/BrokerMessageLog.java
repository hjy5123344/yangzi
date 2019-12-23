package entity;

import java.io.Serializable;
import java.util.Date;

public class BrokerMessageLog implements Serializable {
    private static final long serialVersionUID = 6673891452279939192L;

    private String messageId;
    private String message;
    private Integer tryCount;
    private String status;
    private Long nextRetry;
    private Long createTime;
    private Long updateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Long nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
