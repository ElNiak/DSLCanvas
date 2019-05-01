package DSLStatic
import DSLStatic.Color.Color

case class Rectangle(X:Double, Y: Double, widthI: Double, heightI: Double, s : Int, o : Double) extends Shape {
  override val state  : Int = s
  override var opacity: Double = o
  override var stroke: Stroke = new Stroke
  override var fill : Fill = new Fill
  override var color: ColorRGB = Color.red
  override var x: Double = X
  override var y: Double = Y
  var height  = heightI
  var width = widthI

  override var size: Int = _

}
