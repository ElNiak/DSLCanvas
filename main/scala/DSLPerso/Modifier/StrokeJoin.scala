package DSLPerso.Modifier

import DSLPerso.Shape.Shape
import DSLPerso.Style.Join.JoinValue
import DSLPerso.Style.Stroke
import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}

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
