package com.amgreat.lib;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amgreat.vo.AttributeVO;
import com.amgreat.vo.RecordVO;
import com.amgreat.vo.RequestVO;
import com.amgreat.vo.ResponseVO;
import com.amgreat.vo.VO;

@Component
public class DBWrapper implements DBWrapperInterface{
	
	Logger logger = LoggerFactory.getLogger(DBWrapper.class);
	
	@Autowired
	private DBConnect db;
	
	public boolean doExecute(AttributeVO vo, RecordVO out) {
		boolean bool = false; Attribute attr = new Attribute(); 
		
		try {
			switch( vo.getCmdName() ){
			case "i":
				db.executeUpdate( attr.wrapVOI(vo, new RequestVO()),db.getConn() );
				bool = true;
				break;
			case "u":
				db.executeUpdate(attr.wrapVOU(vo, new RequestVO()),db.getConn() );
				bool = true;
				break;
			case "d":
				db.executeUpdate(attr.wrapVOD(vo, new RequestVO()),db.getConn() );
				bool = true;
				break;
			case "s":
				RequestVO req = new RequestVO(); 
				getResponse(vo
						, db.executeQuery( attr.wrapVOS(vo, req), db.getConn() )
						, out);
				bool = true;
				break;
			}
			return bool;
		} catch(Exception e) {
			logger.error("[DBWrapper.doExecute]:" + "vo.getCmdName() = [" + vo.getCmdName() + "] => " + e.getMessage());
			return false;
		}
	}
	
	private RecordVO getResponse( AttributeVO req, ResultSet rs, RecordVO r ) {
		try {
			if( req != null && rs != null ) {
				
				RecordVO rec = r; boolean bool = false; int recNo = 0;
				
				while( rs.next() ) {
					
					ResponseVO out = new ResponseVO();  
					
					this.getRecord( rs, req, out); recNo++;
					
					if( bool ) {
						rec.setNext( new RecordVO() ); 
						rec = rec.getNext();
					}
					rec.setResponse( out ); bool = true;
				}
				r.setRecNo( recNo );
			}
		} catch(Exception e) {
			logger.error("[DBWrapper.getResponse]:" + e.getMessage());
		}
		return r;
	}
	
	private ResponseVO getRecord(ResultSet rs, AttributeVO rvo, ResponseVO out) {
		String str = ""; ResponseVO o = null;
		try {			
			if( rvo != null ) {
				
				int idx = 0; AttributeVO rv = rvo; o = out;
				
				while ( rv != null ) 
				{
					
					String type = rv.getType();
					String name = rv.getName();
					
					idx++;
					
					if( type != null ) {
						
						// System.out.println("type: " + type  +", columnname: " + name);
						
						switch ( type ) 
						{
							case "str": 
								o.setVal( rs.getString(idx) ); o.setName( name ); break;
							case "text": 
								o.setVal( rs.getString(idx) ); o.setName( name ); break;
							case "smallint":
								o.setVal( Integer.toString(rs.getInt(idx) ) ); o.setName(name); break;
							case "bigint":
								o.setVal( Integer.toString(rs.getInt(idx) ) ); o.setName(name); break;
							case "int":
								o.setVal( Integer.toString(rs.getInt(idx) ) ); o.setName(name); break;	
							case "date": 
								{
									o.setName(name);
									if( rs.getDate(idx) != null ) o.setVal( rs.getDate(idx).toString() ) ;
									else o.setVal("");
									break;
								}
							case "double":
								o.setVal( Double.toString( rs.getDouble(idx) ) ); break;
							case "decimal":
							{
								String bd = ((rs.getBigDecimal(idx) == null ? "0.00": rs.getBigDecimal(idx).toString() ) ); 
								o.setVal(bd); break;
							}
							case "float":
								o.setVal( Float.toString( rs.getFloat(idx) ) ); break;
							default:{
								o.setVal( o.getVal() != null ? o.getVal() : "" );
							}
						}
					}
					
					if( rv.getNext() != null ) {
						rv = rv.getNext(); o.setNext(new ResponseVO()); o = o.getNext();
					} else rv = null;
				}
			} 
			return o;
		} catch(Exception e){
			logger.error("[DBWrapper.getRecord]:" + e.getMessage());
		}
		return o;
	}
}