package top.shelven.bean;

import java.util.List;

public class PageBean<T> {
	//��ǰҳ
	private int curPage;
	//ÿҳ��ʾ�ļ�¼��
	private int pageSize;
	//��ҳ��
	private int totalPage;
	//�ܼ�¼��
	private int totalCount;
	//ÿҳ��ʾ������
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
