package DSLStatic.Shape

import scala.collection.mutable.ListBuffer

class MyShapeExemple(override val X : Double, override val Y : Double, override val s : Int, override val o : Double, w : Int) extends PPLShape(X,Y,s,o,ListBuffer((0.0,0.0),(100.0,100.0),(100.0,50.0),(30.0,10.0),(25.0,50.0))) {

}
