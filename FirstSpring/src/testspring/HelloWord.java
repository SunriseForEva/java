package testspring;

public class HelloWord {
	private String message;
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setMessage(String message){
		this.message  = message;
	}
	
	public void getMessage(){
		System.out.println("Your Message : " + message);
	}
}
