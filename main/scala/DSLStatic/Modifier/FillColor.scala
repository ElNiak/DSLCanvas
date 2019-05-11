package DSLStatic.Modifier

import DSLStatic.Shape.{Circle, Shape}
import DSLStatic.Style.{ColorRGB, ColorStyle, Fill, Gradient, Style}
import DSLStatic.{CanvasyElementModifier, ShapeAttributeException}

case class FillColor [I <: ColorStyle](c: I) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    x.style match {
      case fill: Fill =>
        c match {
          case b: ColorRGB =>
            fill.colorStyle = b
            x.opacity = b.opacity
          case b: Gradient =>
            if(x.isInstanceOf[Circle])
              throw new ShapeAttributeException("Only Rectangle can have a gradient")
            fill.colorStyle = b
        }
      case _ => throw new ShapeAttributeException("Only filled shape can be modified")
    }
  }
}
