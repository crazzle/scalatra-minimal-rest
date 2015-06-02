/**
 * Created by mark on 02.06.15.
 */
import akka.actor.{Props, ActorSystem}
import com.example.app.{MyActor, MyScalatraServlet}
import org.scalatra._
import javax.servlet.ServletContext


class ScalatraBootstrap extends LifeCycle {

  val system = ActorSystem()
  val myActor = system.actorOf(Props[MyActor])

  override def init(context: ServletContext) {
    context.mount(new MyScalatraServlet(system, myActor),"/*")
  }

  override def destroy(context:ServletContext) {
    system.shutdown()
  }
}