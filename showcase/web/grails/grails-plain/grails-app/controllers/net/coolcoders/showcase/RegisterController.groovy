package net.coolcoders.showcase

class RegisterController {

  def index = {
    AppUser userInstance = new AppUser()
    def availableCategories = Category.list()
    [userInstance: userInstance, availableCategories: availableCategories]
  }

  def save = {
    log.debug("Entering save with params $params")
    AppUser userInstance = new AppUser()
    userInstance.properties = params

    def categoryIdsParameter = params['categoryIds']
    if (categoryIdsParameter) {
      if (categoryIdsParameter instanceof String) {
        userInstance.categories = [Category.get(categoryIdsParameter)]
      }
      else {
        def categoryIds = []
        categoryIdsParameter.each {
          categoryIds.add it
        }
        userInstance.categories = Category.getAll(categoryIds)
      }
    }

    log.debug("User categories: $userInstance.categories")
    userInstance.validate()
    if (userInstance.password != userInstance.repassword) {
      userInstance.errors.rejectValue('repassword', 'invalid.repassword')
    }



    if (userInstance.hasErrors()) {
      log.debug("User has validation errors... $userInstance.errors...")
      def availableCategories = Category.list()
      render(view: "index", model: [userInstance: userInstance, availableCategories: availableCategories])
    }
    else {

      userInstance.save()
      log.debug("Created user $userInstance...")
      flash.message = "User created!"
      redirect(uri: "/")
    }

  }
}
