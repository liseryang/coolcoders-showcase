import net.coolcoders.showcase.Category
import net.coolcoders.showcase.Gender
import net.coolcoders.showcase.AppUser

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
    if (!AppUser.get(1)) {
      def user = new AppUser(
              username: 'peter',
              password: 'test123',
              fullname: 'Peter Schneider-Manzell',
              email: 'peter@schneider-manzell.de',
              gender: Gender.MALE).save(failOnError: true)
      user.addToCategories(Category.get(1))
      user.addToCategories(Category.get(2))
      user = new AppUser(
              username: 'josip',
              password: 'test123',
              fullname: 'Josip Mihelko',
              email: 'josip@esistegalaber.de',
              gender: Gender.MALE).save(failOnError: true)
      user.addToCategories(Category.get(1))
      user.addToCategories(Category.get(2))
      user = new AppUser(
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
