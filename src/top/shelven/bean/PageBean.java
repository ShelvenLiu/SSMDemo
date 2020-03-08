package top.shelven.bean;

import java.util.List;

public class PageBean<T> {
	//当前页
	private int curPage;
	//每页显示的记录数
	private int pageSize;
	//总页数
	private int totalPage;
	//总记录数
	private int totalCount;
	//每页显示的数据
	private List<T> lists;
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	
	
}
