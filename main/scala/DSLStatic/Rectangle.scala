package DSLStatic
import DSLStatic.Color.Color

class Rectangle(x:Int, y:Int, width: Width, height: Height) extends Shape {
  override val stroke: Stroke = _
  override val color: Color = _
  override val x: Int = _
  override val canevasyElementModifier: Any = _

  override def stroke(col: Color): Unit = ???

  override def and(shape: Shape): Groups = ???

  override def translateY(y: Int): Unit = ???

  override def translateX(x: Int): Unit = ???
}
