package it.ciroppina.idol.generic.tunnel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultList {

	private List<IdolCategoryResultObject> sortableList;
	private List<IdolCategoryResultObject> returnList;
	
	public ResultList() {
		this.sortableList = new ArrayList<IdolCategoryResultObject>();
	}
	
	protected void add(IdolCategoryResultObject o) {
		this.sortableList.add(o);
		Collections.sort(this.sortableList, new Comparator<IdolCategoryResultObject>() {
		    public int compare(IdolCategoryResultObject o1, IdolCategoryResultObject o2) {
		        Double d1 = Double.parseDouble(o1.getRilevanza());
		        Double d2 = Double.parseDouble(o2.getRilevanza());
		        
		    	if (d1 != null && d2 != null && d1 >= d2)
		        	return -1;
		        else
		        	return 1;
		    }
		});
		
		this.returnList = this.sortableList;
		//Collections.sort(this.returnList, Collections.reverseOrder());
	}
	
	@Override
	public String toString() {
		String result= "\t\n The following values should be desc-ordered:";
		if (returnList != null) {
			for (IdolCategoryResultObject o : returnList) {
				result += "\t\n " + o.getRilevanza() + "% - " + o.getTitolo()
					   + ": " + o.getId();
			}
		} else {
			result += "\t\n no results";
		}
		return result;
	}

	public List<IdolCategoryResultObject> getReturnList() {
		if (returnList == null || returnList.size() < 1)
			return new ArrayList<IdolCategoryResultObject>();
		return returnList;
	}

	public void setReturnList(List<IdolCategoryResultObject> returnList) {
		this.returnList = returnList;
	}

}
