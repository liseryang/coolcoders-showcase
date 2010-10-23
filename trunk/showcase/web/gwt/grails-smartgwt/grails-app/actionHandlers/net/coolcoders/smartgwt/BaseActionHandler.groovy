package net.coolcoders.smartgwt

import org.apache.log4j.Logger
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletWebRequest

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class BaseActionHandler implements GroovyInterceptable {
  protected static transient Logger log = Logger.getLogger(getClass())

  def session;

  def invokeMethod(String name, args) {
    if ('execute'.equals(name)) {
      session = ((ServletWebRequest) RequestContextHolder.currentRequestAttributes()).getSession(false)
    }
    def metaMethod = metaClass.getMetaMethod(name, args)
    def result = metaMethod.invoke(this, args)
    return result
  }
}
