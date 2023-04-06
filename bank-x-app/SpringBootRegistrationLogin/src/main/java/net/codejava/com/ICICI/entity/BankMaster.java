package net.codejava.com.ICICI.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "bankMaster")
public class BankMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bankId;
	
	@Column(nullable = false, unique = true, length = 45)
	private String bankName;
	
	@Column(nullable = false, unique = true, length = 45)
	private String ifscCode;
	
	/*
	 * @OneToMany(mappedBy = "bankMaster") private Set<UserBankLiniking>
	 * setBankMaster;
	 */
	
	@OneToMany(mappedBy = "bankMaster")
	private Set<UserBankAccountDetails> details;
	
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private String creditTransctionCharge;
	private String debitTransctionCharge;
	private String isActive;
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public Set<UserBankAccountDetails> getDetails() {
		return details;
	}
	public void setDetails(Set<UserBankAccountDetails> details) {
		this.details = details;
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
	public String getCreditTransctionCharge() {
		return creditTransctionCharge;
	}
	public void setCreditTransctionCharge(String creditTransctionCharge) {
		this.creditTransctionCharge = creditTransctionCharge;
	}
	public String getDebitTransctionCharge() {
		return debitTransctionCharge;
	}
	public void setDebitTransctionCharge(String debitTransctionCharge) {
		this.debitTransctionCharge = debitTransctionCharge;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/*
	 * @Override public String toString() { return "BankMaster [bankId=" + bankId +
	 * ", bankName=" + bankName + ", ifscCode=" + ifscCode + ", details=" + details
	 * + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate +
	 * ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy +
	 * ", creditTransctionCharge=" + creditTransctionCharge +
	 * ", debitTransctionCharge=" + debitTransctionCharge + ", isActive=" + isActive
	 * + "]"; }
	 */
	
	
	
	
		
	
	
	
	
	
	
}
