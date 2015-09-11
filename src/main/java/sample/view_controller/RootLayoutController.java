package sample.view_controller;

import com.ebay.soap.eBLBaseComponents.ItemType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import sample.Main;
import sample.model.EbayEngine;
import sample.model.Model;
import sample.model.MyItem;

import java.io.File;
import java.text.NumberFormat;
public class RootLayoutController {
    public ToggleGroup viewToggleGroup;
    Main main;
    Model model;
    @FXML
    private RadioMenuItem tableView;
    @FXML
    private RadioMenuItem itemView;
    @FXML
    private BorderPane rootPane;
    @FXML
    private TextField currentItemTextField;
    @FXML
    private TextField currentItemINDEXtextField;
    @FXML
    private Label feesLabel;
    @FXML
    private Button uploadButton;
    @FXML
    private Button deleteAllButton;
    @FXML
    public void handleDeleteAll() {
        EbayEngine.deleteAll();
    }
    @FXML
    void handleOpen() {
        model.getListOfItems().clear();
        handleMerge();
    }
    @FXML
    void handleMerge() {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog( main.getPrimaryStage() );
        if (f != null) {
            main.loadListOfItemsFromXML( f );
            main.saveToConfig( f.getAbsolutePath() );
        }
    }
    @FXML
    void handleClose() {
        handleSave();
        System.exit( 0 );
    }
    @FXML
    void handleSave() {
        String s = main.readConfig();
        if (s != null) {
            File lastAccesedFile = new File( s );
            main.saveListOfItemsToXML( lastAccesedFile );
        } else {
            handleSaveAs();
        }
    }
    @FXML
    void handleSaveAs() {
        FileChooser fc = new FileChooser();
        File f = fc.showSaveDialog( main.getPrimaryStage() );
        if (f != null) {
            if (!f.getPath().endsWith( ".xml" )) {
                f = new File( f.getPath() + ".xml" );
            }
            main.saveListOfItemsToXML( f );
            main.saveToConfig( f.getAbsolutePath() );
        }
    }
    @FXML
    void handleAddNewPictureFolder() {
        DirectoryChooser fc = new DirectoryChooser();
        File f = fc.showDialog( main.getPrimaryStage() );
        if (f != null) {
            main.addPictureFolder( f );
        }
    }
    @FXML
    void uploadItemstoEbay() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        double fees = 0;
        for (MyItem item : model.getListOfItems()) {
            double temp;
            if (item.getReadyForUploadAndValidated()) {
                ItemType itemType = EbayEngine.buildItemType( item );
                temp = EbayEngine.uploadItem( itemType );
                if (temp != -1) {
                    item.setUploaded( true );
                    fees += temp;
                } else
                    item.setUploaded( false );
                try {
                    Thread.sleep( 6000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        feesLabel.setText( nf.format( fees ) );
    }
    @FXML
    private void initialize() {
        model = Model.getInstance();
/*        currentItemTextField.textProperty().bind( model.getCurrentItem().titleProperty() );
        StringProperty sp3 = currentItemINDEXtextField.textProperty();
        IntegerProperty ip1 = model.indexProperty();
        StringConverter<Number> converter = new NumberStringConverter(); // converts between strings and objects
        Bindings.bindBidirectional( sp3,ip1,converter );*/
        tableView.setUserData( "tableView" );
        itemView.setUserData( "itemView" );
        viewToggleGroup.selectedToggleProperty().addListener( new ChangeListener<Toggle>() {
            @Override
            public void changed( ObservableValue<? extends Toggle> observableValue,Toggle oldToggle,Toggle newToggle ) {
                // Important, changed() seems to be called twice,
                if (viewToggleGroup.getSelectedToggle() != null) {
                    //on first call.// selectedToggle=null  giving null pointer exception , obsValue=null,old ok,
                    // new=null//on second call://ok , ok , null , ok.
                    main.showMainPanel( viewToggleGroup.getSelectedToggle().getUserData().toString() );
                }
            }
        } );
        rootPane.addEventFilter( KeyEvent.KEY_TYPED,kEvent -> {
            if (kEvent.isControlDown()) {
                switch (kEvent.getCode()) {
                    case F12:
                        ;
                        break;
                    case F11:
                        ;
                        break;
                }
            }
        } );
    }
    public void setMain( Main main ) {
        this.main = main;
    }
}
