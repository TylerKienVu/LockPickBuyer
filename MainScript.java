package scripts;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

@ScriptManifest(author = "Tylersobored", info = "LockPick Buying Script", name = "lockpick", version = 0, logo = "")

public class MainScript extends Script {
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	private TaskManager taskmanager;

	@Override
	public void onStart() {
		Collections.addAll(activities,new Buy(this),new Bank(this));
		this.taskmanager = new TaskManager(activities,this);
	}
	
	@Override
	public int onLoop() throws InterruptedException {
		taskmanager.getActive().run();
		return Script.random(500, 700);
	}
	
	@Override
	public void onExit() {
	}
	
	@Override
	public void onPaint(Graphics2D g) {
		
	}
}