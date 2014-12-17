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
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "redirect the index page to tasks" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(SEE_OTHER)
      redirectLocation(home) must beSome("/tasks")
    }

    "send 200 on the tasks page" in new WithApplication{
      val tasks = route(FakeRequest(GET, "/tasks")).get

      status(tasks) must equalTo(OK)
    }

  }

}
