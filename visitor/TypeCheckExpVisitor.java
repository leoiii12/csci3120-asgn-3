package visitor;
import syntaxtree.*;

public class TypeCheckExpVisitor extends TypeDepthFirstVisitor {
   

  // Exp e1,e2;
  public Type visit(And n) {
    if (! (n.e1.accept(this) instanceof BooleanType) ) {
       System.out.println("Left side of And must be of type integer");
       System.exit(-1);
    }
    if (! (n.e2.accept(this) instanceof BooleanType) ) {
       System.out.println("Right side of And must be of type integer");
       System.exit(-1);
    }
    return new BooleanType();
  }

  // Exp e1,e2;
  public Type visit(LessThan n) {
    if (! (n.e1.accept(this) instanceof IntegerType) ) {
       System.out.println("Left side of LessThan must be of type integer");
       System.exit(-1);
    }
    if (! (n.e2.accept(this) instanceof IntegerType) ) {
       System.out.println("Right side of LessThan must be of type integer");
       System.exit(-1);
    }
    return new BooleanType();
  }

  // Exp e1,e2;
  public Type visit(Plus n) {
    if (! (n.e1.accept(this) instanceof IntegerType) ) {
       System.out.println("Left side of LessThan must be of type integer");
       System.exit(-1);
    }
    if (! (n.e2.accept(this) instanceof IntegerType) ) {
       System.out.println("Right side of LessThan must be of type integer");
       System.exit(-1);
    }
    return new IntegerType();
  }

  // Exp e1,e2;
  public Type visit(Minus n) {
    if (! (n.e1.accept(this) instanceof IntegerType) ) {
       System.out.println("Left side of LessThan must be of type integer");
       System.exit(-1);
    }
    if (! (n.e2.accept(this) instanceof IntegerType) ) {
       System.out.println("Right side of LessThan must be of type integer");
       System.exit(-1);
    }
    return new IntegerType();
  }

  // Exp e1,e2;
  public Type visit(Times n) {
    if (! (n.e1.accept(this) instanceof IntegerType) ) {
       System.out.println("Left side of LessThan must be of type integer");
       System.exit(-1);
    }
    if (! (n.e2.accept(this) instanceof IntegerType) ) {
       System.out.println("Right side of LessThan must be of type integer");
       System.exit(-1);
    }
    return new IntegerType();
  }

  // Exp e1,e2;
  public Type visit(ArrayLookup n) {
    if (! (n.e1.accept(this) instanceof IntArrayType) ) {
       System.out.println("Left side of LessThan must be of type integer");
       System.exit(-1);
    }
    if (! (n.e2.accept(this) instanceof IntegerType) ) {
       System.out.println("Right side of LessThan must be of type integer");
       System.exit(-1);
    }
    return new IntegerType();
  }

  // Exp e;
  public Type visit(ArrayLength n) {
    if (! (n.e.accept(this) instanceof IntArrayType) ) {
       System.out.println("Left side of LessThan must be of type integer");
       System.exit(-1);
    }
    return new IntegerType();
  }

  // Exp e;
  // Identifier i;
  // ExpList el;
  public Type visit(Call n) {

    if (! (n.e.accept(this) instanceof IdentifierType)){
	System.out.println("method "+ n.i.toString() 
			   + "called  on something that is not a"+
			   " class or Object.");
	System.exit(-1);
    } 

    String mname = n.i.toString();    
    String cname = ((IdentifierType) n.e.accept(this)).s;

    Method calledMethod = TypeCheckVisitor.symbolTable.getMethod(mname,cname);
    
    for ( int i = 0; i < n.el.size(); i++ ) {     	
	Type t1 =null;  
	Type t2 =null;  

	if (calledMethod.getParamAt(i)!=null)
	    t1 = calledMethod.getParamAt(i).getType();
	t2 = n.el.elementAt(i).accept(this);
	if (!TypeCheckVisitor.symbolTable.compareTypes(t1,t2)){
	    System.out.println("Type Error in arguments passed to " +
			       cname+"." +mname);
	    System.exit(-1);  
	}	    
    }

    return TypeCheckVisitor.symbolTable.getMethodType(mname,cname);
  }

  // int i;
  public Type visit(IntegerLiteral n) {
    return new IntegerType();
  }

  public Type visit(True n) {
    return new BooleanType();
  }

  public Type visit(False n) {
    return new BooleanType();
  }

  // String s;
  public Type visit(IdentifierExp n) {      
    return TypeCheckVisitor.symbolTable.getVarType(TypeCheckVisitor.currMethod,
		      TypeCheckVisitor.currClass,n.s);
  }

  public Type visit(This n) {
      return TypeCheckVisitor.currClass.getType();
  }

  // Exp e;
  public Type visit(NewArray n) {
    
    if (! (n.e.accept(this) instanceof IntegerType) ) {
       System.out.println("Left side of LessThan must be of type integer");
       System.exit(-1);
    }
    return new IntArrayType();
  }

  // Identifier i;
  public Type visit(NewObject n) {
    return new IdentifierType(n.i.s);
  }

  // Exp e;
  public Type visit(Not n) {
    if (! (n.e.accept(this) instanceof BooleanType) ) {
       System.out.println("Left side of LessThan must be of type integer");
       System.exit(-1);
    }
    return new BooleanType();
  }

}
 //TypeCheckVisitor.






