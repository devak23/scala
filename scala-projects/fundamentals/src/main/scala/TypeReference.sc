def printAny(x: Any) = println(x)

def printAnyVal(y: AnyVal) = println(y)

def printAnyRef(z: AnyRef) = println(z)

val someVal = 5
val someRef = List(3,5)

printAny(5)
printAnyVal(5)
// Following line will complain about value 5 not being a ref
//printAnyRef(5)

