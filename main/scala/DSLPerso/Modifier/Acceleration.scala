package DSLPerso.Modifier

import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}
import DSLPerso.Shape.Shape

case class Acceleration(AX: Double, AY: Double) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    x.ax = AX
    x.ay = AY
  }
}