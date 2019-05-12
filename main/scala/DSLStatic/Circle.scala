package DSLStatic
import org.scalajs.dom

case class Circle(radiusv: Double, X:Int, Y:Int) extends Shape {
  var stroke = new Stroke()
  var color = Color.red
  var radius = radiusv
  var x = X
  var y = Y

  def apply(w: Double): Unit = {
    radius = w
  }

  def apply(w: String): Unit = {
    color = w
  }

  def apply(w: Stroke): Unit = {
    stroke = w
  }

  override def size(size: Int): Unit = {
    println(size)
    radius = size
  }
}
