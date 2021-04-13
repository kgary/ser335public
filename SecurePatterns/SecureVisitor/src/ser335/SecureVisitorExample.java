package ser335;

/**
 *
 *  File: SecureVisitorExample
 *
 *  This sample code shows a sketch of the Secure Visitor Design Pattern. For simplicity,
 *  the entire codebase is in one file. There are variants of this pattern, this is what
 *  this one does:
 *  Visitor side:
 *  - IVisitor defined the Visitor interface. It is passed secure and insecure data to output
 *  - for the purposes of this simple demo, data are strings. But one can generalize to anything
 *  - The IVisitor interface is only instantiated through anonymous inner classes, which makes it 
 *    so that the secure and regular classes are not declared with public contructors
 *  - The SecureVisitorFactory constructs secure and regular anonymous inner classes based on 
 *    whether the proper credential is presented (which is a simple string match here, again open-ended)
 *  
 *  Visitable side:
 *  - The IVisitable interface defines the accept method that allows the IVisitor to visit
 *  - A contrived data structure of a simple list of ANode types is constructable from abstract class ANode
 *  - Two concrete subclasses of ANode: MyNode and YourNode. They have no additional behaviors, they are
 *    just there to show that we can create as many subtypes as we want, the Visitor does not care
 *    
 *  The main program constructs a simple list of nodes based on the command-line argument, randomly creates
 *  MyNodes and YourNodes, and randomly determines if the node is secure. This in turn impacts the output.
 *  Finally, at the end, it produces one more line of output by creating an Visitable on-the-fly and visiting
 *  it, this time not a node but something else. This is to show that the IVisitor can in fact visit 
 *  anything that is willing to accept it (implements IVisitable).
 * 
 *  @author  Kevin A. Gary
 *  @date    4/17/21
 *  @version 1.0
 **/

import java.util.Random;

// The Visitor side
interface IVisitor {
    public String visit(String secureData, String insecureData);
}
final class SecureVisitorFactory {
    private SecureVisitorFactory() {} // ensure no instances can be created
    private static final String SECRET = "ser335";
    public static IVisitor getVisitor() {
	return new IVisitor() {
	    public String visit(String secureData, String insecureData) {
		return insecureData;
	    }
	};
    }
    public static IVisitor getVisitor(String credential) {
	if (SecureVisitorFactory.SECRET.equals(credential)) {
	    return new IVisitor() {
		public String visit(String secureData, String insecureData) {
		    return secureData + ", " + insecureData;
		}

	    };
	} else {
	    return new IVisitor() {
		public String visit(String secureData, String insecureData) {
		    return insecureData;
		}
	    };
	}
    }
}

// This "Visitable", or what can get Visited, side
interface IVisitable {
    public String accept(IVisitor visitor);
}

abstract class ANode implements IVisitable {
    private ANode __nextNode = null;
    private String pub = null;
    private String pvt = null;
    
    protected ANode(String pub, String pvt, ANode node) {
	this.pub = pub;
	this.pvt = pvt;
	this.__nextNode = node;
    }
    public String accept(IVisitor visitor) {
	return visitor.visit(this.pvt, this.pub);
    }
    public ANode getNextNode() {
	return __nextNode;
    }

}

final class MyNode extends ANode {
    public MyNode(ANode next) {
	super("MyNode public", "MyNode private", next);
    }
}
final class YourNode extends ANode {
    public YourNode(ANode next) {
	super("YourNode public", "YourNode private", next);
    }
}

public final class SecureVisitorExample {
    private SecureVisitorExample() {}
    
    public static void main(String[] args) {
	if (args.length != 1) {
	    System.out.println("USAGE: java ser335.SecureVisitorExample numNodes");
	    System.exit(0);
	}
	ANode rootNode = new MyNode(null);
	int numNodes = Integer.parseInt(args[0]);
	ANode prev = rootNode;
	Random randomNum = new Random(System.currentTimeMillis());

	for (int i = 1; i < numNodes; i++) {
	    // prepend, determine what kind of node randomly
	    rootNode = randomNum.nextInt(2) == 1 ? new MyNode(prev) : new YourNode(prev);
	    prev = rootNode;
	}
	// OK now run visit each node in the List.
	// Make every other visitor secure
	int i = 0;
	for (ANode next = rootNode; next != null; next = next.getNextNode()) {
	    String cred = (i++ % 2) > 0 ? "ser335" : "wrong credential!";
	    System.out.println("Next Node: " + next.accept(SecureVisitorFactory.getVisitor(cred)));
	}

	// OK to show we are not dependent on ANode, here is one more with an anonymous class
	IVisitable visitable = new IVisitable() {
	    public String accept(IVisitor visitor) {
		System.out.println("In last accept, visitor was " + visitor);
		return visitor.visit("anonymous secure", "anonymous insecure");
	    }
	};
	System.out.println("One last example: " +
			   visitable.accept(SecureVisitorFactory.getVisitor(randomNum.nextInt(2) ==1 ? "ser335" : "not 335")));
    }
}
