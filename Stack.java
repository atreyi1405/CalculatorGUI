public class Stack {
    listNode  top=null;
	
	
	void push(String arg) {       //push method
		listNode  node= new listNode();  //creates new entry
		node.data=arg;                  //loads data
		if(top!=null){
			node.next=top;//top now points to this new entry
		} else {
			node.next=null;
			
		}
		top=node;
	}
	 
	String pop() {               //pop method
		if(top==null)return null;       //indicates if top is empty then return null
		String data=top.data;           //else, return to top
		top=top.next;                   //now the entry just after the top becomes the new top
		return data;
	}
	
}
