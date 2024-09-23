package com.amgreat.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amgreat.vo.AttributeVO;
import com.amgreat.vo.RequestVO;

public class Attribute {
	
	Logger logger = LoggerFactory.getLogger(DBConnect.class);
	
	public AttributeVO search(AttributeVO vo, String val) {
		try {
			
			if( vo != null && val.equalsIgnoreCase(vo.getName()) ) {
				return vo;
			} else if ( vo.getNext() != null ) { 
				return this.search( vo.getNext(), val );
			} else return null;
		} catch (Exception e) {
			logger.error("[Attribute.search] " + e.getMessage());
			return null;
		}
	}
	
	public RequestVO wrapVOI(AttributeVO vo, RequestVO rqvo) {
		
		String __sql = "INSERT INTO "+ vo.getTabelName()+"( " + wrapParamsICols(vo, "") + ") VALUES(" + wrapParamsI(vo, "") + ")";
		
		rqvo.setCmdString(__sql);
		
		this.wrapVO( vo, rqvo ); 
		
		return rqvo;
	}
	
	public RequestVO wrapVOU(AttributeVO vo, RequestVO rqvo) {
		
		String __sql = "UDPATE "+vo.getTabelName()+" SET "+ wrapParamsU(vo, "");

		this.wrapVO( vo, rqvo );
		
		if( vo.getFilter() != null ) {
			
			__sql +=" WHERE " + wrapWhere( vo.getFilter(), rqvo, "" );

			rqvo.setCmdString(__sql);
			
		}
		System.out.println("genSQL: " + __sql );
		return rqvo; 
	}
	
	
	public RequestVO wrapVOS(AttributeVO vo, RequestVO rqvo) {
		
		String __sql = "SELECT " + wrapParamsS(vo, "") + " FROM " + vo.getTabelName() + wrapJoin( vo );
		
		rqvo.setCmdString(__sql);
		
		this.wrapVO( vo, rqvo );
		this.wrapVO( vo.getFilter(), rqvo.setFilter(new RequestVO()) );
		
		if( vo.getFilter() != null ) {
			
			__sql += " WHERE " + wrapWhere( vo.getFilter(), rqvo.addNext(), "" );
			
			rqvo.setCmdString(__sql);
		}
		
		return rqvo;
		
	}
	
	private String wrapJoin( AttributeVO vo ) {
		String __sql = "";
			if( vo!=null ) {
				if( vo.getIjoin() != null ) {
					__sql = " inner join " + wrapJoinL2( vo.getIjoin() );
				} else if( vo.getRjoin() != null ) {
					__sql = " right join " + wrapJoinL2( vo.getRjoin() );
				} else if( vo.getLjoin() != null ) {
					__sql = " left join " + wrapJoinL2( vo.getLjoin() );
				}
			}
		return __sql;
	}
	
	private String wrapJoinL2( AttributeVO join ) {
		String __sql = "";
			if( join != null ) {
				__sql = join.getCmdName();
			}
		return __sql;
	}
	
	public String wrapWhere(AttributeVO vo, RequestVO rqvo, String p) {
		if( vo != null && vo.getColumnName() != null) {
			
			rqvo = new RequestVO(); rqvo.setName(vo.getName()); rqvo.setVal(vo.getValue()); rqvo.setType( vo.getType()); rqvo.setNext(new RequestVO());
			
			p += "," + vo.getColumnName() + "=?"; 
			
			return this.wrapWhere(vo.getNext(), rqvo.getNext(), p);
			
		}
		return (p.length() > 1 ? p.substring(1) : "");
	}

	public RequestVO wrapVOD(AttributeVO vo, RequestVO rqvo) {
		
		String __sql = "DELETE FROM "+vo.getTabelName()+" WHERE "+ wrapParamsD(vo, "");
		
		rqvo.setCmdString(__sql);
		
		this.wrapVO( vo, rqvo ); 
		
		return rqvo; 
	}
	
	private String wrapParamsS(AttributeVO vo, String p) {
		if( vo != null && vo.getColumnName() != null ) {
			p+="," + vo.getColumnName(); return wrapParamsS( vo.getNext(), p );
		}
		return (p.length() > 1 ? p.substring(1) : "");
	}
	
	private String wrapParamsD(AttributeVO vo, String p) {
		if( vo != null && vo.getColumnName() != null ) {
			p+="AND " + vo.getColumnName() + "=? "; return wrapParamsD(vo.getNext(), p);
		}
		return (p.length() > 1 ? p.substring(4) : "");
	}
	
	private String wrapParamsICols(AttributeVO vo, String p) {
		if( vo != null && vo.getColumnName() != null ) {
			p+="," + vo.getColumnName(); return wrapParamsICols(vo.getNext(), p);
		}
		return (p.length() > 1 ? p.substring(1) : "");
	}
	
	private String wrapParamsI(AttributeVO vo, String p) {
		if( vo != null && vo.getName() != null ) {
			p+=",?";  return wrapParamsI(vo.getNext(), p);
		}
		return (p.length() > 1 ? p.substring(1) : "");
	}
	
	private String wrapParamsU(AttributeVO vo, String p) {
		if( vo != null && vo.getColumnName() != null ) {
			p+="," + vo.getColumnName() + "=?";  return wrapParamsU(vo.getNext(), p);
		}
		return (p.length() > 1 ? p.substring(1) : "");
	}
	
	public void wrapVO(AttributeVO vo, RequestVO rqvo) {
		if( vo != null && vo.getColumnName() != null) {
			rqvo.setNext(new RequestVO()); rqvo.setName(vo.getColumnName()); rqvo.setVal(vo.getValue()); rqvo.setType( vo.getType()); 
			this.wrapVO(vo.getNext(), rqvo.getNext());
		}
	}
	
}