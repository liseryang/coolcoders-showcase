package net.coolcoders.showcase

class ProfileController {

  def index = {
    User userInstance = User.get(session.currentUser.id)
    def availableCategories = Category.list()
    [userInstance: userInstance, availableCategories: availableCategories]
  }


  def update = {
    log.debug("Entering update with params $params")
    User userInstance = User.get(session.currentUser.id)
    userInstance.properties = params
    def categoryIdsParameter = params['categoryIds']
    if (categoryIdsParameter) {
      if (categoryIdsParameter instanceof String) {
        userInstance.categories = [Category.get(categoryIdsParameter as Long)]
      }
      else {
        def categoryIds = []
        categoryIdsParameter.each {
          categoryIds.add it as Long
        }
        userInstance.categories = Category.getAll(categoryIds)
      }
    }
    if (!userInstance.validate()) {
      def availableCategories = Category.list()
      render(view: "index", model: [userInstance: userInstance, availableCategories: availableCategories])
    }
    else {
      log.debug("Updating user (categories: $userInstance.categories)")
      userInstance.save()
      flash.message = g.message(code: "profile.updated")
      redirect(controller: "message")
    }

  }
}
