public class Queue{
    listNode  front=null;
	listNode  rear=null;//calling class listNode used by Queue
	
//Enqueue: Enqueues string to the queue
	public void enqueue(String arg) {
		listNode  node= new listNode ();
		node.data=arg;      
		node.next=null;
		if(rear!=null)     //If queue is not empty, new node gets to the end
		   rear.next=node;
		else
			front=node;   //otherwise, front points to new node
		rear=node;
	}
	
//Dequeue: Dequeues string from queue
	
	public String dequeue() {
		if(front!=null) {
		   if(front==rear) rear=null;
		   String data=  front.data;
		   front=front.next;
	       return data;
		}
		else
			return null;
		
	}
	
	public void flush() {
		front=null;
		rear=null;
	}
//Concatenation of all tokens in one string separated by a space.
	
	public String toString() {
		String result=""; //initializing string
		listNode cf=front;
		listNode cr=rear;
		
		if(cf==cr) return result;
		while(true) {
		 result=result+ " "+ cf.data.trim();
		 if(cf==cr)break;
		 cf=cf.next;
	}
	return result;
	}
}