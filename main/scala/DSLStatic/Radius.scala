package DSLStatic

case class Radius(r: Double) extends CanevasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = x.radius = r
}
