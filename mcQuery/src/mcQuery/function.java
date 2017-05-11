package mcQuery;

public abstract class function extends Thread{
	public final long _uuid = mcQuery.randomUUID();
	
	abstract void Do();
	
	public void run(){
		Do();
	}
}