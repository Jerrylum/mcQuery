package mcQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Entity;

public class ${
	public final long _uuid = mcQuery.randomUUID();
	
	private Object[] _obj = new Object['?'];
	private ObjType type = null;
	
	/**
	 * init mcQuery
	 */
	public $(){
		//Do not thing
	}
	
	/*
	 * Select an object
	 * Get an implied object
	 */
	public $(Object o){//TODO
		if(o instanceof String){
			mcsol ans = new mcsol((String)o);
			
			if(ans.type == null){
				throw new NullPointerException("The type could not be null");
			}
			System.out.println(ans);
			this.type = ans.type;
			
			switch(ans.type){
			case entity:
				Entity E;
				
				break;
			case event:
				break;
			case inv:
				break;
			case location:
				break;
			case player:
				break;
			case item:
				break;
			case world:
				break;
			default:
				break;
			}
		}else if(o instanceof function){
			
		}else{
			
		}
		
		
		
	}

	/*
	 * Create an object and set its property
	 */
	public $(String type, String style){
		
	}
	
	/**
	 * Eject any passenger of matched elements.
	 * @return myself
	 */
	public $ eject(){
		if( ! (this.type == ObjType.entity || this.type == ObjType.player) ){
			return this;
		}
		
		for(Object obj : _obj){
			((Entity)obj).eject();
		}
		return this;
	}
	
	/**
	 * Reduce the set of matched elements to the one at the specified index.
	 * @param i the index
	 * @return return the element you select
	 */
	public $ eq(int i){
		return _obj.length <= i ? null : new $(_obj[i]);
	}
	
	/**
	 * Retrieve the Raw objects matched by the mcQuery object.
	 * @param i the index
	 * @return return the object you select
	 */
	public Object get(int i){
		return _obj.length <= i ? null : _obj[i];
	}
	
	public $ leave(){
		if( ! (this.type == ObjType.entity || this.type == ObjType.player) ){
			return this;
		}
		
		for(Object obj : _obj){
			((Entity)obj).leaveVehicle();
		}
		return this;
	}
	
	/**
	 * Returns the entity's(the first entity of matched elements.) current fire ticks.
	 * 20 tick = 1sec
	 * @return Returns the current fire ticks.
	 */
	public int onfire(){
		if( ! (this.type == ObjType.entity || this.type == ObjType.player) ){
			return 0;
		}
		
		if(_obj.length == 0){
			return 0;
		}
		int tick = ((Entity)_obj[0]).getFireTicks();
		return tick;
	}
	
	/**
	 * Sets the entity's(the first entity of matched elements.) current fire ticks.
	 * 20 tick = 1sec
	 * @param o The tick you have to set: true = 80, false = 0, Integer
	 * @return
	 */
	public $ onfire(Object o){
		if( ! (this.type == ObjType.entity || this.type == ObjType.player) ){
			return this;
		}
		
		if(_obj.length == 0){
			return this;
		}
		
		int tick = 0;
		if(o instanceof Boolean){
			boolean b = (Boolean)o;
			if(b){
				tick = 80;
			}
		}else{
			try{
				tick = Integer.parseInt((String)o);
			}catch(Exception ex){return this;}
		}
		
		for(Object obj : _obj){
			((Entity)obj).setFireTicks(tick);
		}
		return this;
	}
	
	/**
	 * Gets passenger of the first entity of matched elements.
	 * @return entity corresponding to the current passenger.
	 */
	public $ passeng(){
		if( ! (this.type == ObjType.entity || this.type == ObjType.player) ){
			return null;
		}
		
		if(_obj.length == 0){
			return null;
		}
		Entity E = ((Entity)_obj[0]).getPassenger();
//		if(_obj.length > 1){
//			System.err.println("mcQuery: Warning! mcQuery have more than 1 entity. Only return at the first of the list");
//		}
		return E == null ? null : new $(E);
	}
	
	/**
	 * Get the vehicle that this entity is inside.
	 * (the first entity of matched elements.)
	 * @return The current vehicle.
	 */
	public $ parent(){
		if( ! (this.type == ObjType.entity || this.type == ObjType.player) ){
			return null;
		}
		
		if(_obj.length == 0){
			return null;
		}
		
		Entity E = ((Entity)_obj[0]).getVehicle();
		return new $(E);
	}
	
	/**
	 * Set the passenger of the first entity of matched elements
	 * If Object is:
	 *  - Entity
	 *  - mcQuery
	 * @param o the object
	 * @return myself
	 */
	public $ passeng(Object o){
		if( ! (this.type == ObjType.entity || this.type == ObjType.player) ){
			return this;
		}
		
		if(_obj.length == 0){
			return this;
		}
		
		Entity E = (Entity)_obj[0];
		if(o == null){
			E.eject();
		}else if(o instanceof Entity){
			E.setPassenger((Entity)o);
		}else if(o instanceof $){
			Entity E2 = (Entity) (($)o).get(0);
			if(E2 != null){
				E.setPassenger(E2);
			}
		}
		
//		if(_obj.length > 1){
//			System.err.println("mcQuery: Warning! mcQuery have more than 1 entity. Passenger only set at the first of the list");
//		}
		return this;
	}
	
	public $ remove(){
		if( ! (this.type == ObjType.entity || this.type == ObjType.player) ){
			return null;
		}
		
		if(_obj.length == 0){
			return null;
		}
		
		for(Object obj : _obj){
			((Entity)obj).remove();
		}
		return this;
	}
	
	/*
	 * Set the target's property if it is:
	 * Entity, Player, Block, Location, World, Server, Inventory, Event
	 */
	public $ set(String set){
		//TODO
		return this;
	}
	
	/**
	 * Return the number of elements in the mcQuery object.
	 * @return return the size the elements matched by the mcQuery.
	 */
	public int size(){
		return _obj.length;
	}
	
	
	private static class mcsol{//MineCraft Select Object Language
		public ObjType type = null;
		public String name = null;
		public HashMap<String,Object> map = new HashMap<String,Object>();
		public mcsol passenger = null;
		
		
		public mcsol(String str){	
			char[] text = str.toCharArray();//Change the string to char array 
			for(int index = 0;index < text.length; index++){
				char c = text[index];
				if(c == ' '){//if space, skip it
					continue;
				}
				
				if(c == '#'){//if #, get name
					if(type == null){//if no type, set default type to player
						type = ObjType.player;
					}
					name = "";
					
					index ++;//skip # and start the loop
					for(;index < text.length; index++){
						c = text[index];
						
						if(c == ' '){//if space, end of name
							break;
						}else{
							name += c;
						}
						
					}
					continue;
				}
				
				if(c == '.'){//if ., get type
					String typename = "";
					
					index ++;//skip . and start the loop
					for(;index < text.length; index++){
						c = text[index];
						
						if(c == ' '){//if space, end of type
							break;
						}else{
							typename += c;
						}
						
					}
					try{type = ObjType.valueOf(typename.toLowerCase());}
					catch(Exception ex){throw new NullPointerException("Unknow type name");}
					continue;
				}
				
				if(c == '>'){//if >, get passenger
					passenger = new mcsol(str.substring(index+1));//sub string after the >
					break;
				}
				
				//get map element
				String tag = "";//tag
				String obj = "";//obj
				List<String> objlist = new ArrayList<String>();//obj list, if the array have two object
				boolean tagpass = false;//is the tag content finish
				boolean preend = false;//is the string closed by '
				boolean inmark = false;//is the string in '
				boolean missmark = false;//is the char before \
				for(;index < text.length; index++){
					c = text[index];
					
					if(c == '\''){
						if(!missmark){//if not before \
							if(inmark){//if in the '
								inmark = false;//close the '
								preend = true;//end of the string
								continue;
							}else{
								if( (!tagpass && tag.equals("")) || (tagpass && obj.equals("")) ){//if the first of the string
									inmark = true;//start '
									continue;
								}else{
									throw new NullPointerException("Wrong format array:\nApostrophe must write at the first or the end of the String");
								}
							}
						}
					}
					
					if(!missmark){//if not before \
						if(c == '\\'){
							missmark = true;//start \ parter
							continue;
						}		
					}else{
						missmark = false;//stop \ parter
					}

					if(c == ':'){
						if(!inmark){//if not in the '
							if(!tagpass){//if the tag content not finish
								//change to object mode
								tagpass = true;
								missmark = false;
								inmark = false;
								preend = false;
								continue;
							}else{
								throw new NullPointerException("Wrong format array: 2 Colon in one array");
							}
						}
					}
					
					if(c == ','){
						if(!inmark){//if not in the '
							if(tagpass){//if the tag content finish
								//change to object list mode and add an object
								missmark = false;
								inmark = false;
								preend = false;
								objlist.add(obj);
								obj = "";
								continue;
							}else{
								throw new NullPointerException("Wrong format array: 2 tag in one array");
							}
						}
					}
					
					if(c == ' '){
						if(!inmark){//if not in the '
							if(!tagpass){
								preend = true;
								continue;
							}else{
								if(!obj.equals("")){
									preend = true;
								}
								continue;
							}
							
						}
						
					}
					
					if(c == ';'){
						if(!inmark){//if not in the '
							if(!tagpass){
								throw new NullPointerException("Wrong format array: Unenclosed array");
							}
							break;//finish
						}
						
					}
					
					if(preend){
						throw new NullPointerException("Wrong format array: Add content to a ended string");
					}
					
					if(!tagpass){
						tag += c;
					}else{
						obj += c;
					}
				}
				
				if(tag.equals("")){
					throw new NullPointerException("Wrong format array: Empty content array");
				}
				
				if(inmark){
					throw new NullPointerException("Wrong format array: Unenclosed string");
				}
				
				if(!objlist.isEmpty()){//if objecy mode
					objlist.add(obj);//add the last object
				}
				map.put(tag, objlist.isEmpty() ? obj : objlist);				
			}

		}

		public String toString(){
			return "<type: |" + type + "| name: |" + name + "| map: |" + map + "| passenger: |" + (passenger == null ? "" : passenger.toString()) + ">";
		}
		
	}
	
	public static enum ObjType{
		entity, player, location, world, item, inv, event, json;
	}
}
