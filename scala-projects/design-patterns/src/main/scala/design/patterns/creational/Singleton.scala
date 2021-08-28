package design.patterns.creational

import java.util.concurrent.TimeUnit
import scala.collection.concurrent.TrieMap

object AppRegistry {
  println("Registry initialization block is invoked.")
  private val users: TrieMap[String, String] = TrieMap.empty

  def addUser(id: String, name: String): Unit = {
    users.put(id, name)
  }

  def removeUser(id: String): Unit = {
    users.remove(id)
  }

  def isUserRegistered(id: String): Boolean = users.contains(id)

  def getAllUserNames: List[String] = users.values.toList
}


object Singleton extends App {
  println("Sleeping for 5 seconds")
  TimeUnit.SECONDS.sleep(5)
  println("I woke up")
  AppRegistry.addUser("1", "Federer")
  AppRegistry.addUser("2", "Ronaldo")
  AppRegistry.addUser("3", "Phelps")
  AppRegistry.addUser("4", "Fischer")
  println(s"Is user with ID = 1 registered? ${AppRegistry.isUserRegistered("1")}")
  println(s"Removing user with ID = 3")
  AppRegistry.removeUser("3")
  println(s"Is user with Id = 4 registered? ${AppRegistry.isUserRegistered("4")}")
  println(s"All pending users are: ${AppRegistry.getAllUserNames.mkString(",")}")
}
