package net.codejava.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.com.ICICI.entity.BankMaster;

public interface BankMasterRepositoryImpl  extends JpaRepository<BankMaster,Integer>
{

}
