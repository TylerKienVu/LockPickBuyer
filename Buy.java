package scripts;

import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

public class Buy implements Activity{
	private Script script;
	public String id = "Buy";
	
	public Buy(Script script){
		this.script = script;
	}
	public String getID(){
		return this.id;
	}
	public void run() throws InterruptedException{
		Item[] items = script.inventory.getItems();
		if(items[0].getAmount() < 5000){
			script.log("Money below 5k! Stopping script...");
			script.stop();
		}
		Script.sleep(Script.random(2000,7000));
		NPC store = script.npcs.closest("Martin Thwait");
		if(store != null){
			while(!script.store.isOpen()){
				store.interact("Trade");
				Script.sleep(Script.random(4000,5000));
			}
			int lpAmount = script.store.getAmount("Lockpick");
			if(lpAmount > 20){
				script.store.buy("Lockpick", 20);
				script.store.buy("Lockpick", 5);
			}
			else if(lpAmount > 10){
				script.store.buy("Lockpick", 20);
			}
			else{
				script.store.buy("Lockpick", 10);
			}
			Script.sleep(Script.random(1000,2000));
			if(script.store.getAmount("Iron Knife") > 0){
				script.store.buy("Iron Knife", 10);
			}
			Script.sleep(Script.random(1000,2000));
			if(script.store.getAmount("Steel Knife") > 0){
				script.store.buy("Steel Knife", 5);
			}
			Script.sleep(Script.random(1000,2000));
			script.store.close();
		}
		
	}
	public boolean validate(){
		return script.inventory.isEmptyExcept("Coins");
	}
}
