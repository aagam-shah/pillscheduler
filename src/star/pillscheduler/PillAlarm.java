package star.pillscheduler;

public class PillAlarm {
	private String title;
	private String descr;
	private boolean justNotify;
	private String imLoc;
	private int pillsLeft;
	private int alterDay;
	private boolean isAlterDay;
	private int ringtone,dosage;
	private boolean allDays;
	private String daysTime;
	
	
	public PillAlarm(String t,String d,String timings,int totalPills,int dose){
		this.title=t;
		this.descr=d;
		this.pillsLeft=totalPills;
		this.dosage=dose;
		this.daysTime=timings;
			}
	
	public PillAlarm(String t, String d, String timings, String imguri,int totalPills,int dose) {
		// TODO Auto-generated constructor stub
		this.dosage=dose;
		this.title=t;
		this.descr=d;
		this.pillsLeft=totalPills;
		this.daysTime=timings;
		this.imLoc = imguri;
	}

	
	public String getImgUri(){
		return this.imLoc;
	}
	public String getTitle(){
		
		return this.title;
	}
	
	public int getDosage(){
		return this.dosage;
	}

	public String getDescr(){
		return this.descr;
	}
	
	public int getPills(){
		return this.pillsLeft;
		
	}
	
	public String getTimings(){
		return this.daysTime;
	}
	
}
