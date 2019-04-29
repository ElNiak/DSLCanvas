package DSLStatic
import DSLStatic.Color.Color

case class Circle(radiusv: Double, X:Int, Y:Int) extends Shape {
  var stroke = new Stroke
  var color = Color.red
  var radius = radiusv
  var x = X
  var y = Y
  override var size: Int = _
}
