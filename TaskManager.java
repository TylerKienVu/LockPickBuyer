package scripts;

import java.util.ArrayList;
import org.osbot.rs07.script.Script;

public class TaskManager {
	private Activity activeTask;
	private ArrayList<Activity> taskList;
	private Script script;
	private HopWorld hw;

	public TaskManager(ArrayList<Activity> taskList, Script script)	{
		this.taskList = taskList;
		this.script = script;
		this.hw = new HopWorld(script);
	}
	
	public Activity getActive() throws InterruptedException	{
		if(this.activeTask != null && this.activeTask.getID() == "HopWorld"){
			this.activeTask = taskList.get(0); //switch back to buy
			script.log("Chaning task to: Buy");
		}
		if(this.activeTask != null && this.activeTask.getID() == "Bank"){
			this.activeTask = hw;
			script.log("Changing Task to: HopWorld");
		}
		if (this.activeTask == null || !this.activeTask.validate())	{
			for (Activity task: taskList)	{
				if (task != null && task.validate())	{
					script.log("Changing Task to: " + task.getID());
					this.activeTask = task;
					break;
				}
			}
		}		
		return this.activeTask;
	}
}