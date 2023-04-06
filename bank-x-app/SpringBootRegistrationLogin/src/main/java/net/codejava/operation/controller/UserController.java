
package net.codejava.operation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.codejava.User;
import net.codejava.com.ICICI.entity.BankTransactionHistory;
import net.codejava.com.ICICI.entity.TransferToSaving;
import net.codejava.service.UserBankAccountDetailServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserBankAccountDetailServiceImpl accountDetailService;
	
	
	
	
	@PostMapping("/transferToCurrent")
	public String transferToCurrent(TransferToSaving transerToSaving,Model model)
	{
		model.addAttribute("transferToSaving",new TransferToSaving());
		List<TransferToSaving> transferToSavingList = new ArrayList();
		transerToSaving.setStatus("Successfully Transferd");
	
		boolean status = accountDetailService.tranferAmountToCurrentAccount(transerToSaving);
		if(status)
		{
			transerToSaving.setStatus("Successfully Transferd");
			transferToSavingList.add(transerToSaving);
			model.addAttribute("transferToSavingList",transferToSavingList);
			return "transferStatusPage";
		}
		else
		{
			transerToSaving.setStatus("SomeThing Went Wrong Transferd");
			transferToSavingList.add(transerToSaving);
			model.addAttribute("transferToSavingList",transferToSavingList);
			return "transferStatusPage";
		}
		
	}
	
	@GetMapping("/getTransferPage")
	public String getTransferToSavingPage(Model model)
	{
		model.addAttribute("transferToSaving",new TransferToSaving());
		return "transferPage";
	}
	
	@GetMapping("/getTransferToSavingPage")
	public String getTransferToSaving(Model model)
	{
		model.addAttribute("transferToSaving",new TransferToSaving());
		return "savingTransferPage";
	}
	
	@GetMapping("/getTransferToAnotherAccount")
	public String getTransferToAnotherAccount(Model model)
	{
		model.addAttribute("transferToSaving",new TransferToSaving());
		return "accountTransfer";
	}
	
	@GetMapping("/getTransferHistoryPage")
	public String getTransferHistoryPage(Model model)
	{
		List<BankTransactionHistory> list = accountDetailService.getBankTransction();
	    model.addAttribute("userTransactionHistoryList",list);
		return "transferHistory";
	}
	
	
	@GetMapping("/getMyNotificationsPage")
	public String showMyNotifications(Model model) {
		List<BankTransactionHistory> list = accountDetailService.getBankTransctionForNotifications();
	    model.addAttribute("userTransactionHistoryList",list);
		return "myNotifications";
	}
	
	@GetMapping("/clearNotifications")
	@ResponseBody
	public Integer clearNotifications() {
		Integer count = accountDetailService.clearNotificationForUser();
	    //model.addAttribute("userTransactionHistoryList",list);
		//return "myNotifications";
		return count;
	}
	
	@GetMapping("/getNotificationCount")
	@ResponseBody
	public String getNotificationCount(Model model) {
		Long count= accountDetailService.getNotificationCount();
	   // model.addAttribute("userTransactionHistoryList",list);
		return count+"";
	}
	
	
	
	@PostMapping("/transferToSaving")
	public String transferToSavingAccount(TransferToSaving transerToSaving,Model model)
	{
		model.addAttribute("transferToSaving",new TransferToSaving());
		List<TransferToSaving> transferToSavingList = new ArrayList();
		//transerToSaving.setStatus("Successfully Transferd");
	
		boolean status = accountDetailService.tranferAmountToSavingAccount(transerToSaving);
		if(status)
		{
			transerToSaving.setStatus("Successfully Transferd");
			transferToSavingList.add(transerToSaving);
			model.addAttribute("transferToSavingList",transferToSavingList);
			return "transferStatusPage";
		}
		else
		{
			transerToSaving.setStatus("SomeThing Went Wrong Transferd");
			transferToSavingList.add(transerToSaving);
			model.addAttribute("transferToSavingList",transferToSavingList);
			return "transferStatusPage";
		}
		
	}
	@PostMapping("/transferToAccount")
	public String transferToAccount(TransferToSaving transerToSaving,Model model)
	{
		model.addAttribute("transferToSaving",new TransferToSaving());
		List<TransferToSaving> transferToSavingList = new ArrayList();
		//transerToSaving.setStatus("Successfully Transferd");
	
		boolean status =accountDetailService.tranferAmountToAccount(transerToSaving);
		if(status)
		{
			transerToSaving.setStatus("Successfully Transferd");
			transferToSavingList.add(transerToSaving);
			model.addAttribute("transferToSavingList",transferToSavingList);
			return "transferStatusPage";
		}
		else
		{
			transerToSaving.setStatus("SomeThing Went Wrong Transferd");
			transferToSavingList.add(transerToSaving);
			model.addAttribute("transferToSavingList",transferToSavingList);
			return "transferStatusPage";
		}
		
	}
	
	
	
	
}
