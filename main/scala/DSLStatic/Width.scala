package DSLStatic

case class Width(w: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    x match {
      case Rectangle(a,b,width, height) =>  {
        x.asInstanceOf[Rectangle].width = w
      }
      case _ => throw new ShapeAttributeException("Only rectangle have width")
    }
  }
}
