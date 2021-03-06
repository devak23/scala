import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

abstract class Account (id: UUID, name: String, openingDate: LocalDateTime) {
  val _id: UUID = id
  var _name: String = name
  val _openingDate: LocalDateTime = openingDate
  val _accountType: String

  // getters
  def getId: UUID = _id
  def getName: String = _name
  def getOpeningDate: LocalDateTime = _openingDate

  // setters/updaters
  def updateName (newName: String): Unit = _name = newName

  def this(name: String) {
    this(UUID.randomUUID(), name, LocalDateTime.now())
  }

  override def toString: String = s"You opened a ${_accountType} Account by the name '${_name}' on ${_openingDate.format(DateTimeFormatter.ofPattern("E dd/MM hh:mm a"))}"
}

class CreditAccount(name: String) extends Account(name: String) {
  override val _accountType: String = "Credit"
}

class LoanAccount(name: String) extends Account(name: String) {
  override val _accountType: String = "Loan"
}

class CheckingAccount(name: String, dateTime: LocalDateTime) extends Account(name: String) {
  override val _accountType: String = "Checking"
}

class SavingsAccount(name: String, dateTime: LocalDateTime) extends Account(name: String) {
  override val _accountType: String = "Savings"
}

class DMATAccount(name: String) extends Account(name: String) {
  override val _accountType: String = "DMAT"
}

object AccountRunner extends App {
  val checkingAccount = new CheckingAccount("My Checking Account", LocalDateTime.now())
  val savingsAccount = new SavingsAccount("My Savings Account", LocalDateTime.now.plusHours(6))
  val dmatAccount = new DMATAccount("My DMAT Account")

  println(checkingAccount)
  println(savingsAccount)
  println(dmatAccount)

  val creditAccount = new CreditAccount("Travel Mastercard")
  println(creditAccount)

  val loanAccount = new LoanAccount(name = "Overdraft Account")
  println(loanAccount)
}