package DSLStatic.Style

import DSLStatic.ShapeAttributeException

import scala.collection.mutable.ListBuffer

/**
  * Used to represent gradient style
  */
case class Gradient(X1 : Double, Y1 : Double,X2 : Double, Y2 : Double, R1 : Double, R2 : Double,  color: ListBuffer[ColorRGB], off : ListBuffer[Double]) extends ColorStyle {
  val x1 : Double = if(X1 >= 0) X1 else throw new ShapeAttributeException("X1 cannot be smaller than 0")
  val y1 : Double = if(Y1 >= 0) Y1 else throw new ShapeAttributeException("Y1 cannot be smaller than 0")
  val x2 : Double = if(X2 >= 0) X2 else throw new ShapeAttributeException("X2 cannot be smaller than 0")
  val y2 : Double = if(Y2 >= 0) Y2 else throw new ShapeAttributeException("Y2 cannot be smaller than 0")
  val r1 : Double = if(R1 >= -1) R1 else throw new ShapeAttributeException("R1 cannot be smaller than 0")
  val r2 : Double = if(R2 >= -1) R2 else throw new ShapeAttributeException("R2 cannot be smaller than 0")
  val colors : ListBuffer[ColorRGB] = color
  val offset : ListBuffer[Double] = off
  for(i <- offset)
    if(i < 0 || i >1) throw new ShapeAttributeException("offset must be [0,1]")
  if(colors.size != offset.size)throw new ShapeAttributeException("Number of offset must be equal to the number of color cannot be smaller than 0")
  def this() {
      this(0,0,0,0,0,0,null,null)
  }
}

object Gradient {
  /**
    * Extension of the String Context to add gradL and gradR
    */
  implicit class GradientUtils(val sc: StringContext) extends AnyVal {
    def gradR(args : String) : Gradient = {// gradR"x1&y1&x2&y2&R1&R2&0.1#58a3f8,0.2#58a3f8"
      val array : Array[String] = args.split("&")
      val inter : Array[String] = array(array.length-1).split(",")
      val col : Array[String] = new Array[String](inter.length)
      val off : Array[String] = new Array[String](inter.length)

      for(item <- inter.indices){
        col(item) = inter(item).split("#")(1)
        off(item) = inter(item).split("#")(0)
      }

      var color : ListBuffer[ColorRGB] = new ListBuffer[ColorRGB]
      var offset : ListBuffer[Double] = new ListBuffer[Double]
      if(checkColor(col) && checkCoord(array) && checkCoord(col)){
        for(i <- col.indices)
          if(!col(i).isEmpty) {
            if(i == col.length-1)
              color += ColorRGB('#'+col(i),0)
            else
              color += ColorRGB('#'+col(i),1)
          }
        for(i <- off.indices)
          offset += off(i).toDouble
        Gradient(array(0).toDouble,array(1).toDouble,array(2).toDouble,array(3).toDouble,array(4).toDouble,array(5).toDouble,color,offset)
      }
      else {
        for(i <- col.indices)
          color += ColorRGB("#000000",1)
        for(i <- off.indices)
          offset += 0
        Gradient(array(0).toDouble,array(1).toDouble,array(2).toDouble,array(3).toDouble,array(4).toDouble,array(5).toDouble,color,offset)
      }
    }
    def gradR(args : Unit) : Gradient = {
      val array : Array[String] = sc.parts(0).split("&")
      val inter : Array[String] = array(array.length-1).split(",")
      val col : Array[String] = new Array[String](inter.length)
      val off : Array[String] = new Array[String](inter.length)

      for(item <- inter.indices){
        col(item) = inter(item).split("#")(1)
        off(item) = inter(item).split("#")(0)
      }

      var color : ListBuffer[ColorRGB] = new ListBuffer[ColorRGB]
      var offset : ListBuffer[Double] = new ListBuffer[Double]
      if(checkColor(col) && checkCoord(array) && checkCoord(col)){
        for(i <- col.indices)
          if(!col(i).isEmpty) {
            if(i == col.length-1)
              color += ColorRGB('#'+col(i),0)
            else
              color += ColorRGB('#'+col(i),1)
          }
        for(i <- off.indices)
          offset += off(i).toDouble
        Gradient(array(0).toDouble,array(1).toDouble,array(2).toDouble,array(3).toDouble,array(4).toDouble,array(5).toDouble,color,offset)
      }
      else {
        for(i <- col.indices)
          color += ColorRGB("#000000",1)
        for(i <- off.indices)
          offset += 0
        Gradient(array(0).toDouble,array(1).toDouble,array(2).toDouble,array(3).toDouble,array(4).toDouble,array(5).toDouble,color,offset)
      }
    }
    def gradL(args : String) : Gradient = {// gradL"x1|y1|x2|y2|0.1,0.2,0.6,0.1|#58a3f8#58a3f8"
      val array : Array[String] = args.split("&")
      val inter : Array[String] = array(array.length-1).split(",")
      val col : Array[String] = new Array[String](inter.length)
      val off : Array[String] = new Array[String](inter.length)

      for(item <- inter.indices){
        col(item) = inter(item).split("#")(1)
        off(item) = inter(item).split("#")(0)
      }
      var color : ListBuffer[ColorRGB] = new ListBuffer[ColorRGB]
      var offset : ListBuffer[Double] = new ListBuffer[Double]
      if(checkColor(col) && checkCoord(array) && checkCoord(col)){
        for(i <- col.indices)
          if(!col(i).isEmpty) {
            if(i == col.length-1)
              color += ColorRGB('#'+col(i),0)
            else
              color += ColorRGB('#'+col(i),1)
          }
        for(i <- off.indices)
          offset += off(i).toDouble
        Gradient(array(0).toDouble,array(1).toDouble,array(2).toDouble,array(3).toDouble,-1,-1,color,offset)
      }
      else {
        for(i <- col.indices)
          color += ColorRGB("#000000",1)
        for(i <- off.indices)
          offset += 0
        Gradient(array(0).toDouble,array(1).toDouble,array(2).toDouble,array(3).toDouble,-1,-1,color,offset)
      }
    }
    def gradL(args : Unit) : Gradient = {
      val array : Array[String] = sc.parts(0).split("&")
      val inter : Array[String] = array(array.length-1).split(",")
      val col : Array[String] = new Array[String](inter.length)
      val off : Array[String] = new Array[String](inter.length)

      for(item <- inter.indices){
        col(item) = inter(item).split("#")(1)
        off(item) = inter(item).split("#")(0)
      }
      var color : ListBuffer[ColorRGB] = new ListBuffer[ColorRGB]
      var offset : ListBuffer[Double] = new ListBuffer[Double]
      if(checkColor(col) && checkCoord(array) && checkCoord(col)){
        for(i <- col.indices)
          if(!col(i).isEmpty) {
            if(i == col.length-1)
              color += ColorRGB('#'+col(i),0)
            else
              color += ColorRGB('#'+col(i),1)
          }
        for(i <- off.indices)
          offset += off(i).toDouble
        Gradient(array(0).toDouble,array(1).toDouble,array(2).toDouble,array(3).toDouble,-1,-1,color,offset)
      }
      else {
        for(i <- col.indices)
          color += ColorRGB("#000000",1)
        for(i <- off.indices)
          offset += 0
        Gradient(array(0).toDouble,array(1).toDouble,array(2).toDouble,array(3).toDouble,-1,-1,color,offset)
      }
    }
  }

  def checkCoord(coo : Array[String]): Boolean = {
    val test = (x : Char) => x.isDigit || x.equals('.')
    var res = true
    coo foreach(_ foreach(res && test(_)))
    res
  }

  def checkColor(coo : Array[String]): Boolean = {
    val test = (x : String) => x.length == 6
    var res = true
    coo foreach(res && test(_))
    res
  }
}