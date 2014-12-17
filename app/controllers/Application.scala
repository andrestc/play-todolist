package controllers

import play.api.data._
import play.api.data.Forms._
import play.api.mvc.Action
import play.api.mvc.Controller
import models.Task

object Application extends Controller {

  val taskForm = Form(
		  tuple(
        "label" -> nonEmptyText,
        "category" -> nonEmptyText
      )
  )


  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
        errors => BadRequest(views.html.index(Task.all(),errors)),
        data => {
          val (label, category) = data
          Task.create(label, category)
          Redirect(routes.Application.tasks)
        }
    )
  }


  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }

  def viewTask(id: Long) = Action {
    val task = Task.getById(id)
    if (task != null) Ok(views.html.task(task))
    else BadRequest(views.html.index(Task.all(), taskForm))
  }

}
