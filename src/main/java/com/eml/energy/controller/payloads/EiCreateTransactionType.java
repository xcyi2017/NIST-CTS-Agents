

package com.eml.energy.controller.payloads;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eml.energy.dao.*;
import com.eml.energy.model.*;

import java.util.List;
import java.util.Optional;

//package XSD-01.EiPayloads;

/*import XSD-01.EIClasses.actorID;
import XSD-01.EIClasses.EiTenderType;
import XSD-01.EIClasses.refID;
*/

@RestController
@RequestMapping("/transaction")
//public class EiCreateTenderType {
public class EiCreateTransactionType {
	private static final Logger logger = LogManager.getLogger(EiCreateTransactionType.class);
	private static final Logger ledger = LogManager.getLogger("RollingLedger");
	/*
	 * public actorID counterPartyID; public EiTenderType eiTender; public actorID
	 * partyID; public refID requestID;
	 */
		
	@Autowired
	EiResponseType responsedao;
	
	 @RequestMapping("/") public String home(){ return "Hello World!"; }
	 
	
	/*1) To Save a transaction*/
	@PostMapping("/add")
	public EiResponseModel createTransaction(@Valid @RequestBody EiResponseModel bks) {
		ledger.info("Add: "+bks.toString());
		return responsedao.save(bks);
	}
	

	/*2) Get created transaction by ID */
	@GetMapping("/search/{id}")
	public ResponseEntity<Object>  getCreatedTransaction(@PathVariable(value="id") Long refID){
		logger.info("Search: "+refID);
		Object bks = responsedao.findOne(refID);
		if(bks == null) {
			logger.info("Response: NULL");
			return ResponseEntity.notFound().build();
		}
		logger.info("Response: "+bks.toString());
		return ResponseEntity.ok().body(bks);
	}
	
	
	/*3) delete a transaction by refID */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EiResponseModel> deleteTransaction(@PathVariable(value = "id") Long refID) {
		logger.info("Delete: "+refID);
		EiResponseModel bks = responsedao.getOne(refID);
		if(bks == null) {
			logger.info("Response: NULL");
			return ResponseEntity.notFound().build();
		}
		logger.info("Delete: "+bks.toString());
		responsedao.delete(bks);
		return ResponseEntity.ok().build();		
	}
	
	/*4) get all Transactions*/
	@GetMapping("/allTransactions")
	public List<EiResponseModel> getAllTenders(){
		ledger.info("FindAll");
		return responsedao.findAll();
	}
	
	public void finalize() throws Throwable {

	}

}