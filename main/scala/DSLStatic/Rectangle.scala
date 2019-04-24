package DSLStatic
import DSLStatic.Color.Color

case class Rectangle(X:Int, Y: Int, widthI: Double, heightI: Double) extends Shape {
  override var stroke: Stroke
  override var color: Color.Value
  override var x: Int
  override var y: Int
  var height  = heightI
  var width = widthI

  override var size: Int = _

  override def stroke[A](v: A): Unit = ???

  override def change(x: CanvasyElementModifier[Shape]): Shape = ???
}
