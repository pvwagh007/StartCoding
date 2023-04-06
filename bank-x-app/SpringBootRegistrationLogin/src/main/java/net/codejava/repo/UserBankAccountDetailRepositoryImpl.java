package net.codejava.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.User;
import net.codejava.com.ICICI.entity.UserBankAccountDetails;

public interface UserBankAccountDetailRepositoryImpl extends JpaRepository<UserBankAccountDetails, Integer>
{
	@Query("SELECT u FROM UserBankAccountDetails u WHERE u.user.id = ?1 and bankMaster.bankId = ?2")
	public UserBankAccountDetails findByUserIdAndBankMasterId(Long userid, Integer bankid);
}
