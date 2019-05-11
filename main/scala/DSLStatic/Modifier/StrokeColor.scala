package DSLStatic.Modifier

import DSLStatic.Shape.{Circle, Shape}
import DSLStatic.Style.{ColorRGB, ColorStyle, Fill, Gradient, Stroke}
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class StrokeColor[I <: ColorStyle](c: I) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    x.style match {
      case stroke: Stroke =>
        c match {
          case b: ColorRGB =>
            stroke.colorStyle = b
            x.opacity = b.opacity
          case b: Gradient =>
            if(x.isInstanceOf[Circle])
              throw new ShapeAttributeException("Only Rectangle can have a gradient")
            stroke.colorStyle = b
        }
      case _ => throw new ShapeAttributeException("Only stroke shape can be modified")
    }
  }
}
