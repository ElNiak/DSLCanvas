package DSLStatic.Shape
import DSLStatic.Style.{Clear, Fill, Stroke, Style}

case class QuadraticCurve(X:Double, Y:Double, s: Int , o : Double, cp1X : Double, cp1Y : Double, cp2X : Double, cp2Y : Double) extends Shape {
  override var opacity: Double = o
  override var style : Style =  if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x : Double = X
  override var y : Double = Y
  override var size: Int = _
  override var rotation: Double = 0
  var cp1x : Double = cp1X
  var cp1y : Double = cp1Y
  var cp2x : Double = cp2X
  var cp2y : Double = cp2Y
}
