package star.pillscheduler;

public class PillAlarm {
	private String title;
	private String descr;
	private boolean justNotify;
	private String imLoc;
	private int pillsLeft;
	private int alterDay;
	private boolean isAlterDay;
	private int ringtone;
	private boolean allDays;
	private String[] daysTime;
	
	public PillAlarm(String t,String d,String[] dayst){
		this.title=t;
		this.descr=d;
		this.daysTime=dayst;
		
	}
	
	public PillAlarm(String t,String d){
		this.title=t;
		this.descr=d;
			}

	public String getTitle(){
		
		return this.title;
	}
	public String getDescr(){
		return this.descr;
	}
	
	public String[] getDays(){
		return this.daysTime;
	}
	
}
