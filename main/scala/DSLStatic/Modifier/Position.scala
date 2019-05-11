package DSLStatic.Modifier

import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}
import DSLStatic.Shape.Shape

case class Position(X: Double, Y: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    if (X < 0)
      throw new ShapeAttributeException("x cannot be smaller than 0")
    if (Y < 0)
      throw new ShapeAttributeException("y cannot be smaller than 0")
    x.x = X
    x.y = Y
  }
}