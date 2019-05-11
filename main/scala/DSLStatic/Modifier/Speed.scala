package DSLStatic.Modifier

import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}
import DSLStatic.Shape.Shape

case class Speed(VX: Double, VY: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    x.vx = VX
    x.vy = VY
  }
}