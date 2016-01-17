package ns5d;

import java.util.Arrays;
import java.util.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Line {
	public enum EntryExit  {entry,	exit}
	
	private String[] lineArr;
	private Date date;
	private EntryExit isEntryExit;
	private String methodName;
	private String methodID;
	
	Line(String[] lineArr) {
		this.lineArr = lineArr;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss,SSS");
		try {
			this.date = dateFormat.parse(lineArr[0]);
			dateFormat.format(this.date);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		 
		this.isEntryExit = EntryExit.valueOf(lineArr[3]);
		
		lineArr[5] = lineArr[5].replace("(", "");	// (getData:97991)
		lineArr[5] = lineArr[5].replace(")", "");
		
		String method_id[] = lineArr[5].split(":"); // getData:97991
		this.methodName = method_id[0];				// getData
		this.methodID = method_id[1]; 				// 97991
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public EntryExit getIsEntryExit() {
		return this.isEntryExit;
	}
	
	public String getMethodName() {
		return this.methodName.toString();
	}
	
	public String getMethodID() {
		return this.methodID.toString();
	}
	
	public long getDateDiff(Date date) {
		return this.date.getTime() - date.getTime();
	}
	
	public String toString() {
		return Arrays.toString(this.lineArr);
	}
}
