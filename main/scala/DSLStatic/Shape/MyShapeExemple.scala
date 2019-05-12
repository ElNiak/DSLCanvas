package DSLStatic.Shape

import DSLStatic.Style.SV.SValue

import scala.collection.mutable.ListBuffer

class MyShapeExemple(val X : Double, val Y : Double, override val s : SValue, override val o : Double, w : Int)
  extends PPLShape((X,Y),s,o,ListBuffer((0.0,0.0),(100.0,100.0),(100.0,50.0),(30.0,10.0),(25.0,50.0))) {
}
