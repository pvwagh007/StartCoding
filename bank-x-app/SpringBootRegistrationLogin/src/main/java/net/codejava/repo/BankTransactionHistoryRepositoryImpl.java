package net.codejava.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.codejava.com.ICICI.entity.BankTransactionHistory;

public interface BankTransactionHistoryRepositoryImpl  extends JpaRepository<BankTransactionHistory, Integer>
{
	@Query("SELECT u FROM BankTransactionHistory u WHERE u.fromAccount.user.id = ?1")
	public List<BankTransactionHistory> findTransactionHistoryByUserId(Long userid);
	
	@Query("SELECT u FROM BankTransactionHistory u WHERE u.toAccount.user.id = ?1 and u.isNotified='Y' ")
	public List<BankTransactionHistory> findTransactionHistoryByUserIdForNotifications(Long userid);
	
	@Query("SELECT count(u.isNotified) FROM BankTransactionHistory u WHERE u.toAccount.user.id = ?1 and u.isNotified='Y' ")
	public Long findTransactionHistoryByUserIdForNotificationsCount(Long userid);
	
	
	@Modifying
	@Query(value = " update bank_transaction_history   SET is_notified ='N' WHERE to_account=:a  ", nativeQuery = true)
	public Integer clearNotifications(@Param("a") Long userid);
	
	//update  BankTransactionHistory u set u.isNotified='N' WHERE u.toAccount.user.id =:a

}