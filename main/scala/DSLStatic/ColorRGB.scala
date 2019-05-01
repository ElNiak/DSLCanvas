package DSLStatic

case class ColorRGB(string: String, o : Double){
  val color : String = string
  val opacity : Double = o
}

object ColorRGB {
  implicit class ColorRGBUtils(val sc: StringContext) extends AnyVal {
    def rgb(args : String) : ColorRGB = {
      print(args)
      if(checkColorPattern(args))
        ColorRGB(args,1)
      else
        ColorRGB("#000000",1)
    }
    def rgb(args : Unit) : ColorRGB = {
      println(sc.parts(0))
      println(checkColorPattern(sc.parts(0)))
      if(checkColorPattern(sc.parts(0)))
        ColorRGB(sc.parts(0),1)
      else
        ColorRGB("#000000",1)
    }
    def rgba(args : String) : ColorRGB = { //rgba"1.0#000000"
      var array = args.split("#")
      array foreach(println(_))
      if(checkColorPattern('#'+array(1)) && checkColorPattern2(array(0)))
        ColorRGB('#'+array(1),array(0).toDouble)
      else
        ColorRGB("#000000",1)
    }
    def rgba(args : Unit) : ColorRGB = {
      var array = sc.parts(0).split("#")
      array foreach(println(_))
      if(checkColorPattern('#'+array(1)) && checkColorPattern2(array(0)))
        ColorRGB('#'+array(1),array(0).toDouble)
      else
        ColorRGB("#000000",1)
    }
  }
  def checkColorPattern(color : String): Boolean = {
    println(color.charAt(0))
    println(color.length)
    if(color.charAt(0) == '#')
      if(color.length == 7)
        return true
    false
  }
  def checkColorPattern2(color : String): Boolean ={
    val test = (x : Char) => x.isDigit || x.equals('.')
    for(a <- color)
      if(!test(a))
        false
    true
  }
}