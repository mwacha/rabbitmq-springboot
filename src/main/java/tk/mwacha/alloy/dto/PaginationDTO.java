package tk.mwacha.alloy.dto;

import java.io.Serializable;
import java.util.List;

public class PaginationDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6675901392914468143L;
	
	private long totalRecords;
	private List<? extends Serializable> records;

	public PaginationDTO(long totalRecords, List<? extends Serializable> records) {
		super();
		this.totalRecords = totalRecords;
		this.records = records;
	}
	
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<? extends Serializable> getRecords() {
		return records;
	}
	public void setRecords(List<? extends Serializable> records) {
		this.records = records;
	}
	
}
