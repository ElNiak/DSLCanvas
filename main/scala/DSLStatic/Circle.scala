package DSLStatic
import DSLStatic.Color.Color

case class Circle(radiusv: Double, X:Int, Y:Int) extends Shape {
  var stroke = new Stroke()
  var color = Color.red
  var radius = radiusv
  var x = X
  var y = Y
  override var size: Int = _

  def apply(w: Double): Unit = {
    radius = w
  }

  def apply(w: String): Unit = {
    color = w
  }

  def apply(w: Stroke): Unit = {
    stroke = w
  }

}
