package com.logicify.shoppingcart.user;

import com.logicify.shoppingcart.admin.ProductInfo;
import com.logicify.shoppingcart.domain.Product;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.List;

public class ProductSearchPanel extends Panel {

    private Fragment currentFragment;

    public ProductSearchPanel(String id) {
        super(id);
        setOutputMarkupPlaceholderTag(true);
        currentFragment = InitEmptyFragment();
        add(currentFragment);
    }

    protected Fragment InitEmptyFragment() {
        Fragment fragment = new Fragment("searchResult", "empty",this);
        return fragment;
    }

    public void setFragment(Fragment fragment){
        currentFragment.replaceWith(fragment);
    }

    public Fragment createResultListFragment(List<Product> list){
        Fragment fragment = new Fragment("searchResult", "results",this);
        ListView<Product> listView = new ListView<Product>("productList") {
            @Override
            protected void populateItem(ListItem<Product> productListItem) {
                Product product = productListItem.getModelObject();
                BookmarkablePageLink<Product> linkToProduct = new BookmarkablePageLink<Product>("linkToProduct", ProductInfo.class);
                linkToProduct.getPageParameters().set("id", product.getId());
                linkToProduct.add(new Label("linksText", product.getName()));
                productListItem.add(linkToProduct);
            }
        };
        if (!list.isEmpty()){
            listView.setList(list);
        }
        fragment.add(listView);
        return fragment;
    }

    public Fragment createNoResultListFragment (String errMessage){
        Fragment fragment = new Fragment("searchResult", "noResults",this);
        fragment.add(new Label("errorMessage", errMessage));
        return fragment;
    }
}