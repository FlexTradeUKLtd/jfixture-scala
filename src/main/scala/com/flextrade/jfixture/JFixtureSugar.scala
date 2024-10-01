package com.flextrade.jfixture

import com.flextrade.jfixture.internal.ScalaRelays
import com.flextrade.jfixture.utility.Interceptor

import java.lang.reflect.Type
import scala.reflect.{ClassTag, classTag}

trait JFixtureSugar extends JFixtureSugarPlatformSpecific {
  val defaultFixture: JFixture = new JFixture()
    .addBuilderToStartOfPipeline(ScalaRelays.collections)
    .addBuilderToStartOfPipeline(ScalaRelays.primitives)
    .addBuilderToStartOfPipeline(ScalaRelays.reflection)

  /**
   * The following methods can be accessed when customising the fixture - e.g.,
   * <pre>
   * <code>
   * defaultFixture.customise().scalaLazyInstance[SomeCaseClass](SomeCaseClass(1,2,3))
   * </code>
   * </pre>
   */
  implicit class FluentCustomisationScalaConversion(customisation: FluentCustomisation) {

    def scalaIntercept[T: ClassTag](interceptor: T => T): FluentCustomisation = {
      customisation.intercept(classTag[T].runtimeClass.asInstanceOf[Class[T]], new Interceptor[T] {
        override def intercept(instance: T): Unit = interceptor(instance)
      })
    }

    def scalaLazyInstance[T: ClassTag](instanceSupplier: => T): FluentCustomisation = {
      customisation.lazyInstance(classTag[T].runtimeClass.asInstanceOf[Type], new SpecimenSupplier[T] {
        override def create(): T = instanceSupplier
      })
    }

  }

}
