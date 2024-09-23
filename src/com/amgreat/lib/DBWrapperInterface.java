package com.amgreat.lib;

import com.amgreat.vo.AttributeVO;
import com.amgreat.vo.RecordVO;

public interface DBWrapperInterface {
	public boolean doExecute(AttributeVO vo, RecordVO out) ;
}
