package DSLStatic.Shape

import scala.collection.mutable.ListBuffer

class MyShapeExemple(override val X : Double, override val Y : Double, override val s : Int, override val o : Double, w : Int) extends Xogone(X,Y,s,o,ListBuffer((0,0),(100,100),(100,50),(30,10),(25,50))) {

}
