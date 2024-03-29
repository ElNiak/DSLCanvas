package DSLPerso.Modifier

import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}
import DSLPerso.Shape.Shape

case class Speed(VX: Double, VY: Double) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    x.vx = VX
    x.vy = VY
  }
}