package sample.view_controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import sample.model.Model;
import sample.model.MyItem;
public class TableViewController extends ViewController {
    // Model model inherited from superClass
    @FXML
    private TableView<MyItem> itemsTable;
    @FXML
    private TableColumn<MyItem,String> titleCol;
    @FXML
    private TableColumn<MyItem,String> subtitleCol;
    @FXML
    private TableColumn<MyItem,Integer> priceCol;
    @FXML
    private TableColumn<MyItem,String> pictureCol;
    @FXML
    private TableColumn<MyItem,String> pic2Col;
    @FXML
    private TableColumn<MyItem,String> pic3Col;
    @FXML
    private TableColumn<MyItem,String> pic4Col;
    @FXML
    private TableColumn<MyItem,String> pic5Col;
    @FXML
    private TableColumn<MyItem,String> pic6Col;
    @FXML
    private TableColumn<MyItem,String> postageCol;
    Model model;
    @FXML
    public void handleNew() {
        itemsTable.getItems().add( new MyItem() );
    }
    @FXML
    public void handleDelete() {
        model.deleteCurrenItem( itemsTable.getItems().get( model.getIndex() ) );
    }
    @FXML
    public void setup() {
        model = Model.getInstance();
        itemsTable.setItems( model.getListOfItems() );
        titleCol.setCellValueFactory( new PropertyValueFactory<MyItem,String>( "title" ) );
        titleCol.setCellFactory( new TextFieldCellFactory() );
        subtitleCol.setCellValueFactory( new PropertyValueFactory<MyItem,String>( "subTitle" ) );
        subtitleCol.setCellFactory( new TextFieldCellFactory() );
        pictureCol.setCellValueFactory( new PropertyValueFactory<MyItem,String>( "picture_1" ) );
        pictureCol.setCellFactory( new PictureCellFactory() );
        pic2Col.setCellValueFactory( new PropertyValueFactory<MyItem,String>( "picture_2" ) );
        pic2Col.setCellFactory( new PictureCellFactory() );
        pic3Col.setCellValueFactory( new PropertyValueFactory<MyItem,String>( "picture_3" ) );
        pic3Col.setCellFactory( new PictureCellFactory() );
        pic4Col.setCellValueFactory( new PropertyValueFactory<MyItem,String>( "picture_4" ) );
        pic4Col.setCellFactory( new PictureCellFactory() );
        pic5Col.setCellValueFactory( new PropertyValueFactory<MyItem,String>( "picture_5" ) );
        pic5Col.setCellFactory( new PictureCellFactory() );
        pic6Col.setCellValueFactory( new PropertyValueFactory<MyItem,String>( "picture_6" ) );
        pic6Col.setCellFactory( new PictureCellFactory() );
        Platform.runLater( new Runnable() {
            @Override
            public void run() {
                itemsTable.requestFocus();
                itemsTable.getSelectionModel().select( model.getCurrentItem() );
                itemsTable.getFocusModel().focus( itemsTable.getSelectionModel().getSelectedIndex() );
            }
        } );
        // listens for changes in selected item internal state.
        // RO ObjectPropery
        itemsTable.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<MyItem>() {
            @Override
            public void changed( ObservableValue<? extends MyItem> observableValue,MyItem myItem,MyItem myItem2 ) {
                model.setCurrentItem( myItem2 );        // unb8nded, a bound item canot be set
                int i = itemsTable.getSelectionModel().getSelectedIndex();
                int j = itemsTable.getSelectionModel().getFocusedIndex();
                int k = itemsTable.getFocusModel().getFocusedIndex();
                int a = 2;
            }
        } );
        itemsTable.getFocusModel().focusedItemProperty().addListener( new ChangeListener<MyItem>() {
            @Override
            public void changed( ObservableValue<? extends MyItem> observableValue,MyItem myItem,MyItem myItem2 ) {
                model.setCurrentItem( myItem2 );        // unb8nded, a bound item canot be set
                int i = itemsTable.getSelectionModel().getSelectedIndex();
                int j = itemsTable.getSelectionModel().getFocusedIndex();
                int k = itemsTable.getFocusModel().getFocusedIndex();
                int a = 2;
            }
        } );
    }
    public void update() {
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static class TextFieldCellFactory implements Callback<TableColumn<MyItem,String>,TableCell<MyItem,String>> {
        @Override
        public TableCell<MyItem,String> call( TableColumn<MyItem,String> itemStringTableColumn ) {
            return new TextFieldCell();
        }
    }
    static class TextFieldCell extends TableCell<MyItem,String> {
        TextField f;
        StringProperty boundTo = null;
        {
            f = new TextField();
            setGraphic( f );
        }
        @Override
        public void updateItem( String item,boolean empty ) {
            if (!empty) {
                super.updateItem( item,empty );
                setContentDisplay( ContentDisplay.GRAPHIC_ONLY );
                //have to keep track  the textField is bound to the correct property
                // Retrieve tha StringProperty that should be bound to Text Field. (in TableView ObservableList )
                ObservableValue<String> o = getTableColumn().getCellObservableValue( getIndex() );
                SimpleStringProperty s = (SimpleStringProperty) o;
                // unbind first if bound to another property, then bind it.
                if (boundTo != s) {
                    if (boundTo != null) f.textProperty().unbindBidirectional( boundTo );
                    f.textProperty().bindBidirectional( s );
                    boundTo = s;
                }
            } else setContentDisplay( ContentDisplay.TEXT_ONLY );
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static class PictureCellFactory implements Callback<TableColumn<MyItem,String>,TableCell<MyItem,String>> {
        @Override
        public TableCell<MyItem,String> call( TableColumn<MyItem,String> itemStringTableColumn ) {
            return new PictureCell();
        }
    }
    static class PictureCell extends TableCell<MyItem,String> {
        ImageView img;
        String picture;
        public PictureCell() {
            img = new ImageView();
            img.setFitHeight( 40 );
            img.setPreserveRatio( true ); // this one does the resizing
            img.setSmooth( true );  // don't know exactly what it does.
            img.setCache( true );
            setGraphic( img );
        }
        @Override
        public void updateItem( String pic,boolean empty ) {
            if (pic != picture && pic != null) {
                img.setImage( new Image( "rawPics/" + pic ) );
                picture = pic;
            }
        }
    }
}
