package DSLStatic.Modifier

import DSLStatic.Shape.Shape
import DSLStatic.Style.Cap.CapValue
import DSLStatic.Style.Stroke
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

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
