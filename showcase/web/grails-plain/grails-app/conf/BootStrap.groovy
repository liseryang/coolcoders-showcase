import net.coolcoders.showcase.User
import net.coolcoders.showcase.Gender
import net.coolcoders.showcase.Category

class BootStrap {

  def init = { servletContext ->
    environments {
      production {
        createCategories()
        createUsers()
      }
      development {
        createCategories()
        createUsers()
      }
      test {
        createCategories()
        createUsers()
      }
    }

  }
  def destroy = {
  }

  private void createCategories() {

    Category jee5 = new Category(name: "JEE5").save()
    log.debug("Created category $jee5")

    Category jee6 = new Category(name: "JEE6").save()
    log.debug("Created category $jee6")

    Category grails = new Category(name: "Grails").save()
    log.debug("Created category $grails")

    Category wicket = new Category(name: "Wicket").save()
    log.debug("Created category $wicket")

    Category gwt = new Category(name: "GWT").save()
    log.debug("Created category $gwt")
  }


  private void createUsers() {
    User pschneidermanzell = new User(username: "pschneider-manzell", password: "test123", email: "pschneider-manzell@coolcoders.net", gender: Gender.MALE).save()
    log.debug("Created user $pschneidermanzell")

    User abaumgartner = new User(username: "abaumgartner", password: "test123", email: "abaumgartner@coolcoders.net", gender: Gender.MALE).save()
    log.debug("Created user $abaumgartner")

    User jmihelko = new User(username: "jmihelko", password: "test123", email: "jmihelko@coolcoders.net", gender: Gender.MALE).save()
    log.debug("Created user $jmihelko")

    User anerlich = new User(username: "anerlich", password: "test123", email: "anerlich@coolcoders.net", gender: Gender.MALE).save()
    log.debug("Created user $anerlich")

    createDummyMessagesForUser(pschneidermanzell)
    createDummyMessagesForUser(abaumgartner)
    createDummyMessagesForUser(jmihelko)
    createDummyMessagesForUser(anerlich)

  }

  void createDummyMessagesForUser(User user) {
    log.debug("Creating dummy messages for user $user")
  }
}
