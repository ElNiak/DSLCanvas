package DSLStatic


trait Shape{
  var stroke : Stroke
  var color : String
  var x : Int
  var y : Int
  var canvasyElement : CanvasyElement


  def stroke[A](v : A) : Unit
  def and[A](v: A) : A
  def translateY(Y : Int) : Unit = {y += Y}
  def translateX(X : Int) : Unit = {x += X}

  implicit class arrayShape(group: Array[Circle]) {
    def translateY(Y: Int) : Unit = {group foreach(_ translateY(Y))}
    def translateX(X: Int) : Unit = {group foreach(_ translateX(X))}
  }

object Shape {
  def and(shape: Shape) = {

  }

  def change(shape: Shape) = {

  }

  def change(canvasyElement: CanvasyElement) = {

  }
}
