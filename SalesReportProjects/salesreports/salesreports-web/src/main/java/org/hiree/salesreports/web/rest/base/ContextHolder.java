package org.hiree.salesreports.web.rest.base;

import org.hiree.salesreports.web.filters.CORSFilter;
//@Component("contextHolder")
public class ContextHolder {
private CORSFilter cORSFilter;

public CORSFilter getcORSFilter() {
	return cORSFilter;
}

public void setcORSFilter(CORSFilter cORSFilter) {
	this.cORSFilter = cORSFilter;
}


}
