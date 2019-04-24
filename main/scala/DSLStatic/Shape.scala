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
}


object Shape {
  def and(shape: Shape) = {
    val array = new Array[Shape]()
  }

  implicit class Group[ApplyOn <: Shape](group: Array[Shape]) {

    def translateY[ApplyOn <: Shape](Y: Int) : Array[Shape] = {
      group foreach(_ translateY(Y))
      return group
    }
    def translateX[ApplyOn <: Shape](X: Int) : Array[Shape] = {
      group foreach(_ translateX(X))
      return group
    }
  }

  def change(shape: Shape) = {

  }

  def change(canvasyElement: CanvasyElement) = {

  }
}
