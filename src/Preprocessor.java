
public class Preprocessor<S,V> {
	FileHandler filehandler;
    Node [] lookupTable;
    int size;
    int capacity = 4;
	public Preprocessor(int size, int capacity){
		this.size = size;
		this.capacity = capacity;
		lookupTable = new Node[this.size];
		for ( int i =0;i < lookupTable.length; i++)
			lookupTable[i] = new Node<S,V>();
			
	}
	
	public Preprocessor(FileHandler f) {
		filehandler = f;
		f.openFile("#");
	}
	
	public String readNextSymbol(){
		StringBuilder result = new StringBuilder();
		while (filehandler.hasMore())
		result.append(filehandler.nextLine());
		return result.toString();	
	}

	int undef (String s){
		int hashval = 0;
		for ( int i = 0; i <s.length(); i++)
			hashval = hashval *31 + s.charAt(i);
		return hashval % size;
}
	
	public void install(String symbol, String value){
		int index = undef(symbol);
		Node ArrayValue = lookupTable[index];
		Node newElement =new Node<S, V>(symbol, value);
		newElement.next = ArrayValue.next;
		ArrayValue.next=newElement;	
	}
	
	public String lookup(String symbol){
		V value = null;
		int index = undef(symbol);
		Node ArrayValue = lookupTable[index];
		while (ArrayValue.next != null){
			if(ArrayValue.getSymbol() == symbol){
				value = (V) ArrayValue.getValue();
				break;	
			}
			ArrayValue = ArrayValue.next;
		}
		return (String) value;
	}
}