package mcQuery;

import java.util.Random;

import org.bukkit.plugin.java.JavaPlugin;

public class mcQuery extends JavaPlugin{
	private static Random _random = new Random();
	private static JavaPlugin pl;
	
	public void onEnable(){
		pl = this;
		new $();
	}
	
	public void onDisable(){
		
	}
	
	public static JavaPlugin getPlugin(){
		return pl;
	}
	
	public static long randomUUID(){
		return _random.nextLong();
	}
	
	public static void main(String[] args){ 
		$(args[0]);
	}
	
	/*
	 * Select an object or
	 * Get an implied object
	 */
	public static $ $(Object o){
		return new $(o);
	}

	/*
	 * Create an object and set its property
	 */
	public static $ $(String type, String style){
		return new $(type, style);
	}
	
	/*
	 * Set the target's property
	 */
	public static void $($ mcq, String set){
		mcq.set(set);
	}
	
}
