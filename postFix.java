import java.util.StringTokenizer;
public class postFix {
    static Character[] operators= new Character[] {'^', '%', 'x', '*', '/', '+', '-'};
	static Character[] oPrecedence= new Character[] {4,3,3,3,3,2,2};
	static Character[] poperators =new Character[] {'^','%','x','*','/','+','-','(',')'};
	
	public Queue parse(String arg) {
		Queue Output= new Queue();
		StringTokenizer st= new StringTokenizer(arg, "^%x*/+-()", true);
		while(st.hasMoreTokens()) {
			String token= st.nextToken();
			if(!token.equals(" ")) {
				token=token.replace('!', '-').trim();
				Output.enqueue(token.replaceAll("\\s", " "));
			}
		}
		return Output;
	}
	

//In2Post method using Shunting Yard Algorithm	
   public Queue In2Post(Queue Qin) {
	   String s,o;
	   
	   Stack operatorStack= new Stack();
	   Queue Output= new Queue();
	   
	    //Collecting tokens
	  while((s= Qin.dequeue())!= null) {	 
		if(isOperator(s)) {
			while((o=operatorStack.pop())!=null) {
				if(Precedence(o)>=Precedence(s)) {
					Output.enqueue(o);
				} else {
					operatorStack.push(o);
					break;
				}
			}
			operatorStack.push(s);
		}
		else if(s.charAt(0)=='(') {
			operatorStack.push(s);
		}
		else if(s.charAt(0)==')') {
			while((o=operatorStack.pop())!=null) {
				if(o.charAt(0)!='(') {
					Output.enqueue(o);
				} else {
					break;
				}
			}
		}
		else if(s.charAt(0)!=')') {
			Output.enqueue(s);
		}
		
	}
	  while((o=operatorStack.pop())!=null) {
		  Output.enqueue(o);
	  }
	  return Output;
			
   }
   
   public static boolean isOperator(String s) {
	   if(s.length()==1) {
		   for(int i=0; i<operators.length; i++) {
			   if(s.charAt(0)==operators[i]) return true;
		   }
	   }
	   return false;
   }
   
   private static int Precedence(String s) {
	   for(int i=0; i<operators.length; i++) {
		   if(s.charAt(0)==operators[i]) return oPrecedence[i];
	   }
	   return 0;
   }


   /*PostEval method evaluates the postfix expression.  */
public double PostEval(Queue PostFix) {
	
	String token;
	Stack stack= new Stack();
	Double result=null;
	
	
	while((token=PostFix.dequeue())!=null) {
		if(isOperator(token)) {
			
			String OP_A = stack.pop();
			String OP_B = stack.pop();
			Double A = Double.parseDouble(OP_A);
			Double B = Double.parseDouble(OP_B);
			
			
	/** Switches various arithmetic operators */
			switch(token) {
			case "+":
				result=B+A;
				
				break;
				
			case "-":
				result=B-A;
				
				break;
				
			case "*":
				result=B*A;
				
				break;
				
			case "/":
				result=B/A;
				
				break;
				
			case "x":
				result=B*A;
				
				break;
				
			case "%":
				result=B%A;
				
				break;
				
			case "^":
				result=Math.pow(B, A);
				
				break;
			
			}
			stack.push(Double.toString(result));
			
		}
		else {
			stack.push(token);
			
		}
	}
	return Double.parseDouble(stack.pop());
	
	
}
}
