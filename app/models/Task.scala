package models

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import com.sun.xml.internal.bind.v2.TODO
import play.api.Play.current

case class Task(id: Long, label: String, category: String)

object Task {
  val task = {
    get[Long]("id") ~
    get[String]("label") ~
    get[String] ("category") map {
      case id~label~category => Task(id, label, category)
    }
  }

  def all(): List[Task] = DB.withConnection { implicit c =>
  	SQL("select * from task").as(task *)
  }

  def create(label: String, category: String) {
    DB.withConnection { implicit c =>
      SQL("insert into task (label, category) values ({label},{category})").on(
        "label" -> label,
        "category" -> category
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from task where id = {id}").on(
        "id" -> id
      ).executeUpdate()
    }
  }

  def getById(id: Long): Task = DB.withConnection { implicit c =>
    val row = SQL("select id, label, category from task where id = {id}").on("id" -> id).apply().head
    Task(row[Long]("id"), row[String]("label"), row[String]("category"))
  }

}
