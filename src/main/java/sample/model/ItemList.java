package sample.model;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement (name = "itemList")    // doesnt have to be same name.
public class ItemList {
    private List<MyItem> itemList;    // MyItem and EbayCategory need default no arg consturctor.
    private int index;
    private List<EbayCategory> categoryList;

    @XmlElement (name = "item")
    public List<MyItem> getItemList() {
        return itemList;
    }
    public void setItemList( List<MyItem> itemList ) {
        this.itemList = itemList;
    }

    @XmlElement (name = "category")
    public List<EbayCategory> getCategoryList() {
        return categoryList;
    }
    public void setCategoryList( List<EbayCategory> categoryList ) {
        this.categoryList = categoryList;
    }

    @XmlAttribute
    public void setIndex( int i ) {
        index = i;
    }
    public int getIndex() {
        return index;
    }
}
