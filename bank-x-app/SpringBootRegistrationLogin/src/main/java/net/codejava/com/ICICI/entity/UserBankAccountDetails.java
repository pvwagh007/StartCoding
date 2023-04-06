package net.codejava.com.ICICI.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import net.codejava.User;

@Entity
public class UserBankAccountDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bankDetailId;
	private String savingAccountBalance;
	private String currentAccountBelance;
	
	@ManyToOne
	@JoinColumn(name="fkUserInfoId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="fkBankMasterId")
	private BankMaster bankMaster;
	
	
	@OneToMany(mappedBy = "fromAccount")
	private Set<BankTransactionHistory> fromAccont;
	
	@OneToMany(mappedBy = "toAccount")
	private Set<BankTransactionHistory> toAccount;

	
	
	public Set<BankTransactionHistory> getFromAccont() {
		return fromAccont;
	}

	public void setFromAccont(Set<BankTransactionHistory> fromAccont) {
		this.fromAccont = fromAccont;
	}

	public Set<BankTransactionHistory> getToAccount() {
		return toAccount;
	}

	public void setToAccount(Set<BankTransactionHistory> toAccount) {
		this.toAccount = toAccount;
	}

	public Integer getBankDetailId() {
		return bankDetailId;
	}

	public void setBankDetailId(Integer bankDetailId) {
		this.bankDetailId = bankDetailId;
	}

	public String getSavingAccountBalance() {
		return savingAccountBalance;
	}

	public void setSavingAccountBalance(String savingAccountBalance) {
		this.savingAccountBalance = savingAccountBalance;
	}

	public String getCurrentAccountBelance() {
		return currentAccountBelance;
	}

	public void setCurrentAccountBelance(String currentAccountBelance) {
		this.currentAccountBelance = currentAccountBelance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BankMaster getBankMaster() {
		return bankMaster;
	}

	public void setBankMaster(BankMaster bankMaster) {
		this.bankMaster = bankMaster;
	}

	/*
	 * @Override public String toString() { return
	 * "UserBankAccountDetails [bankDetailId=" + bankDetailId +
	 * ", savingAccountBalance=" + savingAccountBalance + ", currentAccountBelance="
	 * + currentAccountBelance + ", user=" + user + ", bankMaster=" + bankMaster +
	 * ", fromAccont=" + fromAccont + ", toAccount=" + toAccount + "]"; }
	 */
	
	
	
	
	
	
}
