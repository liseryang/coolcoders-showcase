import net.coolcoders.showcase.smartgwt.User
import net.coolcoders.showcase.smartgwt.Category
import net.coolcoders.showcase.smartgwt.Gender
import net.coolcoders.showcase.smartgwt.User
import net.coolcoders.showcase.smartgwt.Gender

class BootStrap {

  def init = { servletContext ->
    if (!Category.get(1)) {
      new Category(name: 'JSF').save(failOnError: true)
      new Category(name: 'Grails').save(failOnError: true)
      new Category(name: 'GWT').save(failOnError: true)
      new Category(name: 'Wicket').save(failOnError: true)
      new Category(name: 'SpringMVC').save(failOnError: true)
      new Category(name: 'Spring Webflow').save(failOnError: true)
      new Category(name: 'Plain Html').save(failOnError: true)
      new Category(name: 'Flex').save(failOnError: true)
      new Category(name: 'Flash').save(failOnError: true)
    }
    if (!User.get(1)) {
      def user = new User(
              username: 'peter',
              password: 'test123',
              fullname: 'Peter Schneider-Manzell',
              email: 'peter@schneider-manzell.de',
              gender: Gender.MALE).save(failOnError: true)
      user.addToCategories(Category.get(1))
      user.addToCategories(Category.get(2))
      user = new User(
              username: 'josip',
              password: 'test123',
              fullname: 'Josip Mihelko',
              email: 'josip@esistegalaber.de',
              gender: Gender.MALE).save(failOnError: true)
      user.addToCategories(Category.get(1))
      user.addToCategories(Category.get(2))
      user = new User(
              username: 'bambo',
              password: 'test123',
              fullname: 'Andreas Baumgartner',
              email: 'bambo@baumgartner.de',
              gender: Gender.MALE).save(failOnError: true)
      user.addToCategories(Category.get(1))
      user.addToCategories(Category.get(2))
    }
  }
  def destroy = {
  }
}
