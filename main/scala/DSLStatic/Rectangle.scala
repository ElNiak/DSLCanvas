package DSLStatic
import DSLStatic.Color.Color

<<<<<<< HEAD
class Rectangle(x:Int, y:Int, width: Double, height: Double) extends Shape {
  override val stroke: Stroke = _
  override val color: Color = _
  override val canevasyElementModifier: Any = _
=======
class Rectangle(x:Int, y:Int, width: Int, height: Int) extends Shape {
  override var stroke: Stroke
  override var color: Color
  override var x: Int
  override var y: Int
//  override val canevasyElementModifier: Any = _
>>>>>>> 394d4ee... Fix canvasy

  override def stroke(col: Color): Unit = ???

  override def and(shape: Shape): Groups = ???

  override def translateY(y: Int): Unit = ???

  override def translateX(x: Int): Unit = ???
}
