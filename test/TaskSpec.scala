import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

import play.api.libs.json._

/**
* Add your spec here.
* You can mock out a whole application including requests, plugins etc.
* For more information, consult the wiki.
*/
@RunWith(classOf[JUnitRunner])
class TaskSpec extends Specification {

  "Task" should {

    "create tasks" in new WithApplication{
      val sizeBefore = models.Task.all().size
      models.Task.create("Task label", "Task category")
      val tasks = models.Task.all()
      tasks.size must equalTo(sizeBefore + 1)
    }

    "delete tasks" in new WithApplication{
      models.Task.create("Task label", "Task category")
      val sizeBefore = models.Task.all().size
      models.Task.delete(1)
      models.Task.all().size must equalTo(sizeBefore - 1)
    }

    "list all tasks" in new WithApplication{
      val sizeBefore = models.Task.all().size
      models.Task.create("Task label", "Task category")
      models.Task.create("Task label 2", "Task category")
      models.Task.create("Task label 3", "Task category")

      models.Task.all().size must equalTo(sizeBefore + 3)
    }

  }

}
