package sample.model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;
public class Model {
    final ObservableMap<String,Image> mapOfRAWpics;
    final private ObservableList<MyItem> listOfItems;
    ObjectProperty<MyItem> currentItem;
    private IntegerProperty index;
    private ObservableList<EbayCategory> ebayCategoriesList;
//-------------------------------------------------------------------------------------singleton ------//
    private static Model instance = null;                                                              //
    private Model() {     // when adding items to empty list, first add then set current item.
        listOfItems = FXCollections.observableArrayList();
        mapOfRAWpics = FXCollections.observableHashMap();
        currentItem = new SimpleObjectProperty<>();
        index = new SimpleIntegerProperty();
        ebayCategoriesList = FXCollections.observableArrayList( );
        currentItem.addListener( ( observableValue,myItem,myItem2 ) -> index.set( listOfItems.indexOf( myItem2 ) ) );
        index.addListener( ( observableValue,number,number2 ) -> currentItem.set ( listOfItems.get( (Integer)number2 )) );
    }
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();

        }
        return instance;
    } //-----------------------------------------------------------
    public void deleteCurrenItem( MyItem item ) {
        listOfItems.remove( item );
    }
    //----------------------------------------------------------------------------------------------------
    //                               Getter and Setters
    //----------------------------------------------------------------------------------------------------
    public MyItem getCurrentItem() {
        return currentItem.get();
    }
    public void setCurrentItem( MyItem currentItem ) {
        this.currentItem.set( currentItem );
    }
    public ObjectProperty<MyItem> currentItemProperty() {
        return currentItem;
    }
    public ObservableMap<String,Image> getMapOfRawPics() {
        return mapOfRAWpics;
    }

    public Integer getIndex() {
        return index.get();
    }
    public void setIndex( Integer i ) { this.index.set( i ); }
    public IntegerProperty indexProperty() {
        return index;
    }
    public ObservableList<MyItem> getListOfItems() { return listOfItems; }

    public ObservableList<EbayCategory> getEbayCategoriesList() {
        return ebayCategoriesList;
    }
    public void setEbayCategoriesList( ObservableList<EbayCategory> ebayCategoriesList ) {
        this.ebayCategoriesList = ebayCategoriesList;
    }
}
