package com.example.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CategoryVO;
import com.example.domain.DepositAccountVO;
import com.example.domain.DepositVO;
import com.example.domain.LoansAccountVO;
import com.example.domain.LoansVO;
import com.example.mapper_oracle.CategoryMapper;
import com.example.mapper_oracle.DepositAccountMapper;
import com.example.mapper_oracle.LoansAccountMapper;

@Service
public class DepositService {
	@Autowired
	DepositAccountMapper mapper;
	@Autowired
	LoansAccountMapper lmapper;
	@Autowired
	CategoryMapper cmapper;
	
	@Transactional
	public void depositinsert(DepositVO vo) {
		mapper.depositinsert(vo);
		mapper.depositaccountupdate(vo.getDepositbalance(),vo.getDeposit_depositaccountcode());
	}
	
	@Transactional
	public void depositaccountinsert(DepositAccountVO vo) {
		mapper.depositaccountinsert(vo);
		DepositVO dvo=new DepositVO();
		dvo.setDeposit_companycode("153-60-00064");
		dvo.setDeposit_day(vo.getDepositaccount_startday());
		dvo.setDeposit_depositaccountcode(vo.getDepositaccountcode());
		dvo.setDepositamount("0");
		dvo.setDepositbalance(vo.getDepositaccountamount());
		dvo.setDeposittype("�Ա�");
		mapper.depositinsert(dvo);
	}
	
	@Transactional
	public void loansaccountinsert(LoansAccountVO vo) {
		lmapper.loansaccountinsert(vo);
		LoansVO lvo=new LoansVO();
		lvo.setLoans_companycode("153-60-00064");
		lvo.setLoans_loansaccountcode(vo.getLoansaccountcode());
		lvo.setLoans_repaymentday(vo.getLoansaccount_startday());
		lvo.setLoansamount("0");
		lvo.setLoansbalance(vo.getLoansaccountamount());
		lmapper.loansinsert(lvo);
	}
	
	@Transactional
	public void categoryupdate(CategoryVO vo){
		cmapper.categoryupdate(vo);
		cmapper.categoryinsert(vo);
	}
}
