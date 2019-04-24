package DSLStatic
import DSLStatic.Color.Color

case class Rectangle(x:Int, y:Int, width: Int, height: Int) extends Shape {
  override var stroke: Stroke
  override var color: Color
  override var x: Int
  override var y: Int
//  override val canevasyElementModifier: Any = _



//  override def translateY(y: Int): Unit = ???
//
//  override def translateX(x: Int): Unit = ???
}
