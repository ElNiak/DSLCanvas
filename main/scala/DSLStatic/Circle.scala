package DSLStatic
import DSLStatic.Color.Color

class Circle(radius: Double, x:Int, y:Int) extends Shape {
  override val stroke: Stroke = _
  override val color: Color = _
  override val canevasyElementModifier: Any = _

  override def stroke(col: Color): Unit = ???

  override def translateY(y: Int): Unit = ???

  override def translateX(x: Int): Unit = ???
}
