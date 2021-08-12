package learning.scala.programming

object PagePrinter {

  def printPages(doc: Document, lastIndex: Int): Unit =
    if (lastIndex <= doc.numOfPages)
      for (i <- 1 to lastIndex) print(i)

  def printPages(doc: Document, firstIndex: Int, lastIndex: Int): Unit =
    if (lastIndex <= doc.numOfPages && firstIndex > 0 && firstIndex < lastIndex)
      for(i <- firstIndex to lastIndex) print(i)

  def printPages(doc: Document, indexes: Int*): Unit =
    for (i <- indexes if i < doc.numOfPages && i > -1)
      print(i)

  private def print(index: Int): Unit = println(s"Printing page $index")

  def main(args: Array[String]): Unit = {
    println("---------------Method V1: lastIndex---------------")
    printPages(doc = Document(15, "DOCX"), lastIndex = 5)

    println("---------------Method V2: firstIndex, lastIndex---------------")
    printPages(doc = Document(15,"DOCX"), firstIndex = 10, lastIndex = 15)

    println("---------------Method V3: individual pages---------------")
    printPages(doc = Document(15, "DOCX"), 2,5,7,9,11)
  }
}

case class Document(numOfPages: Int, typeOfDoc: String)
