package DSLStatic.Modifier

import DSLStatic.Shape.Shape
import DSLStatic.Style.Join.JoinValue
import DSLStatic.Style.Stroke
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class StrokeJoin(w: JoinValue) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    if (w.jn == null || w.jn.isEmpty)
      throw new ShapeAttributeException("Join cannot be null or empty")
    x.style match {
      case stroke : Stroke => stroke.join = w
      case _ => throw new ShapeAttributeException("Only stroke shape can be modified")
    }
  }

}
