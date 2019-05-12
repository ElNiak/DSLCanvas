package DSLPerso.Modifier

import DSLPerso.Shape.Shape
import DSLPerso.Style.Stroke
import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}

case class StrokeDashOff(w: Double) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    if (w < 0)
      throw new ShapeAttributeException("DashOff cannot be smaller than 0")
    x.style match {
      case stroke: Stroke => stroke.offset = w
      case _ => throw new ShapeAttributeException("Only stroke shape can be modified")
    }
  }

}
