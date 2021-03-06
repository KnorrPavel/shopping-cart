package com.logicify.shoppingcart.admin;

import com.logicify.shoppingcart.domain.Category;
import com.logicify.shoppingcart.service.CategoryService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class CategoriesList extends AbstractAdminPage{

    @SpringBean(required = true)
    private CategoryService service;

    public CategoriesList() {
        List categories = this.service.loadAllCategories();
        ListView<Category> listview = new ListView<Category>("categoryList", categories) {
            protected void populateItem(ListItem<Category> item) {
                Category category = item.getModelObject();
                BookmarkablePageLink<Category> linkToCategory = new BookmarkablePageLink<Category>("linkToCategory", CategoryInfo.class);
                linkToCategory.getPageParameters().set("id", category.getId());
                linkToCategory.add(new Label("linksText", category.getName()));
                item.add(linkToCategory);
            }
        };
        add(listview);
    }

}
