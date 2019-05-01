package DSLStatic

case class FillColor(c: ColorRGB) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if(x.state == 1) {
      x.fill.color = c
      x.opacity = c.opacity
    }
    else
      throw new ShapeAttributeException("Only filled shape can be modified")
  }
}

