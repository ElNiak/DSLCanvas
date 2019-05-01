package DSLStatic
import org.scalajs.dom

case class Circle(radiusv: Double, X:Double, Y:Double, s : Int, o : Double) extends Shape {
  override val state : Int = s
  override var opacity: Double = o
  override var stroke : Stroke = new Stroke()
  override var fill : Fill = new Fill
  override var color : ColorRGB = Color.red
  var radius : Double = radiusv
  override var x : Double = X
  override var y : Double = Y
  override var size: Int = _

  def apply(w: Double): Unit = {
    radius = w
  }

  def apply(w: ColorRGB): Unit = {
    color = w
  }

  def apply(w: Stroke): Unit = {
    stroke = w
  }

  def apply(w: Fill): Unit = {
    fill = w
  }

}
