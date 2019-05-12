package DSLPerso.Modifier

import DSLPerso.Shape.Shape
import DSLPerso.Style.Cap.CapValue
import DSLPerso.Style.Stroke
import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}

case class StrokeCap(w: CapValue) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    if (w.cap == null || w.cap.isEmpty)
      throw new ShapeAttributeException("Cap cannot be null or empty")
    x.style match {
      case stroke: Stroke => stroke.cap = w
      case _ => throw new ShapeAttributeException("Only stroke shape can be modified")
    }
  }
}
