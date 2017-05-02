package scripts;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

public class Bank implements Activity {
	private Script script;
	private NPC bank;
	public String id = "Bank";
	
	public Bank(Script script) {
		this.script = script;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void bankDeposit() throws InterruptedException {
		if(Script.random(1,5) > 4) {
			script.log("Taking random break...");
			Script.sleep(Script.random(4000,7000));
		}
		this.script.getBank().depositAllExcept("Coins");
		Script.sleep(Script.random(1000,3000));
		this.script.getBank().close();
	}
	
	public void run() throws InterruptedException {
		this.bank = this.script.npcs.closest("Emerald Benedict");
		if(!this.script.getBank().isOpen() && bank != null){
			while(!this.script.getBank().isOpen()){
				bank.interact("Bank");
				Script.sleep(Script.random(5000,7000));
			}
			this.bankDeposit();
		}
		else if(this.script.getBank().isOpen() && bank != null){
			this.bankDeposit();
		}
		Script.sleep(Script.random(2000,3000));
	}
	
	public boolean validate() {
		return script.inventory.getEmptySlotCount() != 27;
	}
}
