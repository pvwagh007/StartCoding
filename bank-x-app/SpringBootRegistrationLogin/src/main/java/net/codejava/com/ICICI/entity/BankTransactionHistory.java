package net.codejava.com.ICICI.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class BankTransactionHistory
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transitionId;
	
	@ManyToOne
	@JoinColumn(name = "fromAccount")
	private UserBankAccountDetails fromAccount;
	
	@ManyToOne
	@JoinColumn(name = "toAccount")
	private UserBankAccountDetails toAccount;
	
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private String isActive;
	private String isNotified;
	private String amount;
	
	
	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIsNotified() {
		return isNotified;
	}
	public void setIsNotified(String isNotified) {
		this.isNotified = isNotified;
	}
	public Integer getTransitionId() {
		return transitionId;
	}
	public void setTransitionId(Integer transitionId) {
		this.transitionId = transitionId;
	}
	public UserBankAccountDetails getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(UserBankAccountDetails fromAccount) {
		this.fromAccount = fromAccount;
	}
	public UserBankAccountDetails getToAccount() {
		return toAccount;
	}
	public void setToAccount(UserBankAccountDetails toAccount) {
		this.toAccount = toAccount;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/*
	 * @Override public String toString() { return
	 * "BankTransactionHistory [transitionId=" + transitionId + ", fromAccount=" +
	 * fromAccount + ", toAccount=" + toAccount + ", createdDate=" + createdDate +
	 * ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy +
	 * ", modifiedBy=" + modifiedBy + ", isActive=" + isActive + "]"; }
	 */
	
	
	
	
}
