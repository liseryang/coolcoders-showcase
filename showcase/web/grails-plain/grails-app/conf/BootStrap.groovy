import net.coolcoders.showcase.User
import net.coolcoders.showcase.Gender

class BootStrap {

  def init = { servletContext ->
    environments {
      production {
        createDummyUsers()
      }
      development {
        createDummyUsers()
      }
      test {
        createDummyUsers()
      }
    }

  }
  def destroy = {
  }


  private void createDummyUsers() {
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
    log.debug("Creating dummy messaged for user $user")
  }
}
