package DSLStatic
import DSLStatic.Color.Color

case class Rectangle(X:Int, Y: Int, widthI: Double, heightI: Double) extends Shape {
  override var stroke: Stroke = new Stroke
  override var color: String = Color.red
  override var x: Int = X
  override var y: Int = Y
  var height  = heightI
  var width = widthI

  override var size: Int = _

}
