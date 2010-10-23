package net.coolcoders.showcase.web.vaadin.fieldFactory;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import net.coolcoders.showcase.model.Category;
import net.coolcoders.showcase.model.Gender;
import net.coolcoders.showcase.service.CategoryService;
import net.coolcoders.showcase.web.vaadin.UiConstants;

import java.util.Arrays;
import java.util.List;

public class UserFieldFactory extends DefaultFieldFactory {

    private CategoryService categoryService;

    public UserFieldFactory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Field createField(Item item, Object propertyId,
                             Component uiContext) {
        Field f = super.createField(item, propertyId, uiContext);
        if ("username".equals(propertyId)) {
            TextField tf = (TextField) f;
            tf.setRequired(true);
            tf.setRequiredError("Please enter your Username");
            tf.setWidth(UiConstants.COMMON_FIELD_WIDTH);
            tf.setNullRepresentation("");
        } else if ("password".equals(propertyId)) {
            TextField tf = (TextField) f;
            tf.setRequired(true);
            tf.setRequiredError("Please enter your Password");
            tf.setWidth(UiConstants.COMMON_FIELD_WIDTH);
            tf.setNullRepresentation("");
        } else if ("email".equals(propertyId)) {
            TextField tf = (TextField) f;
            tf.setRequired(true);
            tf.setRequiredError("Please enter your Email");
            tf.setWidth(UiConstants.COMMON_FIELD_WIDTH);
            tf.setNullRepresentation("");
        } else if ("fullname".equals(propertyId)) {
            TextField tf = (TextField) f;
            tf.setWidth(UiConstants.COMMON_FIELD_WIDTH);
            tf.setNullRepresentation("");
        } else if ("gender".equals(propertyId)) {
            OptionGroup group = new OptionGroup("Gender", Arrays.asList(Gender.MALE, Gender.FEMALE));
            group.setNullSelectionAllowed(false);
            return group;
        } else if ("categories".equals(propertyId)) {
            List<Category> categories = categoryService.listAll();
            BeanItemContainer<Category> container = new BeanItemContainer<Category>(Category.class);
            for (Category category : categories) {
                container.addBean(category);
            }
            OptionGroup group = new OptionGroup("Categories", container);
            group.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
            group.setItemCaptionPropertyId("name");

            return group;
        }
        return f;
    }
}