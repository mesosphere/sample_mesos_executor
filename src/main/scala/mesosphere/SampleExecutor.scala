package mesosphere

import org.apache.mesos.{MesosExecutorDriver, ExecutorDriver, Executor}
import org.apache.mesos.Protos._

/**
 * @author Florian Leibert (flo@leibert.de)
 */
class SampleExecutor extends Executor {

  def registered(executorDriver: ExecutorDriver, executorInfo: ExecutorInfo,
                 frameworkInfo: FrameworkInfo, slaveInfo: SlaveInfo) {
    //Not implemented
  }

  def reregistered(p1: ExecutorDriver, p2: SlaveInfo) {
    //Not implemented
  }

  def disconnected(p1: ExecutorDriver) {
    //Not implemented
  }

  def launchTask(driver: ExecutorDriver, taskInfo: TaskInfo) {
      val status1: TaskStatus = TaskStatus
        .newBuilder.setTaskId(taskInfo.getTaskId)
        .setState(TaskState.TASK_RUNNING).build()

      driver.sendStatusUpdate(status1)

      // TODO(*): The logic goes here.
  }

  def killTask(p1: ExecutorDriver, p2: TaskID) {
    //Not implemented
  }

  def frameworkMessage(p1: ExecutorDriver, p2: Array[Byte]) {
    //Not implemented
  }

  def shutdown(p1: ExecutorDriver) {
    //Not implemented
    System.exit(0)
  }

  def error(p1: ExecutorDriver, p2: String) {
    //Not implemented
  }
}

object ShellExecutor {

  def main(args: Array[String]) {

    val executor = new SampleExecutor()
    val executorDriver = new MesosExecutorDriver(executor)
    val status = executorDriver.run()
    status match {
      case Status.DRIVER_STOPPED => System.exit(0)
      case _ => System.exit(1)
    }
  }
}
