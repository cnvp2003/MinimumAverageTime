package component

import com.mycompany.assignment.component.CustomerServeComponent
import com.mycompany.assignment.model.Customer

import scala.collection.mutable
import org.scalatest.{FlatSpec, Matchers}

class CustomerServeComponentSpecs  extends FlatSpec with Matchers {
  var totalTime = 0
  var currentTime = 0

  val customerPriority = new mutable.PriorityQueue[Customer]()(Ordering.by[Customer, Int](_.cookTime).reverse)

  trait ENV {
    //test data set 1
    val customer1 = Customer(0, 3)
    val customer2 = Customer(1, 9)
    val customer3 = Customer(2, 6)
    val customers:List[Customer]= List(customer1,customer2,customer3)

    //test data set 2
    val customer4 = Customer(0, 3)
    val customer5 = Customer(1, 9)
    val customer6 = Customer(2, 5)
    val customersOther:List[Customer]= List(customer4,customer5,customer6)
  }

  "getTotalTime" should "return correct wait average time" in new ENV {
    CustomerServeComponent.averageWaitTime(customers) shouldBe(9)
    CustomerServeComponent.averageWaitTime(customersOther) shouldBe(8)
  }

  it should "return correct total time" in new ENV {
    CustomerServeComponent.getTotalTime(customers) shouldBe(27)
  }
}