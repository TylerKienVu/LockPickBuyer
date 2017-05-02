package scripts;

import org.osbot.rs07.script.Script;

public class HopWorld implements Activity{
	private Script script;
	private int worldCount = 0;
	private int numOfWorlds;
	private int[] selectedWorldList;
	private int[][] worldList = new int[][]{
		{302,303,304,305,306,307,309,310,311,312,313,314,315,317,318,319,320,321,322,323,324,378},
		{327,328,329,330,331,332,333,334,336,338,339,340,341,342,343,344,346,347,348,350,386,377},
		{351,352,354,355,356,357,358,359,360,362,365,367,368,369,370,371,372,374,375,376},
		{302,303,304,305,306,307,309,310,311,312,313,314,315,317,318,319,320,321,322,323,324,378,327,328,329,330,331,332,333,334,336,338,339,340,341,342,343,344,346,347,348,350,386,377,351,352,354,355,356,357,358,359,360,362,365,367,368,369,370,374,375,376}
	};
	public String id = "HopWorld";
	public HopWorld(Script script){
		this.script = script;
		int parameters;
		if(script.getParameters() != null){
			parameters = Integer.parseInt(script.getParameters().split(",")[0]);
		}
		else{
			parameters = 3;
		}
		this.numOfWorlds = worldList[parameters].length;
		this.selectedWorldList = worldList[parameters];
		for(int i = 0;i < selectedWorldList.length;i++){
			if(selectedWorldList[i] == script.worlds.getCurrentWorld()){
				this.worldCount = i+1;
				if(this.worldCount == this.selectedWorldList.length){
					this.worldCount = 0;
				}
			}
		}
	}
	
	public String getID(){
		return this.id;
	}
	public void run() throws InterruptedException{
		Script.sleep(Script.random(2000,3000));
		script.worlds.hop(this.selectedWorldList[this.worldCount]);
		if(script.dialogues.isPendingOption()){
			script.dialogues.selectOption(2);
		}
		Script.sleep(Script.random(5000,7000));
		if(this.worldCount == this.numOfWorlds-1){
			this.worldCount = 0;
		}
		else{
			this.worldCount++;
		}
	}
	public boolean validate(){
		return true;
	}
}
