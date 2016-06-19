package com.zhijian.core;

import java.util.ArrayList;
import java.util.List;

public final class CoreUtil {
	public static <T> List<T> DatePaging(List<T> list, boolean isPaging, int start, int limit){
		if(list != null && list.size() > 0 && isPaging){
			int end = start + limit;
			end = (end > list.size() ? list.size() : end);
			return new ArrayList<T>(list.subList(start, end));
		}else{
			return list;
		}
	}
	
}
