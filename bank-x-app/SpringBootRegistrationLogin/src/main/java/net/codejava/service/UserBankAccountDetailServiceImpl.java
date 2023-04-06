package net.codejava.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import net.codejava.User;
import net.codejava.UserRepository;
import net.codejava.com.ICICI.entity.BankMaster;
import net.codejava.com.ICICI.entity.BankTransactionHistory;
import net.codejava.com.ICICI.entity.TransferToSaving;
import net.codejava.com.ICICI.entity.UserBankAccountDetails;
import net.codejava.repo.BankMasterRepositoryImpl;
import net.codejava.repo.BankTransactionHistoryRepositoryImpl;
import net.codejava.repo.UserBankAccountDetailRepositoryImpl;

@Service
public class UserBankAccountDetailServiceImpl  implements UserBankAccountDetailService
{
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BankMasterRepositoryImpl bankMasterRepositoryImpl;
	
	@Autowired
	private UserBankAccountDetailRepositoryImpl detailRepositoryImpl;
	
	@Autowired
	private BankTransactionHistoryRepositoryImpl historyRepositoryImpl;
	
	
	
	
	@Transactional
	public boolean addNewCustomerWithJoiningBonus(User user)
	{
		Optional  opt = bankMasterRepositoryImpl.findById(1);
		System.out.println("opt"+opt.isEmpty());
		if(opt.isPresent())
		{
			BankMaster bankMaster = (BankMaster) opt.get();
			System.out.println("bankMaster"+bankMaster);
			UserBankAccountDetails accountDetails = new UserBankAccountDetails();
			accountDetails.setUser(user);
			accountDetails.setCurrentAccountBelance("00.00");
			accountDetails.setBankMaster(bankMaster);
			accountDetails.setSavingAccountBalance("500.00");
			System.out.println("user"+user);
			System.out.println("accointdetail"+accountDetails);
			detailRepositoryImpl.save(accountDetails);
			userRepo.save(user);
			return true;
			
		}
		else
		{
			return false;
		}
	
	}
	public List<UserBankAccountDetails> getBankAccountDeatilsByUserId(User user)
	{
		UserBankAccountDetails accountDetails=  detailRepositoryImpl.findByUserIdAndBankMasterId(user.getId(),1);
		System.out.println("account detail"+accountDetails.getCurrentAccountBelance());
		System.out.println("account detail"+accountDetails.getSavingAccountBalance());
		List<UserBankAccountDetails> a= new ArrayList();
		a.add(accountDetails);
		return a;
	}
	@Transactional
	public boolean tranferAmountToCurrentAccount(TransferToSaving saving)
	{
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userRepo.findByEmail(username);
		UserBankAccountDetails accountDetails=  detailRepositoryImpl.findByUserIdAndBankMasterId(user.getId(),1);
		
		Float amountToBeTransfer=new Float(saving.getAmount());
		Float savingAccountBalance=new Float(accountDetails.getSavingAccountBalance());
		if(amountToBeTransfer > 0 && amountToBeTransfer < savingAccountBalance)
		{
			Float currentAccountBalance= new Float(accountDetails.getCurrentAccountBelance());
			Float savingAccountBalnce = new Float(accountDetails.getSavingAccountBalance());
			//current=900
			//saving=390
			accountDetails.setSavingAccountBalance(savingAccountBalance-amountToBeTransfer+"");
			accountDetails.setCurrentAccountBelance(currentAccountBalance+amountToBeTransfer+"");
			detailRepositoryImpl.save(accountDetails);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	@Transactional
	public boolean tranferAmountToSavingAccount(TransferToSaving saving)
	{
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userRepo.findByEmail(username);
		UserBankAccountDetails accountDetails=  detailRepositoryImpl.findByUserIdAndBankMasterId(user.getId(),1);
		
		Float amountToBeTransfer=new Float(saving.getAmount());
		Float currentAccountBalance=new Float(accountDetails.getCurrentAccountBelance());
		if(amountToBeTransfer > 0 && amountToBeTransfer < currentAccountBalance)
		{
			//Float currentAccountBalance= new Float(accountDetails.getCurrentAccountBelance());
			Float savingAccountBalnce = new Float(accountDetails.getSavingAccountBalance());
			//current=900
			//saving=390
			accountDetails.setSavingAccountBalance(savingAccountBalnce+amountToBeTransfer+"");
			accountDetails.setCurrentAccountBelance(currentAccountBalance-amountToBeTransfer+"");
			detailRepositoryImpl.save(accountDetails);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	@Transactional
	public boolean tranferAmountToAccount(TransferToSaving saving)
	{
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userRepo.findByEmail(username);
		UserBankAccountDetails accountDetails=  detailRepositoryImpl.findByUserIdAndBankMasterId(user.getId(),1);
		
		Float amountToBeTransfer=new Float(saving.getAmount());
		Float currentAccountBalance=new Float(accountDetails.getCurrentAccountBelance());
		
		
		if(amountToBeTransfer > 0 && amountToBeTransfer < currentAccountBalance)
		{
			//Float currentAccountBalance= new Float(accountDetails.getCurrentAccountBelance());
			//Float savingAccountBalnce = new Float(accountDetails.getSavingAccountBalance());
			//current=900
			//saving=390
			//accountDetails.setSavingAccountBalance(savingAccountBalnce+amountToBeTransfer+"");
			
			String bankAccountNumber = saving.getToAccountNumber();
			Optional<UserBankAccountDetails> optional= detailRepositoryImpl.findById(new Integer(bankAccountNumber));
			if(optional.isPresent())
			{
				UserBankAccountDetails userAccountTo=  optional.get();
				Long userIdd =userAccountTo.getUser().getId();
				if(user.getId().equals(userIdd))
				{
					return false;
				}
				
				Float currentBalanceTo = new Float(userAccountTo.getCurrentAccountBelance());
				userAccountTo.setCurrentAccountBelance(currentBalanceTo+amountToBeTransfer+"");
				accountDetails.setCurrentAccountBelance(currentAccountBalance-amountToBeTransfer+"");
				
				
				BankTransactionHistory bankTransactionHistory = new BankTransactionHistory();
				bankTransactionHistory.setToAccount(userAccountTo);
				bankTransactionHistory.setFromAccount(accountDetails);
				bankTransactionHistory.setIsNotified("Y");
				bankTransactionHistory.setCreatedDate(new Date());
				bankTransactionHistory.setIsActive("1");
				bankTransactionHistory.setAmount(amountToBeTransfer+"");
				
				detailRepositoryImpl.save(userAccountTo);
				detailRepositoryImpl.save(accountDetails);
				historyRepositoryImpl.save(bankTransactionHistory);
				
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
		else
		{
			return false;
		}
		
	}
	public List<BankTransactionHistory> getBankTransction()
	{
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userRepo.findByEmail(username);
		List l =historyRepositoryImpl.findTransactionHistoryByUserId(user.getId());
		System.out.println("history is "+l);
		return l;
	}
	public List<BankTransactionHistory> getBankTransctionForNotifications()
	{
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userRepo.findByEmail(username);
		List l =historyRepositoryImpl.findTransactionHistoryByUserIdForNotifications(user.getId());
		System.out.println("history is "+l);
		return l;
	}
	@Transactional
	public Integer clearNotificationForUser()
	{
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userRepo.findByEmail(username);
		System.out.println("clear my notifications");
		return historyRepositoryImpl.clearNotifications(user.getId());
	}
	
	public Long getNotificationCount()
	{
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		User user = userRepo.findByEmail(username);
		return historyRepositoryImpl.findTransactionHistoryByUserIdForNotificationsCount(user.getId());
	}
}
