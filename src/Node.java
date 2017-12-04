
public class Node<S,V> {
	Node<S, V> next;
	String symbol;
	String value;
	public Node(){
		next =null;
	}
	public Node(String symbol, String value){
		this.symbol=symbol;
		this.value=value;
		next =null;
	}
	
	public String getSymbol(){
		return symbol;
	}
	public String getValue(){
		return value;
	}
}
