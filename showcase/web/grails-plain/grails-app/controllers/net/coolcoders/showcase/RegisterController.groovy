package net.coolcoders.showcase

class RegisterController {

  def index = {
    User userInstance = new User()
    def availableCategories = Category.list()
    [userInstance: userInstance, availableCategories: availableCategories]
  }

  def save = {
    log.debug("Entering save with params $params")
    User userInstance = new User()
    userInstance.properties = params

    def categoryIdsParameter = params['categoryIds']
    if (categoryIdsParameter) {
      if (categoryIdsParameter instanceof String) {
        userInstance.categories =  [Category.get(categoryIdsParameter as Long)]
      }
      else {
        def categoryIds = []
        categoryIdsParameter.each {
          categoryIds.add it as Long
        }
        userInstance.categories = Category.getAll(categoryIds)
      }
    }

    log.debug("User categories: $userInstance.categories")

    if (!userInstance.validate()) {
      def availableCategories = Category.list()
      render(view: "index", model: [userInstance: userInstance, availableCategories: availableCategories])
    }
    else {
      userInstance.save()
      flash.message = "User created!"
      redirect(uri:"/")
    }

  }
}
