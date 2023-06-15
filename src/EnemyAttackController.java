import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class EnemyAttackController {

    @FXML
    private Label myLabel;
    @FXML
    private TextField myTextField;
    @FXML
    private Button myButton;
    @FXML
    private ImageView myImageView;
    int base;

    public void enter(ActionEvent event) {

        try {
            base = Integer.parseInt(myTextField.getText());
            System.out.println(base);

            if(base<1 || base > 10) {
                myLabel.setText("Invalid: Enter base number 1-10.");
            }
            else {
                if(base==1) {
                    myLabel.setText("Best path(s): \n1");
                }
                else if(base==2) {
                    myLabel.setText("Best path(s): \n1 -> 2");
                }
                else if(base==3) {
                    myLabel.setText("Best path(s): \n1 -> 3");
                }
                else if(base==4) {
                    myLabel.setText("Best path(s): \n1 -> 2 -> 4 \n1 -> 3 -> 4");
                }
                else if(base==5) {
                    myLabel.setText("Best path(s): \n1 -> 6 -> 5");
                }
                else if(base==6) {
                    myLabel.setText("Best path(s): \n1 -> 6");
                }
                if(base==7) {
                    myLabel.setText("Best path(s): \n1 -> 3 -> 7 \n1 -> 6 -> 7");
                }
                else if(base==8) {
                    myLabel.setText("Best path(s): \n1 -> 6 -> 8 \n1 -> 10 -> 8");
                }
                else if(base==9) {
                    myLabel.setText("Best path(s): \n1 -> 10 -> 9");
                }
                else if(base==10) {
                    myLabel.setText("Best path(s): \n1 -> 10");
                }
            }
        }
        catch (NumberFormatException e) {
            myLabel.setText("Invalid: Enter only numbers please.");
        }

        catch (Exception e) {
            myLabel.setText("Error");
        }
    }

}
