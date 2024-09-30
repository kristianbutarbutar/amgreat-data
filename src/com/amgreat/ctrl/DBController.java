package com.amgreat.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amgreat.lib.DBWrapperInterface;
import com.amgreat.vo.AttributeVO;
import com.amgreat.vo.RecordVO;
import com.amgreat.vo.ResponseVO;

@RestController
public class DBController {
	
	@Autowired private DBWrapperInterface db;
	
	
	@PostMapping("/amgreat/api/data/cmd")
	public RecordVO doCommand(@RequestBody AttributeVO r) {
		RecordVO rp = new RecordVO();
		try {
			boolean bool = db.doExecute(r,  rp);
		} catch (Exception e) {
			System.out.println( "DBController.doCommand: " + e.getMessage() );
		}
		return rp;
	}

		
	@RequestMapping("/amgreat/api/data/ping")
	public RecordVO getCommand(){
		
		AttributeVO req = new AttributeVO(); AttributeVO preq = req;
		
		req.setTabelName("T_COLUMNS");
		req.setColumnName("ID");req.setName("__ID");req.setValue(""); req.setType("str"); req = req.setNext(new AttributeVO());
		req.setColumnName("PID");req.setName("__FID");req.setValue(""); req.setType("str"); req = req.setNext(new AttributeVO());
		req.setColumnName("LABEL");req.setName("__LABEL");req.setValue(""); req.setType("str"); req = req.setNext(new AttributeVO());
		req.setColumnName("DESCRIPTION");req.setName("__DESC");req.setValue(""); req.setType("str"); req = req.setNext(new AttributeVO());
		req.setColumnName("DATA_TYPE");req.setName("__DATATYPE");req.setValue(""); req.setType("str"); req = req.setNext(new AttributeVO());
		req.setColumnName("TEMPLATE");req.setName("__TEMPLATE");req.setValue(""); req.setType("str"); req = req.setNext(new AttributeVO());
		req.setColumnName("CREATEDBY");req.setName("__CREATEDBY");req.setValue(""); req.setType("str"); req = req.setNext(new AttributeVO());
		req.setColumnName("CREATEDDATE");req.setName("__CREATEDDATE");req.setValue("16-02-1984"); req.setType("date");
		
		RecordVO rp = new RecordVO(); preq.setCmdName("s");
		AttributeVO search = new AttributeVO(); preq.setFilter(search);
		search.setColumnName("ID"); search.setName("__ID"); 
		search.setValue("YDNRNQCOPTDJTQKFCECBBEORBWNWYUFOMUDEUTCDIMMAUVKOWI"); 
		search.setType("str");
		
		boolean bool = db.doExecute(preq,  rp);
		
		if(bool == true && rp!=null) {
			RecordVO rcopy = rp; int i = 1;
			while(rcopy != null) {
				ResponseVO rvo = rcopy.getResponse();
				if( rvo != null )
					System.out.println("RecordNo: " + (i++));
				
				//---column----
				ResponseVO col = rvo;
				while(col !=null) {
					System.out.println("  Name = " + col.getName());
					System.out.println("  Value = " + col.getVal());
					col = col.getNext();
				}
				
				rcopy = rcopy.getNext();
			} 
		}
		
		System.out.println("S Status = " + bool);
		System.out.println("S Response = " + (rp != null ? rp.toString() : "" ) );
		
		return rp;
		
	}
}
