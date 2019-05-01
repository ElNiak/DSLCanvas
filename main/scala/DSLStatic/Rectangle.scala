package DSLStatic
import DSLStatic.Color.Color

case class Rectangle(X:Int, Y: Int, widthI: Double, heightI: Double) extends Shape {
  override var stroke: Stroke = new Stroke
  override var color: String = Color.red
  override var x: Double = X
  override var y: Double = Y
  var height  = heightI
  var width = widthI

  override var size: Int = _

}
