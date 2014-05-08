package gui;
 
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
/**
*
* @web http://java-buddy.blogspot.com/
*/
public class JavaFX_TableView extends Application {
 
  public class Record{
      private SimpleStringProperty fieldMonth;
      private SimpleDoubleProperty fieldValue;
     
      Record(String fMonth, double fValue){
          this.fieldMonth = new SimpleStringProperty(fMonth);
          this.fieldValue = new SimpleDoubleProperty(fValue);
      }
     
      public String getFieldMonth() {
          return fieldMonth.get();
      }
     
      public double getFieldValue() {
          return fieldValue.get();
      }
     
  }
 
  private TableView<Record> tableView = new TableView<>();
 
  private ObservableList<Record> dataList =
          FXCollections.observableArrayList(          
              new Record("January", 100),
              new Record("February", 200),
              new Record("March", 50),
              new Record("April", 75),
              new Record("May", 110),
              new Record("June", 300),
              new Record("July", 111),
              new Record("August", 30),
              new Record("September", 75),
              new Record("October", 55),
              new Record("November", 225),
              new Record("December", 99));
 
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      launch(args);
  }
 
  @Override
  public void start(Stage primaryStage) {
      primaryStage.setTitle("java-buddy.blogspot.com");
     
      Group root = new Group();
 
      TableColumn columnMonth = new TableColumn("Month");
      columnMonth.setCellValueFactory(
              new PropertyValueFactory<Record,String>("fieldMonth"));
 
      TableColumn columnValue = new TableColumn("Value");
      columnValue.setCellValueFactory(
              new PropertyValueFactory<Record,Double>("fieldValue"));
     
      tableView.setItems(dataList);
      tableView.getColumns().addAll(columnMonth, columnValue);
     
      VBox vBox = new VBox();
      vBox.setSpacing(10);
      vBox.getChildren().add(tableView);
 
      root.getChildren().add(vBox);
     
      primaryStage.setScene(new Scene(root, 300, 250));
      primaryStage.show();
  }
}